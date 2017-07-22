package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.PersonalRecord;
import repositories.PersonalRecordRepository;

@Service
@Transactional
public class PersonalRecordService {
	
	//Repositories

	@Autowired
	private  PersonalRecordRepository  personalRecordRepository;

	//Services
	
	//Constructor
	
	public PersonalRecordService() {
		super();
	}

	
	//CRUD Methods
	
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

	public List<PersonalRecord> findAll() {
		return personalRecordRepository.findAll();
	}

	public PersonalRecord findOne(Integer arg0) {
		return personalRecordRepository.findOne(arg0);
	}

	public <S extends PersonalRecord> S save(S arg0) {
		return personalRecordRepository.save(arg0);
	}

	public PersonalRecord saveAndFlush(PersonalRecord entity) {
		return personalRecordRepository.saveAndFlush(entity);
	}


}
