package dashboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dashboard.constraints.FeatureDTOConverter;
import dashboard.constraints.TaskDTOConverter;
import dashboard.data.dto.FeatureDTO;
import dashboard.data.dto.TaskDTO;
import dashboard.data.entities.Actor;
import dashboard.data.entities.Feature;
import dashboard.data.entities.Project;
import dashboard.data.entities.Task;
import dashboard.data.entities.UserStory;
import dashboard.data.repositories.ActorRepository;
import dashboard.data.repositories.FeatureRepository;
import dashboard.data.repositories.ProjectRepository;
import dashboard.data.repositories.TaskRepisitory;
import dashboard.data.repositories.UserStoryRepository;

@Service
public class FeatureService {

//	@Autowired
//	private ProjectRepository projectRepo ;
	
	@Autowired
	private ProjectService projectService ;

	
	@Autowired ActorRepository actorRepo;
	
	@Autowired
	private UserStoryRepository storyRepo;

	@Autowired
	private FeatureRepository featureRepo;

	@Autowired
	private FeatureDTOConverter featureDTOConverter;

	public Feature addFeatureToStory(FeatureDTO feature, Integer storyID) {

		UserStory story = storyRepo.findById(storyID).get();

		if (story == null) {
			return null;
		}

		Feature ans = featureDTOConverter.convertFormDTO(feature);
		ans.setStory(story);

		return featureRepo.save(ans);
	}

	public List<Feature> findByStory(Integer storyID) {

		UserStory story = storyRepo.findById(storyID).get();
		if (story == null)
			return null;

		return featureRepo.findByStory(story);
	}
	
	public List<Feature> findByProject(Integer idProject){
		List<Feature> ans = new ArrayList<>() ;
		
		Project prj = projectService.findById(idProject);
		
		if(prj != null) {
			
			List<Actor> actors = actorRepo.findAllByProject(prj);
			
			for(Actor act : actors) {
				
				List<UserStory> stories =  storyRepo.findByActor(act) ;
				
				for(UserStory str : stories) {
					List<Feature> features = featureRepo.findByStory(str);
					
					ans.addAll(features);
				}
				
				
			}
			
			
		}
		
		
		return ans ;
	}

	public Feature adoptFeture(Integer featureID, Integer parentID) {

		Feature child = featureRepo.findById(featureID).get();
		Feature parent = featureRepo.findById(parentID).get();

		if (child == null || parent == null)
			return null;

		child.setParent(parent);

		return featureRepo.save(child);
	}

	@Deprecated
	@Autowired
	TaskRepisitory taskRepisitory ;
	
	@Autowired
	TaskDTOConverter taskConverter ;
	
	
	
	public TaskDTO addTaskToFeature(Integer featureID, TaskDTO taskDTO) {
		
		Feature feat = featureRepo.findById(featureID).get() ;
		
		if(feat != null) {
			Task tsk = null ;
			if(taskDTO.getId() != null) {
				tsk = taskRepisitory.findById(taskDTO.getId()).get()  ;
				
				if(tsk == null) {
					tsk = taskRepisitory.save( taskConverter.convertFormDTO(taskDTO));
				}
				
			}
			else {
				tsk = taskRepisitory.save( taskConverter.convertFormDTO(taskDTO));
			}
			
			if(tsk != null) {
				feat.getTasks().add(tsk);
				Feature savedFeat = featureRepo.save(feat) ; 
				return taskConverter.convertToDTO(tsk) ;
			}
			
			
			
		}
		
		
		return null;
	}

	public FeatureDTO addFeatureToProject(Integer projectID, FeatureDTO featureDTO) {
		
		Project prj = projectService.findById(projectID);
		
		if(prj != null) {
			
			Feature feat = featureDTOConverter.convertFormDTO(featureDTO) ;
			
			feat.setProject(prj);
			
			featureRepo.save(feat);
			
			return featureDTOConverter.convertToDTO(feat);
		}
		
		return null;
	}
	
	

}
