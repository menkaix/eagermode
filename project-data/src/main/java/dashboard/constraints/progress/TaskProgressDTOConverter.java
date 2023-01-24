package dashboard.constraints.progress;

import org.springframework.stereotype.Component;

import dashboard.data.dto.progress.TaskProgressDTO;
import dashboard.data.entities.Task;
import platform.constraints.AbstractConverter;

@Component
public class TaskProgressDTOConverter extends AbstractConverter<Task, TaskProgressDTO>{

	@Override
	public TaskProgressDTO convertToDTO(Task entity) {
		
		TaskProgressDTO ans = new TaskProgressDTO() ;
		fieldsFromEntity(entity, ans);
		return ans;
	}

	@Override
	public Task convertFormDTO(TaskProgressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
