package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ProfessionalRecord extends DomainEntity{
	
	//Attributes
	private String companyName;
	private Date initialWork;
	private Date finalWork;
	private String role;
	private String attachment;
	private List<String> comments;
	
	//Getters
	
	@NotBlank
	public String getCompanyName() {
		return companyName;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getInitialWork() {
		return initialWork;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFinalWork() {
		return finalWork;
	}
	
	@NotBlank
	public String getRole() {
		return role;
	}
	
	@URL
	@NotNull
	public String getAttachment() {
		return attachment;
	}
	
	@NotNull
	@ElementCollection(targetClass=String.class)
	public List<String> getComments() {
		return comments;
	}
	
	//Setters
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setInitialWork(Date initialWork) {
		this.initialWork = initialWork;
	}
	
	public void setFinalWork(Date finalWork) {
		this.finalWork = finalWork;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	

}
