package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Feature;
import dashboard.data.entities.UserStory;

public interface FeatureRepository  extends PagingAndSortingRepository<Feature, Integer> {
	
	List<Feature> findByStory(UserStory story);

}
