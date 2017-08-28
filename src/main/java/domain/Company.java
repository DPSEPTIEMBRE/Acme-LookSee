package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends Actor{
	
	//Attributes
	
	private String companyName;
	private String VAT;
	private List<Offer> offers;
	private List<Payment> payments;
	private CreditCard creditCard;
	private Boolean bloked;
	
	//Getters
	
	@NotBlank
	public String getCompanyName() {
		return companyName;
	}
	
	@NotNull
	public Boolean getBloked() {
		return bloked;
	}

	@NotBlank
	public String getVAT() {
		return VAT;
	}
	
	@NotNull
	@OneToMany(mappedBy="company")
	public List<Offer> getOffers() {
		return offers;
	}
	
	@NotNull
	@OneToMany
	public List<Payment> getPayments() {
		return payments;
	}
	
	@OneToOne(optional=true)
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	//Setters
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setVAT(String vAT) {
		VAT = vAT;
	}
	
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public void setBloked(Boolean bloked) {
		this.bloked = bloked;
	}
	
}
