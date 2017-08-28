package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class AdministratorKey extends DomainEntity {

	// Attributes

	private String keyName;
	private String keyValue;
	
	// Getters

	@NotBlank
	public String getKeyName() {
		return keyName;
	}

	@NotBlank
	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
