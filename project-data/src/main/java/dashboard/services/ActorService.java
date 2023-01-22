package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.Actor;
import dashboard.data.entities.Project;
import dashboard.data.repositories.ActorRepository;
import dashboard.data.repositories.ProjectRepository;

@Service
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository ;
	
//	@Autowired
//	private ProjectRepository projectRepository ;
	
	@Autowired
	private ProjectService projectService ;

	
	public Actor addActorToProject(Actor actor, Integer projectID) {
		
		Project project = projectService.findById(projectID);
		
		if(project == null) return null ;
		
		actor.setProject(project);
		
		return actorRepository.save(actor) ;
		
	}
	
	public List<Actor> getActorsInProject(Integer projectID){
		
		Project project = projectService.findById(projectID);
		
		return actorRepository.findAllByProject(project) ;
		
	}

}
