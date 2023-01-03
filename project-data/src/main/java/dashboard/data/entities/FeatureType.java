package dashboard.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeatureType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String typeName ;
	private String description ;
	private boolean isVisual ;
	private boolean isContainer ;
	private boolean isInput;
	private boolean isBehaviour ;
	

	public boolean isVisual() {
		return isVisual;
	}

	public void setVisual(boolean isVisual) {
		this.isVisual = isVisual;
	}

	public boolean isContainer() {
		return isContainer;
	}

	public void setContainer(boolean isContainer) {
		this.isContainer = isContainer;
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public boolean isBehaviour() {
		return isBehaviour;
	}

	public void setBehaviour(boolean isBehaviour) {
		this.isBehaviour = isBehaviour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
