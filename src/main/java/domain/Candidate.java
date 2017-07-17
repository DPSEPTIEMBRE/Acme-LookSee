package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Candidate extends Actor{
	
	//Attributes
	
	private List<Curricula> curriculas;
	private List<Application> applications;
	
	//Getters
	
	@OneToMany
	@NotNull
	public List<Curricula> getCurriculas() {
		return curriculas;
	}
	
	@OneToMany
	@NotNull
	public List<Application> getApplications() {
		return applications;
	}
	
	//Setters
	
	public void setCurriculas(List<Curricula> curriculas) {
		this.curriculas = curriculas;
	}
	
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	

}
