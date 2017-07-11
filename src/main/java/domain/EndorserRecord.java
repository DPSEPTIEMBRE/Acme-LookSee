package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class EndorserRecord extends DomainEntity{
	
	//Attributes
	private String endorserName;
	private String endorserEmail;
	private String endorserPhone;
	private String linkedIn;
	private List<String> comments;
	
	//Getters
	
	@NotBlank
	public String getEndorserName() {
		return endorserName;
	}
	
	@NotBlank
	@Email
	public String getEndorserEmail() {
		return endorserEmail;
	}
	
	@NotBlank
	public String getEndorserPhone() {
		return endorserPhone;
	}
	
	@NotBlank
	@URL
	public String getLinkedIn() {
		return linkedIn;
	}
	
	@NotNull
	@ElementCollection(targetClass=String.class)
	public List<String> getComments() {
		return comments;
	}
	
	//Setters
	
	public void setEndorserName(String endorserName) {
		this.endorserName = endorserName;
	}
	
	public void setEndorserEmail(String endorserEmail) {
		this.endorserEmail = endorserEmail;
	}
	
	public void setEndorserPhone(String endorserPhone) {
		this.endorserPhone = endorserPhone;
	}
	
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	

}
