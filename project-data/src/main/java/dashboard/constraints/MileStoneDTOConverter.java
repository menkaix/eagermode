package dashboard.constraints;

import org.springframework.stereotype.Component;

import dashboard.data.dto.MileStoneDTO;
import dashboard.data.entities.MileStone;
import platform.constraints.AbstractConverter;

@Component
public class MileStoneDTOConverter  extends AbstractConverter<MileStone, MileStoneDTO>{

	@Override
	public MileStoneDTO convertToDTO(MileStone entity) {
		MileStoneDTO ans = new MileStoneDTO() ;
		
		fieldsFromEntity(entity, ans);
		
		return ans;
	}

	@Override
	public MileStone convertFormDTO(MileStoneDTO dto) {
		
		MileStone stone = new MileStone() ;
		
		fieldsFromDTO(dto, stone) ;
		
		return stone;
	}

}
