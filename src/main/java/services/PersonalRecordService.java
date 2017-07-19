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

	//CRUD Methods

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
