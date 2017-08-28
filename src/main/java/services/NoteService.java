package services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.Note;
import domain.StatusNote;
import domain.Verifier;
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
	@Autowired
	private VerifierService verifierService;
	
	//Constructor
	
	public NoteService() {
		super();
	}

	//CRUD Methods

	public Note create(Curricula curricula) {
		Note note= new Note();
		
		note.setCreatedMoment(new Date());
		note.setCurricula(curricula);
		note.setRemark(new String());
		note.setReply(new String());
		note.setReplyMoment(null);
		
		StatusNote status= new StatusNote();
		status.setValue(new String("PENDING"));
		
		note.setStatus(status);
		
		return note;
	}
	
	public List<Note> list_group_by(Integer q) {
		Verifier verifier = verifierService.selectByUsername();
		Assert.notNull(verifier);
		
		if(q == 0) {
			noteRepository.notesGroupByState(verifier.getId());
		} else if(q == 1) {
			noteRepository.notesOfVerifierGroupByCandidates(verifier.getId());
		}
		
		return new LinkedList<Note>();
	}
	
	public void delete(Note entity) {
		Assert.notNull(entity);
		
		noteRepository.delete(entity);
	}
	
	public void delete(Iterable<Note> entities) {
		Assert.notNull(entities);
		
		noteRepository.delete(entities);
	}

	public void delete(Note entity, int curricula_id) {
		Assert.notNull(entity);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		curricula.getNotes().remove(entity);
		curriculaService.saveEditing(curricula);
		
		noteRepository.delete(entity);
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
	
	public void flush() {
		curriculaService.flush();
	}

	public Note saveCandidate(Note arg0) {
		Assert.notNull(arg0);
		
		arg0.setReplyMoment(new Date());
		
		return noteRepository.save(arg0);
	}
	
	public Note save(Note arg0, int curricula_id) {
		Assert.notNull(arg0);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		Note saved = noteRepository.save(arg0);
		curricula.getNotes().add(saved);
		
		curriculaService.saveEditing(curricula);
		
		return saved;
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

	public Double[] avgNotesByVerifierGroupByStatus() {
		return noteRepository.avgNotesByVerifierGroupByStatus();
	}

}
