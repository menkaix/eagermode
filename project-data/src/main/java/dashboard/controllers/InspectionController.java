package dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dashboard.data.dto.progress.ProjectProgressDTO;
import dashboard.services.InspectService;

@RequestMapping(path="/inspect")
@RestController
public class InspectionController {

	@Autowired
	private InspectService inspectService ;
	
	@GetMapping(path="/project-progress/{projectID}")
	public ResponseEntity<ProjectProgressDTO> projectProgress(@PathVariable Integer projectID){
		
		
		ProjectProgressDTO groupAns = inspectService.inspectProject(projectID) ;

		if (groupAns != null) {
			return new ResponseEntity<ProjectProgressDTO>(groupAns, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProjectProgressDTO>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
