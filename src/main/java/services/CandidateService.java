package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Candidate;
import repositories.CandidateRepository;

@Service
@Transactional
public class CandidateService {

	//Repositories

	@Autowired
	private CandidateRepository CandidateRepository;

	//Services

	//CRUD Methods

	public List<Candidate> findAll() {
		return CandidateRepository.findAll();
	}

	public Candidate findOne(Integer arg0) {
		return CandidateRepository.findOne(arg0);
	}


	public <S extends Candidate> List<S> save(Iterable<S> entities) {
		return CandidateRepository.save(entities);
	}

	public <S extends Candidate> S save(S arg0) {
		return CandidateRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Candidate> CandidateWithMoreCurriculas() {
		return CandidateRepository.CandidateWithMoreCurriculas();
	}
	
	public List<Candidate> orderByNumCurriculas() {
		return CandidateRepository.orderByNumCurriculas();
	}
}
