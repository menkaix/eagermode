package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Project;
import dashboard.data.entities.Task;

public interface TaskRepisitory  extends PagingAndSortingRepository<Task, Integer> {

	public List<Task> findAllByProject(Project project);
	
	public List<Task> findByTitle(String title);
	

}
