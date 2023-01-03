package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Task;
import dashboard.data.entities.TimeLog;

public interface TimeLogRepository extends PagingAndSortingRepository<TimeLog,Integer> {
	
	public List<TimeLog> findByTask(Task task);

}
