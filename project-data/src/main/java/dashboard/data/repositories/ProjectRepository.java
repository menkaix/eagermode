package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Company;
import dashboard.data.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
	
	public List<Project> findByProjectName(String name);
	public List<Project> findByClient(Company client) ;
	public Iterable<Project> findAll(Sort by);
	public List<Project> findByProjectCode(String projectCode);
}

