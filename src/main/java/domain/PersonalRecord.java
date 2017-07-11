package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class PersonalRecord extends DomainEntity{

	//Attributes
	private String fullName;
	private String picture;
	private String email;
	private String phone;
	private String linkedIn;
	
	//Getters
	
	@NotBlank
	public String getFullName() {
		return fullName;
	}
	
	@NotBlank
	@URL
	public String getPicture() {
		return picture;
	}
	
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}
	
	@NotBlank
	public String getPhone() {
		return phone;
	}
	
	@NotBlank
	@URL
	public String getLinkedIn() {
		return linkedIn;
	}
	
	//Setters
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	
}
