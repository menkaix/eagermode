package dashboard.constraints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dashboard.data.dto.FeatureDTO;
import dashboard.data.entities.Feature;
import dashboard.data.entities.FeatureType;
import dashboard.data.repositories.FeatureRepository;
import dashboard.data.repositories.FeatureTypeRepository;
import platform.constraints.AbstractConverter;

@Component
public class FeatureDTOConverter extends AbstractConverter<Feature, FeatureDTO> {

	@Autowired
	FeatureTypeRepository typeRepo ;
	
	@Autowired
	FeatureRepository featureRepository ;
	
	@Override
	public FeatureDTO convertToDTO(Feature entity) {
		
		FeatureDTO ans = new FeatureDTO() ;
		
		ans.setFeatureName(entity.getFeatureName());
		ans.setDescription(entity.getFeatureDescription());
		ans.setEstimation(entity.getEstimation());
		ans.setFeatureType(entity.getFeatureType().getTypeName());
		
		if(entity.getParent()!=null) {
			ans.setParentID(entity.getParent().getId());
		}
		
		return ans;
	}

	@Override
	public Feature convertFormDTO(FeatureDTO dto) {
		
		List<FeatureType> types = typeRepo.findByTypeName(dto.getFeatureType());
		
		Feature ans = new Feature() ;
			
		ans.setEstimation(dto.getEstimation());
		ans.setFeatureName(dto.getFeatureName());
		ans.setFeatureDescription(dto.getDescription());
		
		if(types.size()>=1) {			
			FeatureType ft = types.get(0);			
			ans.setFeatureType(ft);			
		}
		
		if(dto.getParentID() != null)
		{
			ans.setParent(featureRepository.findById(dto.getParentID()).orElse(null)) ;
		}
		
		return ans ;
	}

}
