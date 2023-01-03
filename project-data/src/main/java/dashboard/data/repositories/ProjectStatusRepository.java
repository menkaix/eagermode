package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.ProjectStatus;

public interface ProjectStatusRepository extends PagingAndSortingRepository<ProjectStatus, Integer> {
	
	public List<ProjectStatus> findByStatusName(String statusName) ;

}
