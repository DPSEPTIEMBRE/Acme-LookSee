package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ProfessionalRecord;
import repositories.ProfessionalRecordRepository;

@Service
@Transactional
public class ProfessionalRecordService {
	
	//Repositories

	@Autowired
	private  ProfessionalRecordRepository  professionalRecordRepository;

	//Services
	
	//Constructor
	
	public ProfessionalRecordService() {
		super();
	}

	//CRUD Methods
	
	public ProfessionalRecord create() {
		ProfessionalRecord record= new ProfessionalRecord();
		
		record.setAttachment(new String());
		record.setComments(new ArrayList<String>());
		record.setCompanyName(new String());
		record.setCopy(false);
		record.setFinalWork(new Date());
		record.setInitialWork(new Date());
		record.setRole(new String());
		
		return record;
	}

	public List<ProfessionalRecord> findAll() {
		return professionalRecordRepository.findAll();
	}


	public ProfessionalRecord findOne(Integer arg0) {
		return professionalRecordRepository.findOne(arg0);
	}

	public <S extends ProfessionalRecord> List<S> save(Iterable<S> entities) {
		return professionalRecordRepository.save(entities);
	}

	public <S extends ProfessionalRecord> S save(S arg0) {
		return professionalRecordRepository.save(arg0);
	}

}
