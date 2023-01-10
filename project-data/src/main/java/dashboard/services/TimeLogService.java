package dashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.metrics.buffering.StartupTimeline.TimelineEvent;
import org.springframework.stereotype.Service;

import dashboard.data.entities.People;
import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.entities.TimeLog;
import dashboard.data.repositories.ProjectRepository;
import dashboard.data.repositories.TimeLogRepository;

@Service
public class TimeLogService {

	private Logger logger = LoggerFactory.getLogger(TimeLogService.class);

	@Autowired
	private TimeLogRepository timeLogRepository;

	@Autowired
	private TaskService taskService;

	@Autowired
	private PeopleService peopleService;
	
	/*
	 * Entrée du journal de travail: 1 heure, 30 minutes enregistré par Iony Mahefa
	 * RASANDIFERA Changement par: Iony Mahefa RASANDIFERA Tempo Work Attribute
	 * value for Billable: 0
	 */
	
	@Autowired
	ProjectRepository projectRepository ;

	private void setTempoTime(TimeLog tempo, String timePassed) {
		String[] parts = timePassed.split(" ");
	
		for (int i = 0; i < parts.length; i++) {
	
			if (parts[i].contains("heure")) {
	
				tempo.setSeconds(tempo.getSeconds() + (Float.parseFloat(parts[i - 1].trim()) * 3600f));
			}
			if (parts[i].contains("minute")) {
	
				tempo.setSeconds(tempo.getSeconds() + (Float.parseFloat(parts[i - 1].trim()) * 60f));
			}
	
		}
	}


	/*
	 * Entrée du journal de travail: 1 heure, 30 minutes enregistré par Iony Mahefa
	 * RASANDIFERA Changement par: Iony Mahefa RASANDIFERA Tempo Work Attribute
	 * value for Billable: 0
	 */
	
	private boolean stringContainsNumber(String s) {
	
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}


	public List<TimeLog> findAllByTask(Task task){
		return timeLogRepository.findByTask(task) ;
	}
	

	/*
	 * Entrée du journal de travail: 1 heure, 30 minutes enregistré par Iony Mahefa
	 * RASANDIFERA Changement par: Iony Mahefa RASANDIFERA Tempo Work Attribute
	 * value for Billable: 0
	 */

	public boolean stripTempo(String sujet, String line) {

		Task task = taskService.getOrCreateTask(sujet);

		TimeLog tempo = new TimeLog();
		tempo.setTask(task);
		tempo.setSeconds(0f);
		tempo.setCreationDate(new Date());
		tempo.setMailSubject(sujet);

		/// System.out.println(">>>" + line);

		People own = null;

		String[] parts = line.split(":");

		for (String string : parts) {

			/// process time
			if ((string.contains("heure") || string.contains("minute")) && string.contains("enregis")) {

				setTempoTime(tempo, string);
			}

			/// process owner
			if (string.contains("Temp") || string.contains("Estimation restante")) {
				int index = string.indexOf("Temp");
				if (index < 0) {
					index = string.indexOf("Estimation restante");
				}
				if (index > 0) {
					String fullName = string.substring(0, index);
					own = peopleService.findOrCreateWithFullName(fullName);

					if (own.getLastName() == null || own.getLastName().isEmpty() || own.getLastName().isBlank()
							|| stringContainsNumber(fullName)) {
						logger.error("invalid name for Tempo owner");
					} else {
						tempo.setOwner(own);
					}

				} else {
					logger.error("\"Temp\" found at " + index + " in :" + string);

					return false;
				}
			}

		}

		if (tempo.getSeconds() == 0) {

			logger.error("no time recorded in " + line + " [" + sujet + "]");
			return false;

		}

		if (own == null) {

			logger.error("no one recorded in " + line + " [" + sujet + "]");
			return false;
		}

		if (task == null) {
			logger.error("no task found for " + line + " [" + sujet + "]");
			return false;
		}

		timeLogRepository.save(tempo);
		taskService.saveTask(task);
		
		return true;

	}
	
	public  List<TimeLog> getTimeLogByProjectID(Integer projectID){
		
		List<TimeLog> ans = new ArrayList<>() ;
		
		Project p = projectRepository.findById(projectID).get() ; 
		
		if(p!=null) {
			
			List<Task> tasks = taskService.getAllByProject(p);
			
			for(Task tsk : tasks) {
				ans.addAll(timeLogRepository.findByTask(tsk));
			}
			
			
		}
		
		return ans ;
	}

}
