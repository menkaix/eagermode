package dashboard.constraints.progress;

import org.springframework.stereotype.Component;

import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.data.dto.progress.UserStoryProgressDTO;
import dashboard.data.entities.Project;
import dashboard.data.entities.UserStory;
import platform.constraints.AbstractConverter;

@Component
public class StoryProgressDTOConverter extends AbstractConverter<UserStory, UserStoryProgressDTO> {

	@Override
	public UserStoryProgressDTO convertToDTO(UserStory entity) {
		
		UserStoryProgressDTO ans = new UserStoryProgressDTO() ;
		fieldsFromEntity(entity, ans);
		
		return ans;
	}

	@Override
	public UserStory convertFormDTO(UserStoryProgressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
