package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class MiscellaneousRecord extends DomainEntity{
	
	//Attributes
	private String title;
	private String attachment;
	private List<String> comments;
	
	//Getters
	
	@NotBlank
	public String getTitle() {
		return title;
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
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	

}
