package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ActivityReport;
import domain.Actor;
import repositories.ActivityReportRepository;

@Service
@Transactional
public class ActivityReportService {

	//Repositories
	
	@Autowired
	private ActivityReportRepository activityReportRepository;
	
	//Services
	
	//CRUD Methods

	public List<ActivityReport> findAll() {
		return activityReportRepository.findAll();
	}

	public ActivityReport findOne(Integer arg0) {
		return activityReportRepository.findOne(arg0);
	}

	public <S extends ActivityReport> List<S> save(Iterable<S> entities) {
		return activityReportRepository.save(entities);
	}

	public <S extends ActivityReport> S save(S arg0) {
		return activityReportRepository.save(arg0);
	}
	
	//Custom Methods
	
	public Object[] MinMaxAvgActiviesByActor() {
		return activityReportRepository.MinMaxAvgActiviesByActor();
	}

	public List<Actor> actorsBetweenTenPercentActivities() {
		return activityReportRepository.actorsBetweenTenPercentActivities();
	}

	public List<Actor> ActivitiesByActor(int company_id) {
		return activityReportRepository.ActivitiesByActor(company_id);
	}

	
	
}
