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
public class EducationRecord extends DomainEntity{
	
	//Attributes
	private String diplomaTitle;
	private Date initialStudying;
	private Date finalStudying;
	private String institution;
	private String attachment;
	private List<String> comments;
	
	//Getters
	
	@NotBlank
	public String getDiplomaTitle() {
		return diplomaTitle;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getInitialStudying() {
		return initialStudying;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFinalStudying() {
		return finalStudying;
	}
	
	@NotBlank
	public String getInstitution() {
		return institution;
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
	
	public void setDiplomaTitle(String diplomaTitle) {
		this.diplomaTitle = diplomaTitle;
	}
	
	public void setInitialStudying(Date initialStudying) {
		this.initialStudying = initialStudying;
	}
	
	public void setFinalStudying(Date finalStudying) {
		this.finalStudying = finalStudying;
	}
	
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	
	

}
