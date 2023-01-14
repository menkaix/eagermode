package dashboard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dashboard.agents.MailJiraAgent;

@RestController
@RequestMapping(path="/garden")
public class MailManagementController {
	
	@Autowired
	private MailJiraAgent mailAgent ;
	
	
	@GetMapping(path="/mail-non-read")
	public @ResponseBody List<String> markAllNonRead() {
		
		
		return mailAgent.markNotRead() ;
		
	}
	
	@GetMapping(path="/read-mail")
	public @ResponseBody List<String> readMail() {
		
		
		return mailAgent.readInboundEmails() ;
		
	}

}
