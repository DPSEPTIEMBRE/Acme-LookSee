package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Curricula;
import domain.Note;
import repositories.NoteRepository;

@Service
@Transactional
public class NoteService {

	//Repositories

	@Autowired
	private NoteRepository noteRepository;
	
	//Services

	//CRUD Methods

	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	public Note findOne(Integer arg0) {
		return noteRepository.findOne(arg0);
	}

	public <S extends Note> List<S> save(Iterable<S> entities) {
		return noteRepository.save(entities);
	}

	public <S extends Note> S save(S arg0) {
		return noteRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Curricula> notesGroupByState(int verifier_id) {
		return noteRepository.notesGroupByState(verifier_id);
	}

	public Double avgNotesByVerifier() {
		return noteRepository.avgNotesByVerifier();
	}

	Number[] notesOfVerifierGroupByCandidates(int verifier_id) {
		return noteRepository.notesOfVerifierGroupByCandidates(verifier_id);
	}



}
