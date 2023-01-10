package dashboard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dashboard.data.entities.ProjectGroup;
import dashboard.data.entities.TimeLog;
import dashboard.services.TimeLogService;

@RestController
@RequestMapping(path="/tempo")
public class TempoController {
	
	@Autowired
	TimeLogService logService ;
	
	@GetMapping(path="/journal/{projectID}")
	public ResponseEntity<List<TimeLog>> getJournal (@PathVariable Integer projectID){
		
		List<TimeLog> groupAns = logService.getTimeLogByProjectID(projectID);

		if (groupAns != null) {
			return new ResponseEntity<List<TimeLog>>(groupAns, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TimeLog>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
