package dashboard.data.dto;

public class FeatureDTO {
	
	private String featureName ;
	private String featureType ;
	private String description ;
	private Float estimation ;
	private Integer parentID ;
	
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getFeatureType() {
		return featureType;
	}
	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getEstimation() {
		return estimation;
	}
	public void setEstimation(Float estimation) {
		this.estimation = estimation;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	
	
}
