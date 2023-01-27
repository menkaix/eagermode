package dashboard.data.dto.progress;

import java.util.ArrayList;
import java.util.List;

public class TaskProgressDTO {
	
	private Integer id;
	private String code ;
	
	private String title;
	
	private Float facturableJHConsomme ;	
	private Float totalJHConsumme ;	
	private Float pourcentTickets ;
	
	private List<String> people = new ArrayList<>() ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getPeople() {
		return people;
	}
	public void setPeople(List<String> people) {
		this.people = people;
	}

}
