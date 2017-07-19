package services;

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

	//CRUD Methods

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
