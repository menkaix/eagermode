package dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dashboard.data.dto.MileStoneDTO;
import dashboard.services.MileStoneService;

@RestController
@RequestMapping(path = "/plannification")
public class PlanificationController {

	@Autowired
	private MileStoneService mileStoneService ;
	
	@PostMapping(path = "/add-milestone/{projectID}")
	public ResponseEntity<MileStoneDTO> addMilestone(@PathVariable Integer projectID, @RequestBody MileStoneDTO dto){
		
		MileStoneDTO groupAns = mileStoneService.createForProject(projectID, dto) ;

		if (groupAns != null) {
			return new ResponseEntity<MileStoneDTO>(groupAns, HttpStatus.OK);
		} else {
			return new ResponseEntity<MileStoneDTO>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
}
