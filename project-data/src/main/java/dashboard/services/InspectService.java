package dashboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.progress.ActorProgressDTOConverter;
import dashboard.constraints.progress.FeatureProgressDTOConverter;
import dashboard.constraints.progress.ProjectProgressDTOConverter;
import dashboard.constraints.progress.StoryProgressDTOConverter;
import dashboard.constraints.progress.TaskProgressDTOConverter;
import dashboard.data.dto.progress.ActorProgressDTO;
import dashboard.data.dto.progress.FeatureProgressDTO;
import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.data.dto.progress.TaskProgressDTO;
import dashboard.data.dto.progress.UserStoryProgressDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Feature;
import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.entities.UserStory;

@Service
public class InspectService {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private FeatureService featureService;

	@Autowired
	private UserStoryService storyService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TimeLogService logService;

	@Autowired
	private ProjectProgressDTOConverter dtoConverter;

	@Autowired
	private FeatureProgressDTOConverter featureDTOConverter;

	@Autowired
	private ActorProgressDTOConverter actorConverter;

	@Autowired
	private StoryProgressDTOConverter storyProgressDTOConverter;

	@Autowired
	private TaskProgressDTOConverter taskDtoConverter;

	public ProjectProgressDTO inspectProject(Integer projectID) {

		Project project = projectService.findById(projectID);
		ProjectProgressDTO ans = null;

		if (project != null) {

			ans = dtoConverter.convertToDTO(project);

			List<Task> allProjectTasks = taskService.getAllByProject(project);

			System.out.println("all tasks in project " + allProjectTasks.size());

			ArrayList<Task> luckyTasks = new ArrayList<>();
			ArrayList<Task> orphanTaskList = new ArrayList<>();

			List<Actor> actors = actorService.getActorsInProject(projectID);

			int storyDoneTask = 0;
			int storyTotalTask = 0;
			float totalJH = 0;
			float billableJH = 0;

			for (Actor actor : actors) {

				ActorProgressDTO actorDTO = inspectActor(actor, luckyTasks);

				storyDoneTask += actorDTO.getDoneTaskNumber();
				storyTotalTask += actorDTO.getTaskNumber();
				totalJH += actorDTO.getTotalJHConsumme();
				billableJH += actorDTO.getFacturableJHConsomme();

				ans.getActors().add(actorDTO);

			}

			FeatureProgressDTO orphanTasks = new FeatureProgressDTO();

			for (Task currentTask : allProjectTasks) {

				boolean found = false;

				System.out.println("lucky tasks " + luckyTasks.size());

				for (Task luckyTask : luckyTasks) {

					if (currentTask.getId() == luckyTask.getId()) {
						found = true;
						break;
					}
				}
				if (!found) {
					orphanTaskList.add(currentTask);
				}

			}

			int orphanDoneTask = 0;
			int orphanTotalTask = orphanTaskList.size();
			float totalOrphanJH = 0;
			float billableOrphanJH = 0;

			for (Task task : orphanTaskList) {

				TaskProgressDTO taskDTO = inspectTask(task);

				if (taskDTO.getPourcentTickets() != null && taskDTO.getPourcentTickets() >= 100) {

					orphanDoneTask++;

				}

				orphanTasks.getTaskDTOs().add(taskDTO);

				totalOrphanJH += taskDTO.getTotalJHConsumme();
				billableOrphanJH += taskDTO.getFacturableJHConsomme();

			}

			orphanTasks.setPourcentTickets((float) orphanDoneTask / (float) orphanTotalTask * 100f);
			orphanTasks.setFacturableJHConsomme(billableOrphanJH);
			orphanTasks.setTotalJHConsumme(totalOrphanJH);
			orphanTasks.setDoneTaskNumber(orphanDoneTask);
			orphanTasks.setTaskNumber(orphanTotalTask);

			ans.setOrhanTasks(orphanTasks);

			ans.setDoneTaskNumber(storyDoneTask + orphanDoneTask);
			ans.setTaskNumber(storyTotalTask + orphanTotalTask);
			ans.setFacturableJHConsomme(billableJH + billableOrphanJH);
			ans.setTotalJHConsumme(totalJH + totalOrphanJH);

			ans.setPourcentTickets(
					(float) (storyDoneTask + orphanDoneTask) / (float) (storyTotalTask + orphanTotalTask) * 100f);

		}

		return ans;

	}

