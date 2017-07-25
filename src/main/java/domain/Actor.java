
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	//Attributes

	private String					actorName;
	private String					surname;
	private String					email;
	private String					phone;
	private String					address;
	private UserAccount				userAccount;
	private List<ActivityReport>	activities;
	private List<Folder>			folders;


	//Getters

	@OneToMany
	@NotNull
	public List<ActivityReport> getActivities() {
		return activities;
	}

	@OneToMany
	@NotNull
	public List<Folder> getFolders() {
		return folders;
	}

	@NotBlank
	public String getactorName() {
		return actorName;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	@NotNull
	public String getPhone() {
		return phone;
	}

	@NotNull
	public String getAddress() {
		return address;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	//Setters

	public void setactorName(String actorName) {
		this.actorName = actorName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setActivities(List<ActivityReport> activities) {
		this.activities = activities;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

}
