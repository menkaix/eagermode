package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.People;
import dashboard.data.entities.Project;
import dashboard.data.entities.ProjectGroup;
import dashboard.data.repositories.PeopleRepository;
import dashboard.data.repositories.ProjectGroupRepository;
import dashboard.data.repositories.ProjectRepository;

@Service
public class GroupService {

	@Autowired
	private ProjectGroupRepository groupRepo;
	
	@Autowired
	private ProjectRepository projectRepo ;
	
	@Autowired
	private PeopleRepository peopleRepository ;
	
	public ProjectGroup createGroupInProject(Integer projectID, ProjectGroup group) {
		
		Project prj = projectRepo.findById(projectID).get() ;
		
		if(prj != null) {
			
			ProjectGroup gp = group ;
			
			gp.setProject(prj);
			
			return groupRepo.save(gp) ;
			
		}else {
			return null ;
		}
		
	}
	
	public ProjectGroup addPeopleToGroup(Integer groupID, People peron) {
		
		ProjectGroup gp = groupRepo.findById(groupID).get() ;
		
		if(gp != null) {
			
			List<People> ppl = peopleRepository.findByEmail(peron.getEmail()) ;
			
			if(ppl.size() < 1) {
				peopleRepository.save(peron) ;
				gp.getMembers().add(peron) ;
			}
			else {
				gp.getMembers().add(ppl.get(0)) ;
			}
			
			
			return  groupRepo.save(gp) ;
			
		}else {
			return null ;
		}
		
	}
	
	
}
