package dashboard.constraints.progress;

import dashboard.data.dto.progress.FeatureProgressDTO;
import dashboard.data.entities.Feature;
import platform.constraints.AbstractConverter;

public class FeatureProgressDTOConverter  extends AbstractConverter<Feature, FeatureProgressDTO> {

	@Override
	public FeatureProgressDTO convertToDTO(Feature entity) {
		
		FeatureProgressDTO ans = new FeatureProgressDTO() ;
		
		fieldsFromEntity(entity, ans);
		
		return ans;
	}

	@Override
	public Feature convertFormDTO(FeatureProgressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
