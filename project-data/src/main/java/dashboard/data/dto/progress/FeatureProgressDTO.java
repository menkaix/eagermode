package dashboard.data.dto.progress;

import java.util.ArrayList;
import java.util.List;

import dashboard.data.dto.TaskDTO;
import dashboard.data.entities.FeatureType;

public class FeatureProgressDTO {
	
	private Integer id;	
	private String featureName ;	
	private String type ;	
	private String featureDescription ;
	private Float estimation ;
	
	
	private Float facturableJHConsomme ;	
	private Float totalJHConsumme ;	
	private Float pourcentTickets ;
	
	private List<TaskProgressDTO> taskDTOs = new ArrayList<TaskProgressDTO>() ;
	
	public Float getFacturableJHConsomme() {
		return facturableJHConsomme;
	}
	public void setFacturableJHConsomme(Float facturableJHConsomme) {
		this.facturableJHConsomme = facturableJHConsomme;
	}
	public Float getTotalJHConsumme() {
		return totalJHConsumme;
	}
	public void setTotalJHConsumme(Float totalJHConsumme) {
		this.totalJHConsumme = totalJHConsumme;
	}
	public Float getPourcentTickets() {
		return pourcentTickets;
	}
	public void setPourcentTickets(Float pourcentTickets) {
		this.pourcentTickets = pourcentTickets;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<TaskProgressDTO> getTaskDTOs() {
		return taskDTOs;
	}
	public void setTaskDTOs(List<TaskProgressDTO> taskDTOs) {
		this.taskDTOs = taskDTOs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
