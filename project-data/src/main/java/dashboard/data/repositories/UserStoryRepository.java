package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Actor;
import dashboard.data.entities.UserStory;

public interface UserStoryRepository extends PagingAndSortingRepository<UserStory, Integer> {
	
	List<UserStory> findByActor(Actor actor);

}
