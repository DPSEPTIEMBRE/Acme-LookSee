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
public class Offer extends DomainEntity{

	//Attributes

	private String title;
	private String description;
	private Double minRange;
	private Double maxRange;
	private String currency;
	private Date deadlineApply;
	private Company company;
	
	//Getters
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	@NotNull
	public Double getMinRange() {
		return minRange;
	}
	
	@NotNull
	public Double getMaxRange() {
		return maxRange;
	}
	
	@NotBlank
	public String getCurrency() {
		return currency;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getDeadlineApply() {
		return deadlineApply;
	}
	
	@NotNull
	@ManyToOne(optional=false)
	public Company getCompany() {
		return company;
	}
	
	//Setters
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setMinRange(Double minRange) {
		this.minRange = minRange;
	}
	
	public void setMaxRange(Double maxRange) {
		this.maxRange = maxRange;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setDeadlineApply(Date deadlineApply) {
		this.deadlineApply = deadlineApply;
	}

	public void setCompany(Company company) {
		this.company = company;
	}



}
