package dashboard.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Feature {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String featureName ;

	@ManyToOne
	private FeatureType featureType ;
	
	private String featureDescription ;

	@ManyToOne
	private Feature parent ;
	
	@ManyToOne
	private UserStory story ;
	
	
	private Float estimation ;
	
	@OneToMany
	private List<Task> tasks ;



	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

	public Feature getParent() {
		return parent;
	}

	public void setParent(Feature parent) {
		this.parent = parent;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getFeatureDescription() {
		return featureDescription;
	}

	public void setFeatureDescription(String featureDescription) {
		this.featureDescription = featureDescription;
	}

	public Float getEstimation() {
		return estimation;
	}

	public void setEstimation(Float estimation) {
		this.estimation = estimation;
	}

	public UserStory getStory() {
		return story;
	}

	public void setStory(UserStory story) {
		this.story = story;
	}

	
}
