package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.progress.ActorProgressDTOConverter;
import dashboard.constraints.progress.ProjectProgressDTOConverter;
import dashboard.data.dto.progress.ActorProgressDTO;
import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Project;

@Service
public class InspectService {

	@Autowired
	ProjectService projectService ;
	
	@Autowired
	ProjectProgressDTOConverter dtoConverter ;
	
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
				
				
				
				//TODO calculate actor progress here
				
				
				ans.getActors().add(actorDTO) ;
			}
			
			//TODO Calculate project here
			
			
		}
				
		return ans;
	}

}
