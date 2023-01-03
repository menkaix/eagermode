package dashboard.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dashboard.data.entities.Note;
import dashboard.data.entities.Project;

public interface NoteRepository  extends PagingAndSortingRepository<Note, Integer> {

	public Note[] findAllByProject(Project project);
	

}
