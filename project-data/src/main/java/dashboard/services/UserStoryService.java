package dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.data.entities.Actor;
import dashboard.data.entities.UserStory;
import dashboard.data.repositories.ActorRepository;
import dashboard.data.repositories.UserStoryRepository;

@Service
public class UserStoryService {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private UserStoryRepository storyRepository;

	public UserStory addStoryToActor(UserStory story, Integer actorID) {

		Actor actor = actorRepository.findById(actorID).get();

		if (actor == null)
			return null;

		story.setActor(actor);

		return storyRepository.save(story);

	}
	
	public List<UserStory> getActorStories( Integer actorID){
		
		Actor actor = actorRepository.findById(actorID).get();
		
		if (actor == null)
			return null;
		
		List<UserStory> ans  =  storyRepository.findByActor(actor) ;
		
		return ans ;
		
	}

}
