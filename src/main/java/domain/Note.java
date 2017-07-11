package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity{
	
	//Attributes

	private Date createdMoment;
	private String remark;
	private String reply;
	private Date replyMoment;
	private StatusNote status;
	
	//Getters
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getCreatedMoment() {
		return createdMoment;
	}
	
	@NotBlank
	public String getRemark() {
		return remark;
	}
	
	@NotNull
	public String getReply() {
		return reply;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getReplyMoment() {
		return replyMoment;
	}
	
	@Valid
	@NotNull
	public StatusNote getStatus() {
		return status;
	}
	
	//Setters
	
	public void setCreatedMoment(Date createdMoment) {
		this.createdMoment = createdMoment;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public void setReplyMoment(Date replyMoment) {
		this.replyMoment = replyMoment;
	}
	
	public void setStatus(StatusNote status) {
		this.status = status;
	}
	
	
}
