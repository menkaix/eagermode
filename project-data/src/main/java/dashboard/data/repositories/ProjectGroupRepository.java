package dashboard.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.ProjectGroup;

public interface ProjectGroupRepository extends PagingAndSortingRepository<ProjectGroup, Integer>{
	
	public Optional<ProjectGroup> findByGroupName(String name) ;

}
