package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		Assert.notNull(arg0);
		
		return educationRecordRepository.findOne(arg0);
	}

	public List<EducationRecord> save(List<EducationRecord> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return educationRecordRepository.save(entities);
	}

	public EducationRecord save(EducationRecord arg0) {
		Assert.notNull(arg0);
		
		return educationRecordRepository.save(arg0);
	}

}
