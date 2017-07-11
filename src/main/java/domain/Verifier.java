package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Verifier extends Actor{
	
	//Attributes
	
	private List<Note> notes;

	//Getters
	
	@NotNull
	@OneToMany
	public List<Note> getNotes() {
		return notes;
	}

	//Setters
	
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	

}
