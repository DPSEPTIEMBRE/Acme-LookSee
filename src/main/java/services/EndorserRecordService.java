package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EndorserRecord;
import repositories.EndorserRecordRepository;

@Service
@Transactional
public class EndorserRecordService {

	//Repositories

	@Autowired
	private EndorserRecordRepository endorserRecordRepository;

	//Services
	
	//Constructor
	
	public EndorserRecordService() {
		super();
	}

	//CRUD Methods
	
	public EndorserRecord create() {
		EndorserRecord record= new EndorserRecord();
		
		record.setComments(new ArrayList<String>());
		record.setCopy(false);
		record.setEndorserEmail(new String());
		record.setEndorserName(new String());
		record.setEndorserPhone(new String());
		record.setLinkedIn(new String());
		
		return record;
	}
	
	public List<EndorserRecord> findAll() {
		return endorserRecordRepository.findAll();
	}

	public EndorserRecord findOne(Integer arg0) {
		return endorserRecordRepository.findOne(arg0);
	}

	public <S extends EndorserRecord> List<S> save(Iterable<S> entities) {
		return endorserRecordRepository.save(entities);
	}

	public <S extends EndorserRecord> S save(S arg0) {
		return endorserRecordRepository.save(arg0);
	}
}
