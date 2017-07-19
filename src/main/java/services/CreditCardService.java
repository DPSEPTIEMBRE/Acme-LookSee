package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

	//Repositories

	@Autowired
	private CreditCardRepository creditCardRepository;
	
	//Services

	//CRUD Methods
	
	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

	public CreditCard findOne(Integer arg0) {
		return creditCardRepository.findOne(arg0);
	}

	public <S extends CreditCard> List<S> save(Iterable<S> entities) {
		return creditCardRepository.save(entities);
	}

	public <S extends CreditCard> S save(S arg0) {
		return creditCardRepository.save(arg0);
	}

}
