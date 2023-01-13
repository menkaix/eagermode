package dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.MileStoneDTOConverter;
import dashboard.data.dto.MileStoneDTO;
import dashboard.data.entities.MileStone;
import dashboard.data.entities.Project;
import dashboard.data.repositories.MileStoneRepository;
import dashboard.data.repositories.ProjectRepository;

@Service
public class MileStoneService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	MileStoneRepository mileStoneRepository;

	@Autowired
	MileStoneDTOConverter mileStoneDTOConverter;

	public MileStoneDTO createForProject(Integer projectID, MileStoneDTO dto) {

		Project prj = projectRepository.findById(projectID).get();

		if (prj != null) {
			MileStone milestone = mileStoneDTOConverter.convertFormDTO(dto);
			milestone.setProject(prj);

			return mileStoneDTOConverter.convertToDTO(mileStoneRepository.save(milestone));
		}

		return null;
	}

}
