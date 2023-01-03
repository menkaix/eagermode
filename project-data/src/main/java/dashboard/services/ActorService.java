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
	
	@Autowired
	private ProjectRepository projectRepository ;
	
	public Actor addActorToProject(Actor actor, Integer projectID) {
		
		Project project = projectRepository.findById(projectID).get();
		
		if(project == null) return null ;
		
		actor.setProject(project);
		
		return actorRepository.save(actor) ;
		
	}
	
	public List<Actor> getActorsInProject(Integer projectID){
		
		Project project = projectRepository.findById(projectID).get();
		
		return actorRepository.findAllByProject(project) ;
		
	}

}
