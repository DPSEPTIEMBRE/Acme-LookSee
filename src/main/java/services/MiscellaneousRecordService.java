package services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.MiscellaneousRecord;
import repositories.MiscellaneousRecordRepository;

@Service
@Transactional
public class MiscellaneousRecordService {

	//Repositories

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;

	//Services
	@Autowired
	private CurriculaService curriculaService;
	//Constructor
	
	public MiscellaneousRecordService() {
		super();
	}
	
	//CRUD Methods
	private MiscellaneousRecord clone(MiscellaneousRecord o){
		MiscellaneousRecord res = new MiscellaneousRecord();
		res.setAttachment(new String(o.getAttachment()));
		res.setComments(new LinkedList<String>(o.getComments()));
		res.setCopy(true);
		res.setTitle(new String(o.getTitle()));
		
		return res;
	}
	
	public List<MiscellaneousRecord> clone(List<MiscellaneousRecord> o) {
		List<MiscellaneousRecord> cloned = new LinkedList<MiscellaneousRecord>();
		
		for(MiscellaneousRecord e : o) {
			cloned.add(clone(e));
		}
		
		return miscellaneousRecordRepository.save(cloned);
	}
	
	public MiscellaneousRecord create() {
		MiscellaneousRecord record= new MiscellaneousRecord();
		
		record.setAttachment(new String());
		record.setComments(new ArrayList<String>());
		record.setCopy(false);
		record.setTitle(new String());
		
		return record;
	}
	
	public void delete(MiscellaneousRecord entity) {
		miscellaneousRecordRepository.delete(entity);
	}
	
	public void delete(Iterable<MiscellaneousRecord> entities) {
		Assert.notNull(entities);
		miscellaneousRecordRepository.delete(entities);
	}

	public void delete(MiscellaneousRecord entity, int curricula_id) {
		Assert.notNull(entity);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		curricula.getMiscellaneousRecords().remove(entity);
		curriculaService.saveEditing(curricula);
		
		miscellaneousRecordRepository.delete(entity);
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

	public MiscellaneousRecord save(MiscellaneousRecord arg0, int curricula_id) {
		Assert.notNull(arg0);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		MiscellaneousRecord saved = miscellaneousRecordRepository.save(arg0);
		curricula.getMiscellaneousRecords().add(saved);
		curriculaService.saveEditing(curricula);
		
		return saved;
	}
	
	public void flush() {
		miscellaneousRecordRepository.flush();
	}

	public MiscellaneousRecord saveEditing(MiscellaneousRecord arg) {
		Assert.notNull(arg);
		
		return miscellaneousRecordRepository.save(arg);
	}

}