	// =========================================================================================================================

	// =========================================================================================================================

	private ActorProgressDTO inspectActor(Actor actor, List<Task> toRemove) {
		ActorProgressDTO actorDTO = actorConverter.convertToDTO(actor);

		List<UserStory> stories = storyService.getActorStories(actor.getId());

		int storyDoneTask = 0;
		int storyTotalTask = 0;
		float totalJH = 0;
		float billableJH = 0;

		for (UserStory story : stories) {

			UserStoryProgressDTO storyDTO = inspectStory(story, toRemove);

			storyDoneTask += storyDTO.getDoneTaskNumber();
			storyTotalTask += storyDTO.getTaskNumber();
			totalJH += storyDTO.getTotalJHConsumme();
			billableJH += storyDTO.getFacturableJHConsomme();

			actorDTO.getStories().add(storyDTO);

		}

		actorDTO.setDoneTaskNumber(storyDoneTask);
		actorDTO.setTaskNumber(storyTotalTask);
		actorDTO.setFacturableJHConsomme(billableJH);
		actorDTO.setTotalJHConsumme(totalJH);

		return actorDTO;
	}

	private UserStoryProgressDTO inspectStory(UserStory story, List<Task> toRemove) {

		UserStoryProgressDTO storyDTO = storyProgressDTOConverter.convertToDTO(story);

		List<Feature> features = featureService.findByStory(story.getId());

		int storyDoneTask = 0;
		int storyTotalTask = 0;
		float totalJH = 0;
		float billableJH = 0;

		for (Feature feature : features) {

			FeatureProgressDTO featureDTO = inspectFeature(feature, toRemove);

			storyDTO.getFeatures().add(featureDTO);

			storyDoneTask += featureDTO.getDoneTaskNumber();
			storyTotalTask += featureDTO.getTaskNumber();
			totalJH += featureDTO.getTotalJHConsumme() != null ? featureDTO.getTotalJHConsumme() : 0;
			billableJH += featureDTO.getFacturableJHConsomme() != null ? featureDTO.getFacturableJHConsomme() : 0;
			;
		}

		storyDTO.setPourcentTickets(null);
		storyDTO.setDoneTaskNumber(storyDoneTask);
		storyDTO.setTaskNumber(storyTotalTask);
		storyDTO.setFacturableJHConsomme(billableJH);
		storyDTO.setTotalJHConsumme(totalJH);

		return storyDTO;
	}

	private FeatureProgressDTO inspectFeature(Feature feature, List<Task> toreMove) {

		FeatureProgressDTO featureDTO = featureDTOConverter.convertToDTO(feature);

		if (feature.getTasks().size() > 0) {

			int doneTask = 0;
			float jhTotal = 0;
			float jhBilled = 0;

			for (Task task : feature.getTasks()) {

				toreMove.add(task);

				TaskProgressDTO taskDTO = inspectTask(task);

				if (taskDTO.getPourcentTickets() != null && taskDTO.getPourcentTickets() >= 100) {

					doneTask++;

				}

				featureDTO.getTaskDTOs().add(taskDTO);

				jhTotal += taskDTO.getTotalJHConsumme();
				jhBilled += taskDTO.getFacturableJHConsomme();

			}

			featureDTO.setPourcentTickets((float) doneTask / (float) feature.getTasks().size() * 100f);
			featureDTO.setFacturableJHConsomme(jhBilled);
			featureDTO.setTotalJHConsumme(jhTotal);
			featureDTO.setDoneTaskNumber(doneTask);
			featureDTO.setTaskNumber(feature.getTasks().size());
		}

		return featureDTO;
	}

	private TaskProgressDTO inspectTask(Task task) {
		TaskProgressDTO taskDTO = taskDtoConverter.convertToDTO(task);

		if (task.getCloseDate() != null) {
			taskDTO.setPourcentTickets(100f);
		} else {
			taskDTO.setPourcentTickets(0f);
		}

		taskDTO.setTotalJHConsumme(logService.getSecondsConsumed(task) / (3600 * 8));

		if (task.getIsBillable() != null && task.getIsBillable()) {
			taskDTO.setFacturableJHConsomme(logService.getSecondsConsumed(task) / (3600 * 8));
		} 
		
		taskDTO.setFacturableJHConsomme(0f);
		

		return taskDTO;

	}

}
