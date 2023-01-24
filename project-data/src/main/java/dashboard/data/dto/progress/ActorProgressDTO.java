package dashboard.data.dto.progress;

import java.util.ArrayList;
import java.util.List;

public class ActorProgressDTO {
	
private Integer id;
	
	private String actorName ;
	private String actorDescription ;
	
	private Float facturableJHConsomme ;	
	private Float totalJHConsumme ;	
	private Float pourcentTickets ;
	
	private List<UserStoryProgressDTO> stories = new ArrayList<>() ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getActorDescription() {
		return actorDescription;
	}
	public void setActorDescription(String actorDescription) {
		this.actorDescription = actorDescription;
	}
	public List<UserStoryProgressDTO> getStories() {
		return stories;
	}
	public void setStories(List<UserStoryProgressDTO> stories) {
		this.stories = stories;
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

}