package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EducationRecord;
import repositories.EducationRecordRepository;

@Service
@Transactional
public class EducationRecordService {

	//Repositories

	@Autowired
	private EducationRecordRepository educationRecordRepository;

	//Services
	
	//Constructor
	
	public EducationRecordService() {
		super();
	}

	
	//CRUD Methods
	
	public EducationRecord create() {
		EducationRecord record= new EducationRecord();
		
		record.setAttachment(new String());
		record.setComments(new ArrayList<String>());
		record.setCopy(false);
		record.setDiplomaTitle(new String());
		record.setFinalStudying(new Date());
		record.setInitialStudying(new Date());
		record.setInstitution(new String());
		
		return record;
	}
	
	public List<EducationRecord> findAll() {
		return educationRecordRepository.findAll();
	}

	public EducationRecord findOne(Integer arg0) {
		return educationRecordRepository.findOne(arg0);
	}

	public <S extends EducationRecord> List<S> save(Iterable<S> entities) {
		return educationRecordRepository.save(entities);
	}

	public <S extends EducationRecord> S save(S arg0) {
		return educationRecordRepository.save(arg0);
	}

}
