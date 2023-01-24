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
import dashboard.data.dto.progress.UserStoryProgressDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Feature;
import dashboard.data.entities.Project;
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
	TaskService taskService ;

	@Autowired
	private ProjectProgressDTOConverter dtoConverter;

	@Autowired
	private FeatureProgressDTOConverter featureDTOConverter;

	@Autowired
	private ActorProgressDTOConverter actorConverter;

	@Autowired
	private StoryProgressDTOConverter storyProgressDTOConverter;
	
	@Autowired
	private TaskProgressDTOConverter taskDtoConverter ;

	public ProjectProgressDTO inspectProject(Integer projectID) {

		Project project = projectService.findById(projectID);
		ProjectProgressDTO ans = null;

		if (project != null) {

			ans = dtoConverter.convertToDTO(project);

			List<Actor> actors = actorService.getActorsInProject(projectID);

			for (Actor actor : actors) {

				ActorProgressDTO actorDTO = actorConverter.convertToDTO(actor);

				List<UserStory> userStories = storyService.getActorStories(actor.getId());

				for (UserStory userStory : userStories) {

					UserStoryProgressDTO storyDTO = storyProgressDTOConverter.convertToDTO(userStory);

					List<Feature> features = featureService.findByStory(userStory.getId());

					for (Feature feature : features) {

						FeatureProgressDTO featureDTO = featureDTOConverter.convertToDTO(feature);
						
						 
						
						//TODO feature progress here
						
						storyDTO.getFeatures().add(featureDTO);
					}

					// TODO story progress Here

					actorDTO.getStories().add(storyDTO);
				}

				// TODO calculate actor progress here

				ans.getActors().add(actorDTO);
			}

			// TODO Calculate project here

		}

		return ans;
	}

}
