package dashboard.constraints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dashboard.data.dto.MileStoneDTO;
import dashboard.data.entities.MileStone;
import dashboard.data.entities.Project;
import dashboard.data.repositories.ProjectRepository;
import platform.constraints.AbstractConverter;

@Component
public class MileStoneDTOConverter extends AbstractConverter<MileStone, MileStoneDTO> {

	@Autowired
	ProjectRepository projectRepo;

	@Override
	public MileStoneDTO convertToDTO(MileStone entity) {
		MileStoneDTO ans = new MileStoneDTO();

		fieldsFromEntity(entity, ans);
		if (entity.getProject() != null) {
			ans.setProjectID(entity.getProject().getId());
		}

		return ans;
	}

	@Override
	public MileStone convertFormDTO(MileStoneDTO dto) {

		MileStone stone = new MileStone();

		fieldsFromDTO(dto, stone);

		if (dto.getProjectID() != null) {

			Project p = projectRepo.findById(dto.getProjectID()).get();

			if (p != null) {
				stone.setProject(p);
			}

		}

		return stone;
	}

}
