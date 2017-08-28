package services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.EndorserRecord;
import repositories.EndorserRecordRepository;

@Service
@Transactional
public class EndorserRecordService {

	//Repositories

	@Autowired
	private EndorserRecordRepository endorserRecordRepository;

	//Services
	@Autowired
	private CurriculaService curriculaService;
	
	//Constructor
	
	public EndorserRecordService() {
		super();
	}
	
	private EndorserRecord clone(EndorserRecord o) {
		EndorserRecord res = new EndorserRecord();
		res.setComments(new LinkedList<String>(o.getComments()));
		res.setCopy(true);
		res.setEndorserEmail(new String(o.getEndorserEmail()));
		res.setEndorserName(new String(o.getEndorserName()));
		res.setEndorserPhone(new String(o.getEndorserPhone()));
		res.setLinkedIn(new String(o.getLinkedIn()));
		
		return res;
	}
	
	public List<EndorserRecord> clone(List<EndorserRecord> o) {
		List<EndorserRecord> cloned = new LinkedList<EndorserRecord>();
		
		for(EndorserRecord e : o) {
			cloned.add(clone(e));
		}
		
		return endorserRecordRepository.save(cloned);
	}

	//CRUD Methods
	
	public EndorserRecord create() {
		EndorserRecord record= new EndorserRecord();
		
		record.setComments(new ArrayList<String>());
		record.setCopy(false);
		record.setEndorserEmail(new String());
		record.setEndorserName(new String());
		record.setEndorserPhone(new String());
		record.setLinkedIn(new String());
		
		return record;
	}
	
	public void delete(EndorserRecord entity) {
		endorserRecordRepository.delete(entity);
	}
	
	public void delete(EndorserRecord entity, int curricula_id) {
		Assert.notNull(entity);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		curricula.getEndorserRecords().remove(entity);
		curriculaService.saveEditing(curricula);
		
		endorserRecordRepository.delete(entity);
	}
	
	public void delete(Iterable<EndorserRecord> entities) {
		Assert.notNull(entities);
		endorserRecordRepository.delete(entities);
	}

	public List<EndorserRecord> findAll() {
		return endorserRecordRepository.findAll();
	}

	public EndorserRecord findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return endorserRecordRepository.findOne(arg0);
	}

	public List<EndorserRecord> save(List<EndorserRecord> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return endorserRecordRepository.save(entities);
	}

	public EndorserRecord save(EndorserRecord arg0) {
		Assert.notNull(arg0);
		
		return endorserRecordRepository.save(arg0);
	}
	
	public void flush() {
		endorserRecordRepository.flush();
	}

	public EndorserRecord save(EndorserRecord arg0, int curricula_id) {
		Assert.notNull(arg0);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		EndorserRecord saved = endorserRecordRepository.save(arg0);
		curricula.getEndorserRecords().add(saved);
		curriculaService.saveEditing(curricula);
		
		return saved;
	}
}
