package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.Note;
import domain.StatusNote;
import repositories.NoteRepository;

@Service
@Transactional
public class NoteService {

	//Repositories

	@Autowired
	private NoteRepository noteRepository;
	
	//Services
	
	@Autowired
	private CurriculaService curriculaService;
	
	//Constructor
	
	public NoteService() {
		super();
	}

	//CRUD Methods

	public Note create() {
		Note note= new Note();
		
		note.setCreatedMoment(new Date());
		note.setCurricula(curriculaService.create());
		note.setRemark(new String());
		note.setReply(new String());
		note.setReplyMoment(new Date());
		
		StatusNote status= new StatusNote();
		status.setValue(new String("PENDING"));
		
		note.setStatus(status);
		
		return note;
	}
	
	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	public Note findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return noteRepository.findOne(arg0);
	}

	public List<Note> save(List<Note> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return noteRepository.save(entities);
	}

	public Note save(Note arg0) {
		Assert.notNull(arg0);
		
		return noteRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Curricula> notesGroupByState(int verifier_id) {
		Assert.notNull(verifier_id);
		
		return noteRepository.notesGroupByState(verifier_id);
	}

	public Double avgNotesByVerifier() {
		return noteRepository.avgNotesByVerifier();
	}

	Number[] notesOfVerifierGroupByCandidates(int verifier_id) {
		Assert.notNull(verifier_id);
		
		return noteRepository.notesOfVerifierGroupByCandidates(verifier_id);
	}

	public Double avgNotesByVerifierGroupByStatus() {
		return noteRepository.avgNotesByVerifierGroupByStatus();
	}



}
