package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.FeatureType;

public interface FeatureTypeRepository extends PagingAndSortingRepository<FeatureType, Integer> {

	public List<FeatureType> findByTypeName(String typeName);
	
	
	
}
