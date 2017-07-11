package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity{
	
	//Attributes
	
	private String holderName;
	private Brand brandName;
	private Integer number;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer CVV;
	
	//Getters
	
	@NotBlank
	public String getHolderName() {
		return holderName;
	}
	
	@NotNull
	@Valid
	public Brand getBrandName() {
		return brandName;
	}
	
	@NotNull
	@Range(min=1,max=12)
	public Integer getNumber() {
		return number;
	}
	
	@NotNull
	public Integer getExpirationMonth() {
		return expirationMonth;
	}
	
	@NotNull
	@Range(min=100,max=999)
	public Integer getExpirationYear() {
		return expirationYear;
	}
	
	@NotNull
	public Integer getCVV() {
		return CVV;
	}
	
	//Setters
	
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	public void setBrandName(Brand brandName) {
		this.brandName = brandName;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	public void setCVV(Integer cVV) {
		CVV = cVV;
	}
	
	

}
