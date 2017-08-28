package services;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.AdministratorKey;
import domain.Cache;
import domain.Candidate;
import domain.Offer;
import repositories.CacheRepository;

@Service
@Transactional
public class CacheService {

	//Repositories

	@Autowired
	private CacheRepository cacheRepository;
	@Autowired
	private AdministratorKeyService administratorKeyService;
	
	public Cache createCandidate(List<Candidate> candidates, String session_id) {
		AdministratorKey max_results = administratorKeyService.select_max_results();
		AdministratorKey time = administratorKeyService.select_cache();
		
		int max = Integer.valueOf(max_results.getKeyValue());
		int time_clear = Integer.valueOf(time.getKeyValue());
		
		List<Integer> ids = new LinkedList<Integer>();
		
		for(Candidate e : candidates) {
			if(ids.size() >= max) {
				break; }
			
			ids.add(e.getId()); }
		
		Cache cache;
		
		if(cacheRepository.existBySessionId(session_id) > 0l) {
			cache = cacheRepository.selectBySessionId(session_id);
			cache.setResultIds(ids);
		} else {
			cache = new Cache();
			cache.setResultIds(ids);
			cache.setCacheType(0);
			cache.setSessionId(session_id);
			
			create_schedule(session_id, this, time_clear);
		}
		
		return cacheRepository.save(cache);
	}
	
	public Cache createOffer(List<Offer> offers, String session_id) {
		AdministratorKey max_results = administratorKeyService.select_max_results();
		AdministratorKey time = administratorKeyService.select_cache();
		
		int max = Integer.valueOf(max_results.getKeyValue());
		int time_clear = Integer.valueOf(time.getKeyValue());
		
		List<Integer> ids = new LinkedList<Integer>();
		
		for(Offer e : offers) {
			if(ids.size() >= max) {
				break; }
			
			ids.add(e.getId()); }
		
		Cache cache;
		
		if(cacheRepository.existBySessionId(session_id) > 0l) {
			cache = cacheRepository.selectBySessionId(session_id);
			cache.setResultIds(ids);
		} else {
			cache = new Cache();
			cache.setResultIds(ids);
			cache.setCacheType(1);
			cache.setSessionId(session_id);
			
			create_schedule(session_id, this, time_clear);
		}
		
		return cacheRepository.save(cache);
	}
	
	public Cache createOfferByIds(List<Integer> offers, String session_id) {
		AdministratorKey max_results = administratorKeyService.select_max_results();
		AdministratorKey time = administratorKeyService.select_cache();
		
		int max = Integer.valueOf(max_results.getKeyValue());
		int time_clear = Integer.valueOf(time.getKeyValue());
		
		List<Integer> ids = new LinkedList<Integer>();
		
		for(Integer e : offers) {
			if(ids.size() >= max) {
				break; }
			
			ids.add(e); }
		
		Cache cache;
		
		if(cacheRepository.existBySessionId(session_id) > 0l) {
			cache = cacheRepository.selectBySessionId(session_id);
			cache.setResultIds(ids);
		} else {
			cache = new Cache();
			cache.setResultIds(ids);
			cache.setCacheType(1);
			cache.setSessionId(session_id);
			
			create_schedule(session_id, this, time_clear);
		}
		
		return cacheRepository.save(cache);
	}
	
	public void flush() {
		cacheRepository.flush();
	}

	/**
	 * @author jvz19
	 * This method created a task that change status of application if the period is terminated
	 * */
	private static void create_schedule(final String session_id, final CacheService cacheService, int time) {
		final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		
	    exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				Cache remove = cacheService.selectBySessionId(session_id);
				
				if(remove != null) {
					cacheService.delete(remove.getId());
				}
				
				exec.shutdown();
			}
		}, time, time, TimeUnit.HOURS);
	}
	
	public boolean existBySessionId(String session_id) {
		return cacheRepository.existBySessionId(session_id) > 0l;
	}

	public Cache selectBySessionId(String session_id) {
		return cacheRepository.selectBySessionId(session_id);
	}

	public void delete(Cache entity) {
		cacheRepository.delete(entity);
	}

	public void delete(Integer id) {
		cacheRepository.delete(id);
	}
}
