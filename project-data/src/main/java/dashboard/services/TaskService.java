package dashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.TaskDTOConverter;
import dashboard.data.dto.TaskDTO;
import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.repositories.TaskRepisitory;

@Service
public class TaskService {

	Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	TaskRepisitory taskRepo;

	@Autowired
	TaskDTOConverter taskConverter;

	@Autowired
	ProjectService projectService;

//	@Autowired
//	ProjectRepository projectRepo;

	public void saveTask(Task tsk) {

		if (tsk.getProject() != null) {
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

				List<Task> tasks = taskRepo.findByCode(ticket);

				if (tasks.size() == 0 && projectList.size() == 1) {
					Task task = new Task();
					task.setCreationDate(new Date());
					task.setProject(projectList.get(0));
					task.setCode(ticket);
					task.setTitle(summary);
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

	public List<Task> getAllByProject(Project prj) {
		ArrayList<Task> tsks = new ArrayList<Task>();

		tsks.addAll(taskRepo.findAllByProject(prj));

		return tsks;
	}

	public TaskDTO addTaskToProject(Integer projectID, TaskDTO taskDTO) {

		List<Task> tasks = taskRepo.findByCode(taskDTO.getCode());

		Task tsk = null;

		if (tasks.size() > 0) {
			tsk = tasks.get(0);
			if (tsk.getTitle() == null)
				tsk.setTitle(taskDTO.getTitle());
			if (tsk.getDescription() == null)
				tsk.setDescription(taskDTO.getDescription());

		} else {
			tsk = taskConverter.convertFormDTO(taskDTO);
		}

		if (tsk.getCreationDate() == null)
			tsk.setCreationDate(new Date());

		Project prj = projectService.getFromProjectID(projectID);

		tsk.setProject(prj);

		return taskConverter.convertToDTO(taskRepo.save(tsk));
	}

	public List<TaskDTO> getAllByProjectID(Integer projectID) {

		Project prj = projectService.getFromProjectID(projectID);

		if (prj != null) {
			return taskConverter.covertAllToDTO(getAllByProject(prj));
		}

		return null;
	}

	public TaskDTO markTaskDone(TaskDTO dto) {
		Task tsk = null;

		if (dto.getCode() != null) {
			List<Task> tasks = taskRepo.findByCode(dto.getCode());
			if (tasks.size() > 0) {
				tsk = tasks.get(0);
			}
		} else if (dto.getId() != null) {
			tsk = taskRepo.findById(dto.getId()).get();
		}

		if (tsk != null) {

			if (tsk.getCloseDate() == null)
				tsk.setCloseDate(new Date());
			if (tsk.getCloseComment() == null)
				tsk.setCloseComment(dto.getCloseComment());

			return taskConverter.convertToDTO(taskRepo.save(tsk));

		}

		return null;
	}

	public TaskDTO setTaskDue(TaskDTO dto) {
		Task tsk = null;

		if (dto.getCode() != null) {
			List<Task> tasks = taskRepo.findByCode(dto.getCode());
			if (tasks.size() > 0) {
				tsk = tasks.get(0);
			}
		} else if (dto.getId() != null) {
			tsk = taskRepo.findById(dto.getId()).get();
		}

		if (tsk != null) {

			if (tsk.getDueDate() == null) {
				
				Task tempTask = taskConverter.convertFormDTO(dto);
				tsk.setDueDate(tempTask.getDueDate());

			}

			return taskConverter.convertToDTO(taskRepo.save(tsk));

		}

		return null;
	}

}
