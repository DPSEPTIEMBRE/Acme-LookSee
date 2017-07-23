package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Application;
import domain.StatusApplication;
import repositories.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	//Repositories

	@Autowired
	private ApplicationRepository applicationRepository;
	
	//Services
	
	@Autowired
	private CurriculaService curriculaService;
	
	@Autowired
	private OfferService offerService;
	
	//Constuctor
	
	public ApplicationService() {
		super();
	}
	
	//CRUD Methods
	
	public Application create() {
		Application application = new Application();
		
		application.setCreateMoment(new Date());
		application.setCurricula(curriculaService.create());
		application.setOffer(offerService.create());
		
		StatusApplication status= new StatusApplication();
		status.setValue("PENDING");
		
		application.setStatus(status);
		
		return application;
	}

	public List<Application> findAll() {
		return applicationRepository.findAll();
	}


	public List<Application> save(List<Application> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return applicationRepository.save(entities);
	}

	public Application findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return applicationRepository.findOne(arg0);
	}

	public Application save(Application arg0) {
		Assert.notNull(arg0);
		
		return applicationRepository.save(arg0);
	}
	
	
	//Others Methods
	
	public Object[] AvgMaxMinApplicationsAcceptedByCompany() {
		return applicationRepository.AvgMaxMinApplicationsAcceptedByCompany();
	}

	public Object[] AvgMaxMinApplicationsRejectedByCompany() {
		return applicationRepository.AvgMaxMinApplicationsRejectedByCompany();
	}
	
	public Object[] AvgMaxMinApplicationsByOffers() {
		return applicationRepository.AvgMaxMinApplicationsByOffers();
	}

	public Object[] AvgMaxMinApplicationsPendingByCompany() {
		return applicationRepository.AvgMaxMinApplicationsPendingByCompany();
	}
	
	public List<Application> applicationOrderStatus(int candidate_id) {
		Assert.notNull(candidate_id);
		
		return applicationRepository.applicationOrderStatus(candidate_id);
	}

	public List<Application> applicationOrderCreatedMoment(int candidate_id) {
		Assert.notNull(candidate_id);
		
		return applicationRepository.applicationOrderCreatedMoment(candidate_id);
	}

	public List<Application> applicationOrderLimit(int candidate_id) {
		Assert.notNull(candidate_id);
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) -7);
		return applicationRepository.applicationOrderLimit(candidate_id, c.getTime());
	}

	public Object[] AvgMaxMinApplicationsByCandidate() {
		return applicationRepository.AvgMaxMinApplicationsByCandidate();
	}


	
	
}
