package dashboard.data.dto.progress;

public class TaskProgressDTO {
	
	private Integer id;
	private String code ;
	
	private Float facturableJHConsomme ;	
	private Float totalJHConsumme ;	
	private Float pourcentTickets ;
	
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

}
