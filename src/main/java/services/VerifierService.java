package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Verifier;
import repositories.VerifierRepository;

@Service
@Transactional
public class VerifierService {

	//Repositories

	@Autowired
	private  VerifierRepository  verifierRepository;

	//Services

	//CRUD Methods
	
	public List<Verifier> findAll() {
		return verifierRepository.findAll();
	}

	public Verifier findOne(Integer arg0) {
		return verifierRepository.findOne(arg0);
	}

	public <S extends Verifier> List<S> save(Iterable<S> entities) {
		return verifierRepository.save(entities);
	}

	public <S extends Verifier> S save(S arg0) {
		return verifierRepository.save(arg0);
	}


}
