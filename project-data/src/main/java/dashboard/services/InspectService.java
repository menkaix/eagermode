package dashboard.services;

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

			List<Actor> actors = actorService.getActorsInProject(projectID);

			for (Actor actor : actors) {

				ActorProgressDTO actorDTO = actorConverter.convertToDTO(actor);

				List<UserStory> stories = storyService.getActorStories(actor.getId());

				for (UserStory story : stories) {
					UserStoryProgressDTO storyDTO = storyProgressDTOConverter.convertToDTO(story);

					List<Feature> features = featureService.findByStory(story.getId());

					for (Feature feature : features) {

						FeatureProgressDTO featureDTO = featureDTOConverter.convertToDTO(feature);

						//if (feature.getTasks().size() > 0) 
						{

							int doneTask = 0;
							float jhTotal = 0;
							float jhBilled = 0;

							for (Task task : feature.getTasks()) {

								TaskProgressDTO taskDTO = inspectTask(task);

								if (taskDTO.getPourcentTickets()!=null && taskDTO.getPourcentTickets() >= 100) {

									doneTask++;

								}

								featureDTO.getTaskDTOs().add(taskDTO);

								jhTotal += taskDTO.getTotalJHConsumme();
								jhBilled += taskDTO.getFacturableJHConsomme();

							}

							featureDTO.setPourcentTickets((float) doneTask / (float) feature.getTasks().size() * 100f);
							featureDTO.setFacturableJHConsomme(jhBilled);
							featureDTO.setTotalJHConsumme(jhTotal);
						}

						storyDTO.getFeatures().add(featureDTO);
					}

					// TODO calculate Stroy Progress Here

					actorDTO.getStories().add(storyDTO);
				}

				// TODO calculate actor progress here

				ans.getActors().add(actorDTO);

			}

			// TODO Calculate project here

		}

		return ans;
	}

	private TaskProgressDTO inspectTask(Task task) {
		TaskProgressDTO taskDTO = taskDtoConverter.convertToDTO(task);

		if (task.getCloseDate() != null) {
			taskDTO.setPourcentTickets(100f);
		}
		else {
			taskDTO.setPourcentTickets(0f);
		}

		taskDTO.setTotalJHConsumme(logService.getSecondsConsumed(task) / (3600 * 8));

		if (task.getIsBillable() != null && task.getIsBillable()) {
			taskDTO.setFacturableJHConsomme(logService.getSecondsConsumed(task) / (3600 * 8));
		} else {
			taskDTO.setFacturableJHConsomme(0f);
		}

		return taskDTO;

	}

}
