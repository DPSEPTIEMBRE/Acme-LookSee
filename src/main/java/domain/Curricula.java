package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity{
	
	//Attributes
	private String ticker;
	private List<EducationRecord> educationRecords;
	private PersonalRecord personalRecord;
	private List<ProfessionalRecord> professionalRecords;
	private List<MiscellaneousRecord> miscellaneousRecords;
	private List<EndorserRecord> endorserRecords;
	private List<Note> notes;
	private Boolean copy;
	
	//Getters
	
	@NotNull
	public Boolean getCopy() {
		return copy;
	}

	@NotBlank
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}-[a-zA-Z]{5}")
	public String getTicker() {
		return ticker;
	}
	
	@OneToMany
	@NotNull
	public List<EducationRecord> getEducationRecords() {
		return educationRecords;
	}
	
	@OneToOne(optional = true)
	public PersonalRecord getPersonalRecord() {
		return personalRecord;
	}
	
	@OneToMany
	@NotNull
	public List<ProfessionalRecord> getProfessionalRecords() {
		return professionalRecords;
	}
	
	@OneToMany
	@NotNull
	public List<MiscellaneousRecord> getMiscellaneousRecords() {
		return miscellaneousRecords;
	}
	
	@OneToMany
	@NotNull
	public List<EndorserRecord> getEndorserRecords() {
		return endorserRecords;
	}
	
	@NotNull
	@ManyToMany
	public List<Note> getNotes(){
		return notes;
	}
	
	//Setters
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public void setEducationRecords(List<EducationRecord> educationRecords) {
		this.educationRecords = educationRecords;
	}
	
	public void setPersonalRecord(PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}
	
	public void setProfessionalRecords(List<ProfessionalRecord> professionalRecords) {
		this.professionalRecords = professionalRecords;
	}
	
	public void setMiscellaneousRecords(List<MiscellaneousRecord> miscellaneousRecords) {
		this.miscellaneousRecords = miscellaneousRecords;
	}
	
	public void setEndorserRecords(List<EndorserRecord> endorserRecords) {
		this.endorserRecords = endorserRecords;
	}
	
	public void setNotes(List<Note> notes){
		this.notes = notes;
	}
	
	public void setCopy(Boolean copy) {
		this.copy = copy;
	}

}
