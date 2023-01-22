package dashboard.constraints.progress;

import org.springframework.stereotype.Component;

import dashboard.data.dto.progress.ActorProgressDTO;
import dashboard.data.entities.Actor;
import platform.constraints.AbstractConverter;

@Component
public class ActorProgressDTOConverter extends AbstractConverter<Actor, ActorProgressDTO> {

	@Override
	public ActorProgressDTO convertToDTO(Actor entity) {
		
		ActorProgressDTO ans = new ActorProgressDTO() ;
		fieldsFromEntity(entity, ans);
				
		return ans;
	}

	@Override
	public Actor convertFormDTO(ActorProgressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
