package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Candidate extends Actor{
	
	//Attributes
	
	private Curricula curricula;
	private List<Application> applications;
	
	//Getters
	
	@OneToOne(optional=true)
	@NotNull
	public Curricula getCurricula() {
		return curricula;
	}
	
	@OneToMany
	@NotNull
	public List<Application> getApplications() {
		return applications;
	}
	
	//Setters
	
	public void setCurricula(Curricula curricula) {
		this.curricula = curricula;
	}
	
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	

}
