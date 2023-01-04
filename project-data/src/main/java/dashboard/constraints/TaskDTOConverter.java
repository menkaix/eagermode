package dashboard.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dashboard.data.dto.TaskDTO;
import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.repositories.ProjectRepository;
import platform.constraints.AbstractConverter;

@Component
public class TaskDTOConverter extends AbstractConverter<Task, TaskDTO>{

	@Autowired
	ProjectRepository projectRepo ;
	
	@Override
	public TaskDTO convertToDTO(Task entity) {
		
		TaskDTO ans = new TaskDTO();
		
		ans.setTitle(entity.getTitle());
		ans.setDescription(entity.getDescription());		
		ans.setCloseComment(entity.getCloseComment());
		ans.setIsHot(entity.getIsHot());
		
		ans.setCode(entity.getCode());
		
		ans.setCloseDate(entity.getCloseDate()!=null ? (new SimpleDateFormat("dd/MM/yyyy").format(entity.getCloseDate())):"");
		ans.setCreationDate((new SimpleDateFormat("dd/MM/yyyy").format(entity.getCreationDate())));
		ans.setDueDate(entity.getDueDate()!=null ? (new SimpleDateFormat("dd/MM/yyyy").format(entity.getDueDate())):"");
		
		ans.setProjectID(entity.getProject().getId());
		ans.setProjectName(entity.getProject().getProjectName());
		
		if(entity.getId()!=null) ans.setId(entity.getId());
		
		return ans;
	}

	@Override
	public Task convertFormDTO(TaskDTO dto) {
		Task t = new Task() ;
		
		t.setTitle(dto.getTitle());
		t.setDescription(dto.getDescription());
		t.setCloseComment(dto.getCloseComment());
		t.setIsHot(dto.getIsHot());
		t.setCode(dto.getCode());
		
		if(dto.getProjectID()!=null) {
			Project prj = projectRepo.findById(dto.getProjectID()).get() ;
			t.setProject(prj);
		}
		
		try {
			if(dto.getCreationDate()!= null)
				t.setCreationDate((new SimpleDateFormat("dd/MM/yyyy").parse(dto.getCreationDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			if(dto.getCloseDate()!= null)
				t.setCloseDate((new SimpleDateFormat("dd/MM/yyyy").parse(dto.getCloseDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			if(dto.getDueDate()!= null)
				t.setDueDate((new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDueDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return t;
	}

}
