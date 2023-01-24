package dashboard.data.dto.progress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectProgressDTO {
	
	
private Integer id;
	
	private String projectName ;
	
	private String projectCode ;

	private String description ;

	private String clientName ;
		
	private Date dateCreated ;
	
	private Date deadLine ;
	
	private String refBL ;
	
	private Float budjetJH ;
	
	private Float tjm ;
	
	private Float facturableJHConsomme ;
		
	private Float totalJHConsumme ;
	
	private Float pourcentTickets ;
	
	private Integer doneTaskNumber = 0;
	private Integer taskNumber = 0;
	
	private FeatureProgressDTO orhanTasks ;
	
	private List<ActorProgressDTO>  actors = new ArrayList<ActorProgressDTO>() ; 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public Float getBudjetJH() {
		return budjetJH;
	}

	public void setBudjetJH(Float budjetJH) {
		this.budjetJH = budjetJH;
	}

	public Float getTjm() {
		return tjm;
	}

	public void setTjm(Float tjm) {
		this.tjm = tjm;
	}

	public List<ActorProgressDTO> getActors() {
		return actors;
	}

	public void setActors(List<ActorProgressDTO> actors) {
		this.actors = actors;
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

	public FeatureProgressDTO getOrhanTasks() {
		return orhanTasks;
	}

	public void setOrhanTasks(FeatureProgressDTO orhanTasks) {
		this.orhanTasks = orhanTasks;
	}

	
	
	

}
