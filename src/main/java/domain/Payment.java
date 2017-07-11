package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Payment extends DomainEntity{

	//Attributes

	private Date createMoment;
	private String description;
	private Double price;
	private Double tax;
	private CreditCard creditCard;
	
	//Getters
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getCreateMoment() {
		return createMoment;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	@NotNull
	public Double getPrice() {
		return price;
	}
	
	@NotNull
	public Double getTax() {
		return tax;
	}
	
	@NotNull
	@ManyToOne(optional=false)
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	//Setters
	
	public void setCreateMoment(Date createMoment) {
		this.createMoment = createMoment;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
}
