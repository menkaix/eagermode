package dashboard.data.dto;

import java.util.Date;

public class MileStoneDTO {

	private Integer id;

	private String mileStoneName;
	private String description;
	private Date estimStartDate;
	private Date estimEndDate;
	private Date startDate;
	private Date endDate;

	private boolean isActive;

	private Integer projectID;

	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer integer) {
		this.projectID = integer;
	}

}
