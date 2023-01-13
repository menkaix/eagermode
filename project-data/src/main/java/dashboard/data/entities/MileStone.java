package dashboard.data.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MileStone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String mileStoneName ;
	private String description ;
	private Date estimStartDate ;
	private Date estimEndDate ;
	private Date startDate ;
	private Date endDate ;
	
	private boolean isActive ;
	
	@ManyToOne
	private Project project ;

	@OneToMany
	private List<Feature> features ;
	@OneToMany
	private List<Task> tasks ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public String getMileStoneName() {
		return mileStoneName;
	}
	public void setMileStoneName(String mileStoneName) {
		this.mileStoneName = mileStoneName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEstimStartDate() {
		return estimStartDate;
	}
	public void setEstimStartDate(Date estimStartDate) {
		this.estimStartDate = estimStartDate;
	}
	public Date getEstimEndDate() {
		return estimEndDate;
	}
	public void setEstimEndDate(Date estimEndDate) {
		this.estimEndDate = estimEndDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	
	
	
}
