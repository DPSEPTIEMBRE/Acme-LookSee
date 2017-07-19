package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Offer;
import repositories.OfferRepository;

@Service
@Transactional
public class OfferService {
	
	//Repositories

	@Autowired
	private OfferRepository offerRepository;
	
	//Services

	//CRUD Methods
	
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	public Offer findOne(Integer arg0) {
		return offerRepository.findOne(arg0);
	}

	public <S extends Offer> List<S> save(Iterable<S> entities) {
		return offerRepository.save(entities);
	}

	public <S extends Offer> S save(S arg0) {
		return offerRepository.save(arg0);
	}

	//Others Methods
	
	public List<Offer> offers() {
		return offerRepository.offers();
	}

	public List<Offer> offersByCompany(int company_id) {
		return offerRepository.offersByCompany(company_id);
	}

}
