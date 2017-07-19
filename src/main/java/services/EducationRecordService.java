package services;

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

	//CRUD Methods
	
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
