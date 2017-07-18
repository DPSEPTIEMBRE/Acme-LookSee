package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity{
	
	//Attributes
	
	private Date createMoment;
	private StatusApplication status;
	private Curricula curricula;
	private Offer offer;
	
	//Getters
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getCreateMoment() {
		return createMoment;
	}
	
	@Valid
	@NotNull
	public StatusApplication getStatus() {
		return status;
	}
	
	@NotNull
	@OneToOne(optional=true)
	public Curricula getCurricula() {
		return curricula;
	}
	
	@NotNull
	@ManyToOne(optional=false)
	public Offer getOffer() {
		return offer;
	}
	
	//Setters
	
	public void setCreateMoment(Date createMoment) {
		this.createMoment = createMoment;
	}
	
	public void setStatus(StatusApplication status) {
		this.status = status;
	}
	
	public void setCurricula(Curricula curricula) {
		this.curricula = curricula;
	}
	
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	

}
