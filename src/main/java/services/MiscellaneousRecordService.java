package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.MiscellaneousRecord;
import repositories.MiscellaneousRecordRepository;

@Service
@Transactional
public class MiscellaneousRecordService {

	//Repositories

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;

	//Services

	//CRUD Methods

	public List<MiscellaneousRecord> findAll() {
		return miscellaneousRecordRepository.findAll();
	}

	public MiscellaneousRecord findOne(Integer arg0) {
		return miscellaneousRecordRepository.findOne(arg0);
	}

	public <S extends MiscellaneousRecord> List<S> save(Iterable<S> entities) {
		return miscellaneousRecordRepository.save(entities);
	}

	public <S extends MiscellaneousRecord> S save(S arg0) {
		return miscellaneousRecordRepository.save(arg0);
	}

}
