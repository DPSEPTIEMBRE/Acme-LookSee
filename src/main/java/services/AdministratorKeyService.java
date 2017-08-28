package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.AdministratorKey;
import repositories.AdministratorKeyRepository;

@Service
@Transactional
public class AdministratorKeyService {
	
	//Repositories
	@Autowired
	private AdministratorKeyRepository administratorKeyRepository;
	
	//Services
	
	public AdministratorKey saveEditing(AdministratorKey entity) {
		Assert.notNull(entity);
		
		return administratorKeyRepository.save(entity);
	}
	
	public void flush() {
		administratorKeyRepository.flush();
	}
	
	public AdministratorKey create() {
		return new AdministratorKey();
	}

	public AdministratorKey save(AdministratorKey entity) {
		Assert.notNull(entity);
		if(entity.getKeyName().equals("CACHE")) {
			Integer time = Integer.valueOf(entity.getKeyValue());
			
			if(time < 1 || time > 24) {
				throw new IllegalArgumentException("cache.time");
			}
		}
		
		if(entity.getKeyName().equals("MAXRESULTS")) {
			Integer max = Integer.valueOf(entity.getKeyValue());
			
			if(max < 1 || max > 100) {
				throw new IllegalArgumentException("cache.max.results");
			}
		}
		
		if(entity.getKeyName().equals("LASTPAID")) {
			throw new IllegalArgumentException("cache.last.paid");
		}
		
		return administratorKeyRepository.save(entity);
	}

	public List<AdministratorKey> findAll() {
		return administratorKeyRepository.findAll();
	}

	public AdministratorKey findOne(Integer id) {
		Assert.notNull(id);
		
		return administratorKeyRepository.findOne(id);
	}
	
	public AdministratorKey selectByKy(String key) {
		Assert.notNull(key);
		
		return administratorKeyRepository.selectByKy(key);
	}
	
	public AdministratorKey select_iva() {
		return administratorKeyRepository.selectByKy("IVA");
	}
	
	public AdministratorKey select_lastPaid() {
		return administratorKeyRepository.selectByKy("LASTPAID");
	}
	
	public AdministratorKey select_price() {
		return administratorKeyRepository.selectByKy("PRICE");
	}
	
	public AdministratorKey select_cache() {
		return administratorKeyRepository.selectByKy("CACHE");
	}
	
	public AdministratorKey select_max_results() {
		return administratorKeyRepository.selectByKy("MAXRESULTS");
	}

}
