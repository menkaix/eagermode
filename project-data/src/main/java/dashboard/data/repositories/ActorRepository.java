package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Actor;
import dashboard.data.entities.Project;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Integer> {
	
	List<Actor> findAllByProject(Project p) ;
	

}
