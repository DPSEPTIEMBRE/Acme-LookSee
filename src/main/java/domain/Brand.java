package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Brand {

	//Attributes

	private final String VISA="VISA",MASTERCARD="MASTERCARD",DISCOVER="DISCOVER",DINNERS="DINNERS",AMEX="AMEX";
	private String value;

	//Getters

	@NotBlank
	@Pattern(regexp = "^" + VISA + "|" + MASTERCARD + "|" + DISCOVER + "|" + DINNERS + "|" + AMEX +"$")
	public String getValue() {
		return value;
	}


	//Setters

	public void setValue(String value) {
		this.value = value;
	}
}
