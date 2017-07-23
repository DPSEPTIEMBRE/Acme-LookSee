package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.MiscellaneousRecord;
import repositories.MiscellaneousRecordRepository;

@Service
@Transactional
public class MiscellaneousRecordService {

	//Repositories

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;

	//Services
	
	//Constructor
	
	public MiscellaneousRecordService() {
		super();
	}
	
	//CRUD Methods
	
	public MiscellaneousRecord create() {
		MiscellaneousRecord record= new MiscellaneousRecord();
		
		record.setAttachment(new String());
		record.setComments(new ArrayList<String>());
		record.setCopy(false);
		record.setTitle(new String());
		
		return record;
	}

	public List<MiscellaneousRecord> findAll() {
		return miscellaneousRecordRepository.findAll();
	}


	public MiscellaneousRecord findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return miscellaneousRecordRepository.findOne(arg0);
	}

	public List<MiscellaneousRecord> save(List<MiscellaneousRecord> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return miscellaneousRecordRepository.save(entities);
	}

	public MiscellaneousRecord save(MiscellaneousRecord arg0) {
		Assert.notNull(arg0);
		
		return miscellaneousRecordRepository.save(arg0);
	}

}
