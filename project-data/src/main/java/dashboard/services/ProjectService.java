package dashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dashboard.constraints.ProjectMinimalConverter;
import dashboard.data.dto.ProjectMinimumDTO;
import dashboard.data.entities.Project;
import dashboard.data.repositories.ProjectRepository;
import dashboard.data.values.LifeCycle;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectMinimalConverter converter ;

	public List<Project> allProjects() {
		ArrayList<Project> ans = new ArrayList<Project>();

		for (Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			ans.add(prj);
		}

		return ans;
	}

	public Project createFromRequest(ProjectMinimumDTO req) {

		Project project = converter.convertFormDTO(req);

		project.setLastActivity(new Date());
		
		return projectRepository.save(project);
	}
	
	public List<Project> getFromCode(String projectCode) {
		return projectRepository.findByProjectCode(projectCode);
	}
	
	public void save(Project prj) {
		prj.setLastActivity(new Date());
		projectRepository.save(prj);
	}
	
	public Project getFromProjectID(Integer id) {

		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		return project;

	}

	public boolean delete(Integer id) {

		try {
			Project project = projectRepository.findById(id).get();
			projectRepository.delete(project);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public List<Project> getActives(){
		ArrayList<Project> ans = new ArrayList<Project>() ;
		
		for(Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			if(prj.getStatus()!=null && prj.getStatus().getCycle()== LifeCycle.ACTIF) {
				ans.add(prj);
			}
		}
		
		return ans ;
	}
	
	public List<Project> getPrepa(){
		ArrayList<Project> ans = new ArrayList<Project>() ;

		for(Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			if(prj.getStatus()!=null && prj.getStatus().getCycle()== LifeCycle.PREPARATION) {
				ans.add(prj);
			}
		}
		
		return ans ;
	}
	
	public List<Project> getStandBy(){
		ArrayList<Project> ans = new ArrayList<Project>() ;
		

		for(Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			if(prj.getStatus()!=null && prj.getStatus().getCycle()== LifeCycle.STAND_BY) {
				ans.add(prj);
			}
		}
		
		return ans ;
	}
	
	public List<Project> getBackGround(){
		ArrayList<Project> ans = new ArrayList<Project>() ;
		

		for(Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			if(prj.getStatus()!=null && prj.getStatus().getCycle()== LifeCycle.BACK_GROUND) {
				ans.add(prj);
			}
		}
		
		return ans ;
	}
	
	public List<Project> getEnded(){
		ArrayList<Project> ans = new ArrayList<Project>() ;
		

		for(Project prj : projectRepository.findAll(Sort.by(Sort.Direction.DESC, "lastActivity"))) {
			if(prj.getStatus()!=null && prj.getStatus().getCycle()== LifeCycle.CLOSED) {
				ans.add(prj);
			}
		}
		
		return ans ;
	}

}
