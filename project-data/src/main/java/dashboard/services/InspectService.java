package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Project;

@Service
public class InspectService {

	@Autowired
	ProjectService projectService ;
	
	@Autowired 
	private ActorService actorService ;
	
	public ProjectProgressDTO inspectProject(Integer projectID) {
		
		Project project = projectService.findById(projectID) ;
		
		if(project != null) {
			
			List<Actor> actors = actorService.getActorsInProject(projectID) ;
			
			
			
		}
				
		return null;
	}

}
