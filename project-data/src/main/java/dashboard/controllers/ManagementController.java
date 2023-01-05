package dashboard.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dashboard.data.dto.ProjectMinimumDTO;
import dashboard.data.dto.TaskDTO;
import dashboard.data.entities.People;
import dashboard.data.entities.Project;
import dashboard.data.entities.ProjectGroup;
import dashboard.services.FeatureService;
import dashboard.services.GroupService;
import dashboard.services.ProjectService;
import dashboard.services.TaskService;

@RestController
@RequestMapping(path = "/management")
public class ManagementController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private GroupService groupService;

	@PostMapping(path = "/create-project")
	public ResponseEntity<Project> createProject(@RequestBody ProjectMinimumDTO projectDTO) {

		Project ans = projectService.createFromRequest(projectDTO);

		return new ResponseEntity<Project>(ans, HttpStatus.OK);
	}

	@PostMapping(path = "/create-group/{id}")
	public ResponseEntity<ProjectGroup> createGroup(@PathVariable(name = "id") Integer projectID,
			@RequestBody ProjectGroup group) {

		ProjectGroup groupAns = groupService.createGroupInProject(projectID, group);

		if (groupAns != null) {
			return new ResponseEntity<ProjectGroup>(groupAns, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProjectGroup>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/populate-group/{groupID}")
	public ResponseEntity<ProjectGroup> createGroup(@PathVariable(name = "groupID") Integer groupID,
			@RequestBody People group) {

		ProjectGroup groupAns = groupService.addPeopleToGroup(groupID, group);

		if (groupAns != null) {
			return new ResponseEntity<ProjectGroup>(groupAns, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProjectGroup>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/add-task-in-project/{projectID}")
	public ResponseEntity<TaskDTO> addTaskInProject(@PathVariable(name = "projectID") Integer projectID,
			@RequestBody TaskDTO taskDTO) {

		TaskDTO ans = taskService.addTaskToProject(projectID, taskDTO);

		if (ans != null) {
			return new ResponseEntity<TaskDTO>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<TaskDTO>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@Autowired
	FeatureService featureService ;
	
	@PostMapping(path = "/add-task-in-feature/{featureID}")
	public ResponseEntity<TaskDTO> addTaskInFeature(@PathVariable(name = "featureID") Integer featureID,
			@RequestBody TaskDTO taskDTO) {

		TaskDTO ans = featureService.addTaskToFeature(featureID, taskDTO);

		if (ans != null) {
			return new ResponseEntity<TaskDTO>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<TaskDTO>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/add-bulk-task-in-project/{projectID}")
	public ResponseEntity<List<TaskDTO>> addBulkTaskInProject(@PathVariable(name = "projectID") Integer projectID,
			@RequestBody List<TaskDTO> taskDTO) {

		ArrayList<TaskDTO> ans = new ArrayList<TaskDTO>();
		for (TaskDTO dto : taskDTO) {
			try {
				TaskDTO t =taskService.addTaskToProject(projectID, dto);
				if(t!=null) {
					ans.add(t) ;
				}
			}
			catch(Exception e) {
				System.out.println(dto.getCode()+" : "+ e.getClass().getSimpleName());
			}
			
		}

		return new ResponseEntity<List<TaskDTO>>(ans, HttpStatus.OK);

	}
	
	@GetMapping(path="/get-task-in-project/{projectID}")
	public ResponseEntity<List<TaskDTO>> addBulkTaskInProject(@PathVariable(name = "projectID") Integer projectID){
		
		List<TaskDTO> ans = taskService.getAllByProjectID(projectID) ;
		
		if (ans != null) {
			return new ResponseEntity<List<TaskDTO>>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TaskDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
