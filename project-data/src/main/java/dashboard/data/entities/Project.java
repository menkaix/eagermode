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
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String projectName ;
	
	private String projectCode ;

	private String description ;

	private Date dateCreated ;
	
	private Date deadLine ;
	
	private String refBL ;
	
	private Float budjetJH ;
	
	private Float tjm ;
	
	private Date lastActivity ;

	@ManyToOne
	private Company client ;
	
	@ManyToOne
	private ProjectStatus status ;
	
	@ManyToOne
	private ProjectGenre genre ;
	
	@ManyToOne
	private Channel channel ;
	
	@ManyToOne
	private People porter ;
	
	
	
	private boolean ignoreAudit ;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public ProjectGenre getGenre() {
		return genre;
	}

	public void setGenre(ProjectGenre genre) {
		this.genre = genre;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String companyName) {
		this.projectName = companyName;
	}

	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public boolean isIgnoreAudit() {
		return ignoreAudit;
	}

	public void setIgnoreAudit(boolean ignoreAudit) {
		this.ignoreAudit = ignoreAudit;
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

	public Float getTjm() {
		return tjm;
	}

	public void setTjm(Float tjm) {
		this.tjm = tjm;
	}

	public Float getBudjetJH() {
		return budjetJH;
	}

	public void setBudjetJH(Float budjetJH) {
		this.budjetJH = budjetJH;
	}

	
}
