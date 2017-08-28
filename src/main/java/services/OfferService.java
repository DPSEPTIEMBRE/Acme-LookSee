package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.AdministratorKey;
import domain.Application;
import domain.Cache;
import domain.Candidate;
import domain.Company;
import domain.Curricula;
import domain.Offer;
import repositories.OfferRepository;
import security.LoginService;

@Service
@Transactional
public class OfferService {
	
	//Repositories
	@Autowired
	private OfferRepository offerRepository;
	
	//Services
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CurriculaService curriculaService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private AdministratorKeyService administratorKeyService;
	@Autowired
	private CacheService cacheService;
	
	//Constructor
	
	public OfferService() {
		super();
	}
	
	//CRUD Methods
	
	public Application apply(int offer_id, Curricula curricula) {
		try {
			Offer offer = offerRepository.findOne(offer_id);
			
			if(offer.getDeadlineApply().before(new Date())) {
				throw new IllegalArgumentException();
			}
			
			Application application = applicationService.create();
			application.setCurricula(curriculaService.clone(curricula));
			application.setOffer(offer);
			
			Application saved = applicationService.save(application);
			
			Candidate candidate = candidateService.selectByUsername(LoginService.getPrincipal().getUsername());
			candidate.getApplications().add(saved);
			candidateService.saveEditing(candidate);
			
			offer.getApplications().add(saved);
			offerRepository.save(offer);
			
			create_schedule(saved, saved.getId(), applicationService);
			
			return saved;
		} catch(Throwable t) {
			throw new IllegalArgumentException(t);
		}
	}
	
	private static int hoursBeetween(Date startDate, Date endDate) {
		long secs = (endDate.getTime() - startDate.getTime()) / 1000;
		long hours = secs / 3600;    
		
		return (int) Math.abs(hours);
	}
	
	private static Date plusDate(Offer o) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(o.getDeadlineApply());
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date date = calendar.getTime();
		
		return date;
	}
	
	/**
	 * @author jvz19
	 * This method created a task that change status of application if the period is terminated
	 * */
	private static void create_schedule(Application application, final int application_id, final ApplicationService service) {
		int hours = hoursBeetween(new Date(), plusDate(application.getOffer()));
		
		final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				service.rejectSchedule(application_id);
				exec.shutdown();
			}
		}, hours, 10, TimeUnit.HOURS);
	}
	
	public Offer create() {
		Offer offer=new Offer();
		
		Company c = companyService.selectByUsername(LoginService.getPrincipal().getUsername());
		
		offer.setApplications(new ArrayList<Application>());
		offer.setCompany(c);
		offer.setCurrency(new String("Euros"));
		offer.setDeadlineApply(new Date());
		offer.setDescription(new String());
		offer.setMaxRange(new Double(1.0));
		offer.setMinRange(new Double(0.0));
		offer.setTitle(new String());
		
		return offer;
	}
	
	public List<Curricula> getAvaliableCurriculas() {
		
		return curriculaService.selectCurriculasNotCopy();
	}
	
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}
	
	public List<Offer> filterOf(Company q) {
		
		return q.getBloked() ? new LinkedList<Offer>() : q.getOffers();
	}
	
	public List<Offer> findQ(String q, boolean isAdmin) {
		if(q == null || q.trim().isEmpty()) {
			List<Offer> res = new LinkedList<Offer>();
			
			for(Offer e : offerRepository.findAll()) {
				if(e.getCompany().getBloked() && !isAdmin) {
					continue;
				}
				
				if(e.getDeadlineApply().before(new Date())) {
					continue;
				}
				
				res.add(e);
			}
			
			return res;
		}
		
		List<Offer> res = new LinkedList<Offer>();
		
		final String rq = q.toLowerCase().trim();
		
		for(Offer e : offerRepository.findAll()) {
			if(e.getCompany().getBloked() && !isAdmin) {
				continue;
			}
			
			if(e.getDeadlineApply().before(new Date())) {
				continue;
			}
			
			if(e.getTitle().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			if(e.getDescription().toLowerCase().contains(rq)) {
				res.add(e);
				continue;
			}
			
			try {
				Double price = Double.valueOf(rq.trim());
				if(price >= e.getMinRange() && price <= e.getMaxRange()) {
					res.add(e);
					continue;
				}
			} catch(Exception ex) {
				try {
					String cur = rq.replaceAll("[\\d.]","");

					if(cur.trim().toLowerCase().equalsIgnoreCase(e.getCurrency())) {
						Double price = Double.valueOf(rq.replaceAll("[^\\d.]", ""));
						if(price >= e.getMinRange() && price <= e.getMaxRange()) {
							res.add(e);
							continue;
						}
					}
				} catch(Exception ex0) {
					continue;
				}
			}
		}
		
		return res;
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
	
	public void flush() {
		offerRepository.flush();
	}

	public boolean isValidDate(Offer o) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date date = calendar.getTime();
		
		return o.getDeadlineApply().after(date);
	}

	//Others Methods
	
	public List<Offer> offersByCompany(int company_id) {
		Assert.notNull(company_id);
		
		return offerRepository.offersByCompany(company_id);
	}
	
	public List<Offer> advanced_search(Double min, Double max, String q, String session_id) {
		List<Offer> res = new LinkedList<Offer>();
		
		if(min == 0 && max == -1 && (q == null || q.trim().isEmpty())) {
			if(cacheService.existBySessionId(session_id)) {
				Cache cache = cacheService.selectBySessionId(session_id);
				if(cache.getCacheType() != 1) {
					return res;
				}
				
				return offerRepository.findAll(cache.getResultIds());
			}
			
			return res; 
		}
		
		max = max == -1 ? Double.MAX_VALUE : max;
		
//		Date now = new Date();
		AdministratorKey administratorkey = administratorKeyService.select_max_results();
		int max_size = Integer.valueOf(administratorkey.getKeyValue());
		
		if(q == null || q.trim().isEmpty()) {
			for(Offer e : offerRepository.findAll()) {
				if(res.size() == max_size) {
					break; }
//				if(e.getDeadlineApply().before(now)) {
//					continue; }
				
				if(e.getMinRange() >= min && e.getMaxRange() <= max) {
					res.add(e);
				}
			}
		} else {
			final String rq = q.toLowerCase().trim();
			
			for(Offer e : offerRepository.findAll()) {
				if(res.size() == max_size) {
					break; }
//				if(e.getDeadlineApply().before(now)) {
//					continue; }
				
				if ((e.getTitle().toLowerCase().contains(rq) || e.getDescription().toLowerCase().contains(rq))
						&& e.getMinRange() >= min && e.getMaxRange() <= max) {
					res.add(e);
				}
			}
		}
		
		return res;
	}

}
