package dashboard.controllers;

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

import dashboard.data.dto.FeatureDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Feature;
import dashboard.data.entities.UserStory;
import dashboard.services.ActorService;
import dashboard.services.FeatureService;
import dashboard.services.UserStoryService;

@RestController
@RequestMapping(path = "/backlog")
public class BacklogController {

	@Autowired
	private ActorService actorService;

	@Autowired
	private UserStoryService storyService;

	@Autowired
	private FeatureService featureService;

	@PostMapping(path = "/create-actor/{project}")
	public ResponseEntity<Actor> createActor(@RequestBody Actor actor,
			@PathVariable(name = "project") Integer projectID) {

		Actor ans = actorService.addActorToProject(actor, projectID);

		if (ans != null) {
			return new ResponseEntity<Actor>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/create-story/{actor}")
	public ResponseEntity<UserStory> createStory(@RequestBody UserStory story,
			@PathVariable(name = "actor") Integer actorID) {
		UserStory ans = storyService.addStoryToActor(story, actorID);

		if (ans != null) {
			return new ResponseEntity<UserStory>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserStory>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/create-feature/{story}")
	public ResponseEntity<Feature> createFeature(@RequestBody FeatureDTO feature,
			@PathVariable(name = "story") Integer storyID) {
		Feature ans = featureService.addFeatureToStory(feature, storyID);

		if (ans != null) {
			return new ResponseEntity<Feature>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<Feature>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/actors/{project}")
	public ResponseEntity<List<Actor>> getactors(@PathVariable(name = "project") Integer projectID) {
		List<Actor> ans = actorService.getActorsInProject(projectID);

		if (ans != null) {
			return new ResponseEntity<List<Actor>>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Actor>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/actors-stories/{actor}")
	public ResponseEntity<List<UserStory>> getActorStories(@PathVariable(name = "actor") Integer actorID) {
		List<UserStory> ans = storyService.getActorStories(actorID);

		if (ans != null) {
			return new ResponseEntity<List<UserStory>>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserStory>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/features-stories/{story}")
	public ResponseEntity<List<Feature>> getStoriesFeatures(@PathVariable(name = "story") Integer storyID) {
		List<Feature> ans = featureService.findByStory(storyID);

		if (ans != null) {
			return new ResponseEntity<List<Feature>>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Feature>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/set-feature-parent/{feature}/{parent}")
	public ResponseEntity<Feature> adoptFeature(@PathVariable(name = "feature") Integer featureID,
			@PathVariable(name = "parent") Integer parentID) {
		
		Feature ans = featureService.adoptFeture(featureID, parentID);

		if (ans != null) {
			return new ResponseEntity<Feature>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<Feature>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/features/{projectID}")
	public ResponseEntity<List<Feature>> featuresInProject(@PathVariable Integer projectID){
		
		List<Feature> ans = featureService.findByProject(projectID);

		if (ans != null) {
			return new ResponseEntity<List<Feature>>(ans, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Feature>>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
