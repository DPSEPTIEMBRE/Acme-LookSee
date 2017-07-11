package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ActivityReport extends DomainEntity{
	
	//Attributes
	
	private Date writtenMoment;
	private String title;
	private String description;
	private List<String> attachments;

	//Getters
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getWrittenMoment() {
		return writtenMoment;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	@NotEmpty
	@ElementCollection(targetClass=String.class)
	public List<String> getAttachments() {
		return attachments;
	}
	
	//Setters
	
	public void setWrittenMoment(Date writtenMoment) {
		this.writtenMoment = writtenMoment;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
	

}
