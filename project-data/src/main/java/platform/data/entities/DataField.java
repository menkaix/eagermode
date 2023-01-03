package platform.data.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class DataField<T> {
	
	
	
	private String fieldName ;
	
	private T fieldValue ;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public T getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(T fieldValue) {
		this.fieldValue = fieldValue;
	}

}
