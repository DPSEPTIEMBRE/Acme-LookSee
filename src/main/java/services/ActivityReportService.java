package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Actor;
import domain.Administrator;
import domain.Candidate;
import domain.Company;
import domain.Verifier;
import repositories.ActivityReportRepository;
import security.LoginService;

@Service
@Transactional
public class ActivityReportService {

	//Repositories
	
	@Autowired
	private ActivityReportRepository activityReportRepository;
	
	//Services
	@Autowired
	LoginService loginService;
	@Autowired
	AdministratorService administratorService;
	@Autowired
	CompanyService companyService;
	@Autowired
	VerifierService verifierService;
	@Autowired
	CandidateService candidateService;
	
	//Constructor
	
	public ActivityReportService() {
		super();
	}
	
	//CRUD Methods
	
	public ActivityReport create() {
		ActivityReport activity = new ActivityReport();
		
		activity.setAttachments(new ArrayList<String>());
		activity.setDescription(new String());
		activity.setTitle(new String());
		activity.setWrittenMoment(new Date());
		
		return activity;
	}

	public List<ActivityReport> findAll() {
		return activityReportRepository.findAll();
	}

	public ActivityReport findOne(Integer arg0) {	
		Assert.notNull(arg0);
		
		return activityReportRepository.findOne(arg0);
	}
	
	public ActivityReport createDefault(String title, String msg) {
		try {
			ActivityReport report = new ActivityReport();
			report.setAttachments(Arrays.asList("http://www.iconsdb.com/icons/preview/royal-blue/info-xxl.png"));
			report.setDescription(msg);
			report.setTitle(title);
			report.setWrittenMoment(new Date());
			
			ActivityReport saved = activityReportRepository.save(report);
			
			Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getUsername());
			actor.getActivities().add(saved);
			
			if(actor instanceof Administrator) {
				administratorService.saveEditing((Administrator) actor);
			} else if(actor instanceof Company) {
				companyService.saveEditing((Company) actor);
			} else if(actor instanceof Verifier) {
				verifierService.saveEditing((Verifier) actor);
			} else if(actor instanceof Candidate) {
				candidateService.saveEditing((Candidate) actor);
			}
			
			return saved;
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return null;
	}

	public  List<ActivityReport> save(List<ActivityReport> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return activityReportRepository.save(entities);
	}

	public ActivityReport save(ActivityReport activity) {
		Assert.notNull(activity);
		
		return activityReportRepository.save(activity);
	}
	
	public void flush() {
		activityReportRepository.flush();
	}

	//Custom Methods
	
	public Object[] MinMaxAvgActiviesByActor() {
		return activityReportRepository.MinMaxAvgActiviesByActor();
	}

	public List<Actor> actorsBetweenTenPercentActivities() {
		return activityReportRepository.actorsBetweenTenPercentActivities();
	}

	public List<Actor> ActivitiesByActor(int company_id) {
		Assert.notNull(company_id);
		
		return activityReportRepository.ActivitiesByActor(company_id);
	}

	public void delete(ActivityReport activity) {
		Assert.notNull(activity);
		
		Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getUsername());
		actor.getActivities().remove(activity);
		
		if(actor instanceof Administrator) {
			administratorService.saveEditing((Administrator) actor);
		} else if(actor instanceof Company) {
			companyService.saveEditing((Company) actor);
		} else if(actor instanceof Verifier) {
			verifierService.saveEditing((Verifier) actor);
		} else if(actor instanceof Candidate) {
			candidateService.saveEditing((Candidate) actor);
		}
		
		activityReportRepository.delete(activity);
	}
	
	
}
