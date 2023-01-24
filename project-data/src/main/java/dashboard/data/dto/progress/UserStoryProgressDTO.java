package dashboard.data.dto.progress;

import java.util.ArrayList;
import java.util.List;

public class UserStoryProgressDTO {

	private Integer id;
	private String action;
	private String scenario;
	private String objectif;

	private Float facturableJHConsomme;
	private Float totalJHConsumme;
	private Float pourcentTickets;

	private Integer doneTaskNumber = 0;
	private Integer taskNumber = 0;

	private List<FeatureProgressDTO> features = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

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

	public List<FeatureProgressDTO> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureProgressDTO> features) {
		this.features = features;
	}

	public Integer getDoneTaskNumber() {
		return doneTaskNumber;
	}

	public void setDoneTaskNumber(Integer doneTaskNumber) {
		this.doneTaskNumber = doneTaskNumber;
	}

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

}
