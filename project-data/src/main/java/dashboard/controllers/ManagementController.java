package dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dashboard.data.dto.ProjectMinimumDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.People;
import dashboard.data.entities.Project;
import dashboard.data.entities.ProjectGroup;
import dashboard.services.ActorService;
import dashboard.services.GroupService;
import dashboard.services.ProjectService;

@RestController
@RequestMapping(path = "/management")
public class ManagementController {
	
	@Autowired
	private ProjectService projectService ;
	
	@Autowired
	private GroupService groupService ;
	
	@PostMapping(path = "/create-project")
	public ResponseEntity<Project> createProject(@RequestBody ProjectMinimumDTO projectDTO){
		
		Project ans = projectService.createFromRequest(projectDTO);
		
		return new ResponseEntity<Project>(ans, HttpStatus.OK) ;
	}
	
	@PostMapping(path="/create-group/{id}")
	public ResponseEntity<ProjectGroup> createGroup(@PathVariable(name="id")Integer projectID, @RequestBody ProjectGroup group){
		
		ProjectGroup groupAns = groupService.createGroupInProject(projectID, group);
		
		if(groupAns != null) {
			return new ResponseEntity<ProjectGroup>(groupAns, HttpStatus.OK) ;
		}
		else {
			return new ResponseEntity<ProjectGroup>(HttpStatus.BAD_REQUEST) ;
		}
		
		
	}
	
	@PostMapping(path="/populate-group/{groupID}")
	public ResponseEntity<ProjectGroup> createGroup(@PathVariable(name="groupID")Integer groupID, @RequestBody People group){
		
		ProjectGroup groupAns = groupService.addPeopleToGroup(groupID, group);
		
		if(groupAns != null) {
			return new ResponseEntity<ProjectGroup>(groupAns, HttpStatus.OK) ;
		}
		else {
			return new ResponseEntity<ProjectGroup>(HttpStatus.BAD_REQUEST) ;
		}
		
		
	}
	
	
	
	

}