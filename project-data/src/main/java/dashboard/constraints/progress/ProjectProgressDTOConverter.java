package dashboard.constraints.progress;

import org.springframework.stereotype.Component;

import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.data.entities.Project;
import platform.constraints.AbstractConverter;

@Component
public class ProjectProgressDTOConverter extends AbstractConverter<Project, ProjectProgressDTO> {

	@Override
	public ProjectProgressDTO convertToDTO(Project entity) {
		
		ProjectProgressDTO ans = new ProjectProgressDTO() ;
		fieldsFromEntity(entity, ans);
		ans.setClientName(entity.getClient() != null ? entity.getClient().getCompanyName() : "");
		
		
		return ans;
	}

	@Override
	public Project convertFormDTO(ProjectProgressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
