package dashboard.data.dto;

@Deprecated
public class ProjectMinimumDTO {
	
	private Integer id ;
	private String projectName ;
	private String projectCode ;
	private String client ;
	private String description ;
	private String status ;
	private String genre ;
	private String createDate ;
	private String lastActivity ;
	private boolean newDataNeeded ;
	
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public boolean isNewDataNeeded() {
		return newDataNeeded;
	}
	public void setNewDataNeeded(boolean newDataNeeded) {
		this.newDataNeeded = newDataNeeded;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

}
