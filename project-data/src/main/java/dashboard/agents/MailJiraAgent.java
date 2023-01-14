package dashboard.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dashboard.services.TaskService;
import dashboard.services.TimeLogService;
import jakarta.mail.Address;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

@Service
public class MailJiraAgent {
	/*
	 * @Scheduled(fixedDelay = 1000) public void scheduleFixedDelayTask() {
	 * System.out.println( "Fixed delay task - " + System.currentTimeMillis() /
	 * 1000); }
	 */

	Logger logger = LoggerFactory.getLogger(MailJiraAgent.class);

	@Autowired
	private Environment env;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TimeLogService timeLogService;

	private Session getImapSession() {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.imap.host", env.getProperty("mail.host"));
		props.setProperty("mail.imap.port", env.getProperty("mail.port"));
		props.setProperty("mail.imap.ssl.enable", "true");

		Session session = Session.getDefaultInstance(props, null);

		session.setDebug(false);

		return session;
	}

	private boolean scanDocument(String sujet, Document doc) {

		Elements elts = doc.select("tr");

		
//		System.out.println("------------ "+sujet + "--------------");

		for (Element elt : elts) {

			String lineContent = (elt.text());
			
			//update a tempo
			if(lineContent.contains(" jour un journal de travail")) {
				return false ;
			}
			

			if (lineContent.contains("e du journal de travail:") && lineContent.contains("Changement par:")) {

				if (timeLogService.stripTempo(sujet, lineContent)) {
//					System.out.println("Nice ! > "+lineContent);
//					System.out.println("-----------------------------------------");
					return true;
				} else {
					logger.error("Tried to add tempo but failed : [" + lineContent + "] [" + sujet + "]");
				}
			} else if (lineContent.contains("solution: Termin")) {

				if (taskService.markTaskDone(sujet)) {
//					System.out.println("Done ! > "+lineContent);
//					System.out.println("-----------------------------------------");
					return true;
				} else {
					logger.error("Tried to close Task but failed : " + lineContent + " (" + sujet + ")");
				}

			} else {
//				System.out.println(">"+lineContent);
			}

		}
//		System.out.println("-----------------------------------------");
		return false;
	}
	
	public List<String>  markNotRead() {
		// create session object
		
		List<String> ans = new ArrayList<>() ;
		
				Session session = this.getImapSession();

				try {
					// connect to message store
					Store store = session.getStore("imap");

					store.connect(env.getProperty("mail.host"), Integer.parseInt(env.getProperty("mail.port")),
							env.getProperty("mail.username"), env.getProperty("mail.password"));
					// open the inbox folder

					Folder inbox = (Folder) store.getFolder("INBOX");
					inbox.open(Folder.READ_WRITE);
					// fetch messages
					Message[] messages = inbox.getMessages();
					// read messages
					for (int i = 0; i < messages.length; i++) {
						Message msg = messages[i];
						Address[] fromAddress = msg.getFrom();
						String from = fromAddress[0].toString();
						String subject = msg.getSubject();
						Address[] toList = msg.getRecipients(RecipientType.TO);
						Address[] ccList = msg.getRecipients(RecipientType.CC);
						String contentType = msg.getContentType();

						try {
							
							msg.setFlag(Flags.Flag.SEEN, false);
							
							
						} catch (Exception ex) {
							
							ans.add(ex.getClass().getSimpleName() +" --- "+ ex.getMessage() + " --- "+ subject ) ;
							
						}

					}
				} catch (AuthenticationFailedException e) {
					logger.error("Exception (auth) in reading EMails : " + e.getMessage());
				} catch (MessagingException e) {
					logger.error("Exception (messaging) in reading EMails : " + e.getMessage());
				} catch (Exception e) {
					logger.error("Exception (" + e.getClass().getSimpleName() + ") in reading EMails : " + e.getMessage());
				} finally {
					logger.info("done reading mails");
					
				}
				
				return ans ;
		
	}

	public List<String> readInboundEmails() {
		
		List<String> ans  = new ArrayList<>() ;
		// create session object
		Session session = this.getImapSession();

		try {
			// connect to message store
			Store store = session.getStore("imap");

			store.connect(env.getProperty("mail.host"), Integer.parseInt(env.getProperty("mail.port")),
					env.getProperty("mail.username"), env.getProperty("mail.password"));
			// open the inbox folder

			Folder inbox = (Folder) store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			// fetch messages
			Message[] messages = inbox.getMessages();
			// read messages
			for (int i = 0; i < messages.length; i++) {
				Message msg = messages[i];
				Address[] fromAddress = msg.getFrom();
				String from = fromAddress[0].toString();
				String subject = msg.getSubject();
				Address[] toList = msg.getRecipients(RecipientType.TO);
				Address[] ccList = msg.getRecipients(RecipientType.CC);
				String contentType = msg.getContentType();

				try {
					if (!msg.getFlags().contains(Flags.Flag.SEEN)) {

						String content = msg.getContent().toString();
						// System.out.println(content);
						Document document = Jsoup.parse(content);

						// document.select("").

						boolean newTask = taskService.createTask(subject);
						boolean newInfo = scanDocument(subject, document);

						msg.setFlag(Flags.Flag.SEEN, newTask || newInfo);

					} else {
						// System.out.println(">>>Seen<<<");
					}
				} catch (Exception ex) {

					msg.setFlag(Flags.Flag.SEEN, false);
					ans.add("Exception (" + ex.getClass().getSimpleName() + ") in reading one email : "
							+ ex.getMessage());
				}

			}
		} catch (AuthenticationFailedException e) {
			ans.add("Exception (auth) in reading EMails : " + e.getMessage());
		} catch (MessagingException e) {
			ans.add("Exception (messaging) in reading EMails : " + e.getMessage());
		} catch (Exception e) {
			ans.add("Exception (" + e.getClass().getSimpleName() + ") in reading EMails : " + e.getMessage());
		} finally {
			ans.add("done reading mails");
			
		}
		
		return ans ;
	}

	// cron : s m h dom m dow
	@Scheduled(cron = "* */5 * * * *")
	public void scheduleFixedDelayTask() {
		// System.out.println("Fixed delay task - " + System.currentTimeMillis() /
		// 1000);
		//readInboundEmails();
	}

}
