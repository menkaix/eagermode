package dashboard.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.Note;
import dashboard.data.entities.Project;
import dashboard.data.repositories.NoteRepository;

@Service
public class NoteService {
	
	
	@Autowired
	private NoteRepository noteRepo ;
	
	public ArrayList<Note> allNotesFormatHTML(Project prj){
		ArrayList<Note> ans = new ArrayList<Note>() ;
		
		for(Note notto : noteRepo.findAllByProject(prj)) {
			
			ans.add(notto) ;
		}
		
		
		return ans ;
	}

}
