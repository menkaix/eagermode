package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.People;

public interface PeopleRepository extends PagingAndSortingRepository<People, Integer> {
	
	public List<People> findByLastName(String lastName) ;
	public List<People> findByEmail(String email);
}
