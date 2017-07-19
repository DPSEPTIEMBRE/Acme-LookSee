package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	
	//Repositories

	@Autowired
	private AdministratorRepository administratorRepository;

	//Services
	
	//CRUD Methods
	
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Administrator findOne(Integer arg0) {
		return administratorRepository.findOne(arg0);
	}

	public <S extends Administrator> List<S> save(Iterable<S> entities) {
		return administratorRepository.save(entities);
	}

	public <S extends Administrator> S save(S arg0) {
		return administratorRepository.save(arg0);
	}
}
