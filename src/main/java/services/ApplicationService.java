package services;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Application;
import domain.Candidate;
import domain.StatusApplication;
import repositories.ApplicationRepository;
import security.LoginService;

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
	private CandidateService candidateService;
	
	//Constuctor
	
	public ApplicationService() {
		super();
	}
	
	//CRUD Methods
	
	public Application create() {
		Application application = new Application();
		
		application.setCreateMoment(new Date());
		application.setCurricula(curriculaService.create());
		application.setOffer(null);
		
		StatusApplication status = new StatusApplication();
		status.setValue("PENDING");
		
		application.setStatus(status);
		
		return application;
	}
	
	public void rejectSchedule(Integer id) {
		if(id != null) {
			if(applicationRepository.exists(id)) {
				try {
					Application entity = applicationRepository.findOne(id);
					
					if(entity.getStatus().getValue().equals("PENDING")) {
						StatusApplication rejected = new StatusApplication();
						rejected.setValue("REJECTED");
						
						entity.setStatus(rejected);
						
						applicationRepository.save(entity);
					}
				} catch(Throwable th) {
					th.printStackTrace();
				}
			}
		}
	}
	
	public boolean exists(Integer id) {
		return applicationRepository.exists(id);
	}

	public List<Application> selectSelf() {
		try {
			Candidate candidate = candidateService.selectByUsername(LoginService.getPrincipal().getUsername());
			
			return candidate.getApplications();
		} catch(Exception e) {
			return new LinkedList<Application>();
		}
	}
	
	public List<Application> selectSelfCompany() {
		try {
			return applicationRepository.getCompanyApplications(LoginService.getPrincipal().getUsername());
		} catch(Exception e) {
			return new LinkedList<Application>();
		}
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

	public void flush() {
		applicationRepository.flush();
	}
}
