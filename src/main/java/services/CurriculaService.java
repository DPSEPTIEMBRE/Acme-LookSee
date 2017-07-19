package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Curricula;
import repositories.CurriculaRepository;

@Service
@Transactional
public class CurriculaService {


	//Repositories

	@Autowired
	private CurriculaRepository curriculaRepository;

	//Services

	//CRUD Methods
	
	public List<Curricula> findAll() {
		return curriculaRepository.findAll();
	}

	public Curricula findOne(Integer arg0) {
		return curriculaRepository.findOne(arg0);
	}

	public <S extends Curricula> List<S> save(Iterable<S> entities) {
		return curriculaRepository.save(entities);
	}

	public <S extends Curricula> S save(S arg0) {
		return curriculaRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Curricula> getCurriculasByCandidate(int candidate_id) {
		return curriculaRepository.getCurriculasByCandidate(candidate_id);
	}

	public List<Curricula> getCurriculasGroupByCandidate() {
		return curriculaRepository.getCurriculasGroupByCandidate();
	}

	public Double avgCurriculasByCandidate() {
		return curriculaRepository.avgCurriculasByCandidate();
	}

	public List<Curricula> curriculasGroupByOffer() {
		return curriculaRepository.curriculasGroupByOffer();
	}

	public List<Curricula> curriculasGroupByCompany() {
		return curriculaRepository.curriculasGroupByCompany();
	}
}
