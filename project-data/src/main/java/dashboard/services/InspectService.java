package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.progress.ActorProgressDTOConverter;
import dashboard.constraints.progress.ProjectProgressDTOConverter;
import dashboard.constraints.progress.StoryProgressDTOConverter;
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
	ProjectService projectService ;
	
	@Autowired
	UserStoryService userStoryService ;
	
	@Autowired
	ProjectProgressDTOConverter dtoConverter ;
	
	@Autowired
	StoryProgressDTOConverter storyDtoConverter ;
	
	@Autowired
	FeatureService featureService ;
	
	@Autowired 
	private ActorService actorService ;
	
	@Autowired
	private ActorProgressDTOConverter actorConverter ;
	
	public ProjectProgressDTO inspectProject(Integer projectID) {
		
		Project project = projectService.findById(projectID) ;
		ProjectProgressDTO ans = null ;
		
		if(project != null) {
			
			ans = dtoConverter.convertToDTO(project) ;
			
			List<Actor> actors = actorService.getActorsInProject(projectID) ;
			
			for(Actor actor : actors) {
				
				ActorProgressDTO actorDTO = actorConverter.convertToDTO(actor);
				
				List<UserStory> stories = userStoryService.getActorStories(actor.getId());
				
				for(UserStory story :  stories) {
					UserStoryProgressDTO storyDTO = storyDtoConverter.convertToDTO(story) ;
					
					List<Feature> features = featureService.findByStory(story.getId());
					
					for(Feature feature :  features ) {
						
						//FeatureProgressDTO featureDTO = 
						
						
					}
					
					
					//TODO calculate Stroy Progress Here
					
					actorDTO.getStories().add(storyDTO);
				}
				
				
				
				//TODO calculate actor progress here
				
				
				ans.getActors().add(actorDTO) ;
			}
			
			//TODO Calculate project here
			
			
		}
				
		return ans;
	}

}
