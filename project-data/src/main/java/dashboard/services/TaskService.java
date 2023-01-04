package dashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.repositories.ProjectRepository;
import dashboard.data.repositories.TaskRepisitory;

@Service
public class TaskService {

	Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	TaskRepisitory taskRepo;
	
	@Autowired
	ProjectService projectService ;

//	@Autowired
//	ProjectRepository projectRepo;

	public void saveTask(Task tsk) {
		
		if(tsk.getProject()!= null) {
			projectService.save(tsk.getProject());
		}
		
		taskRepo.save(tsk);
	}

	public ArrayList<Task> allActiveFormatHTML(Project prj) {
		ArrayList<Task> ans = new ArrayList<Task>();

		for (Task tasuku : taskRepo.findAllByProject(prj)) {

			if (tasuku.getCloseDate() == null) {

				ans.add(tasuku);

			}

		}

		return ans;
	}

	public boolean createTask(String subject) {

		return getOrCreateTask(subject) != null;

	}

	public Task getOrCreateTask(String subject) {

		StringTokenizer stk = new StringTokenizer(subject, "()");

		if (stk.countTokens() == 3) {
			String trash = stk.nextToken();
			String ticket = stk.nextToken();
			String summary = stk.nextToken();

			StringTokenizer stkin = new StringTokenizer(ticket, "-");

			if (stkin.countTokens() == 2) {
				String projectCode = stkin.nextToken();

				List<Project> projectList = projectService.getFromCode(projectCode);
				
				List<Task> tasks = taskRepo.findByTitle(ticket);

				if (tasks.size() == 0 && projectList.size() == 1) {
					Task task = new Task();
					task.setCreationDate(new Date());
					task.setProject(projectList.get(0));
					task.setTitle(ticket);
					task.setDescription(summary);
					projectService.save(projectList.get(0));
					taskRepo.save(task);
					return taskRepo.save(task);
				} else if (tasks.size() == 1) {
					return tasks.get(0);
				} else {
					logger.error("ERR : " + tasks.size() + " Tasks found and " + projectList.size()
							+ " projects found : " + subject);
					return null;
				}

			} else {
				logger.error("ERR : inside token was " + ticket);
				return null;
			}

		} else {
			logger.error("ERR : count token was " + stk.countTokens() + " in " + subject);
			return null;
		}

//		System.out.println("ERR : ?????  "+subject);
//		return null;

	}

	public boolean markTaskDone(String sujet) {

		Task t = this.getOrCreateTask(sujet);

		if (t != null) {

			t.setCloseDate(new Date());
			t.setCloseComment("Closed in Jira");

			taskRepo.save(t);

			return true;
		}

		return false;
	}

	
	public List<Task> getAllByProject(Project prj){
		ArrayList<Task> tsks = new ArrayList<Task>() ;
		
		tsks.addAll(taskRepo.findAllByProject(prj) );
		
		
		return tsks ;
	}
	
	
	
}