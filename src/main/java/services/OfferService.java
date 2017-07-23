package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Application;
import domain.Offer;
import repositories.OfferRepository;

@Service
@Transactional
public class OfferService {
	
	//Repositories

	@Autowired
	private OfferRepository offerRepository;
	
	//Services
	
	@Autowired
	private CompanyService companyService;
	
	//Constructor
	
	public OfferService() {
		super();
	}
	
	//CRUD Methods
	
	public Offer create() {
		Offer offer=new Offer();
		
		offer.setApplications(new ArrayList<Application>());
		offer.setCompany(companyService.create());
		offer.setCurrency(new String("Euros"));
		offer.setDeadlineApply(new Date());
		offer.setDescription(new String());
		offer.setMaxRange(new Double(1.0));
		offer.setMinRange(new Double(0.0));
		offer.setTitle(new String());
		
		return offer;
	}
	
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	public Offer findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return offerRepository.findOne(arg0);
	}

	public List<Offer> save(List<Offer> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return offerRepository.save(entities);
	}

	public Offer save(Offer arg0) {
		Assert.notNull(arg0);
		
		return offerRepository.save(arg0);
	}

	//Others Methods
	
	public List<Offer> offersByCompany(int company_id) {
		Assert.notNull(company_id);
		
		return offerRepository.offersByCompany(company_id);
	}

}
