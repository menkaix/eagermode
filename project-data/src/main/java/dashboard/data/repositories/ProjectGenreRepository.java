package dashboard.data.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.ProjectGenre;

public interface ProjectGenreRepository extends PagingAndSortingRepository<ProjectGenre, Integer> {
	
	public List<ProjectGenre> findByGenreName (String genreName) ;

}
