package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.PersonalRecord;
import repositories.PersonalRecordRepository;

@Service
@Transactional
public class PersonalRecordService {
	
	//Repositories

	@Autowired
	private  PersonalRecordRepository  personalRecordRepository;

	//Services
	@Autowired
	CurriculaService curriculaService;
	
	//Constructor
	
	public PersonalRecordService() {
		super();
	}

	
	//CRUD Methods
	
	public PersonalRecord clone(PersonalRecord p) {
		PersonalRecord res = new PersonalRecord();
		res.setCopy(true);
		res.setEmail(new String(p.getEmail()));
		res.setFullName(new String(p.getFullName()));
		res.setLinkedIn(new String(p.getLinkedIn()));
		res.setPhone(new String(p.getPhone()));
		res.setPicture(new String(p.getPicture()));
		
		return personalRecordRepository.save(res);
	}
	
	public PersonalRecord create() {
		PersonalRecord record= new PersonalRecord();
		
		record.setCopy(false);
		record.setEmail(new String());
		record.setFullName(new String());
		record.setLinkedIn(new String());
		record.setPhone(new String());
		record.setPicture(new String());
		
		
		return record;
	}

	public void delete(PersonalRecord entity) {
		Assert.notNull(entity);
		personalRecordRepository.delete(entity);
	}

	public List<PersonalRecord> findAll() {
		return personalRecordRepository.findAll();
	}

	public PersonalRecord findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return personalRecordRepository.findOne(arg0);
	}

	public PersonalRecord save(PersonalRecord arg0, int curricula_id) {
		Assert.notNull(arg0);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		PersonalRecord saved = personalRecordRepository.save(arg0);
		
		curricula.setPersonalRecord(saved);
		
		curriculaService.saveEditing(curricula);
		
		return saved;
	}
	
	public void flush() {
		personalRecordRepository.flush();
	}


	public PersonalRecord saveEditing(PersonalRecord personalRecord) {
		return personalRecordRepository.save(personalRecord);
	}


	public List<PersonalRecord> save(List<PersonalRecord> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return personalRecordRepository.save(entities);
	}
	



}
