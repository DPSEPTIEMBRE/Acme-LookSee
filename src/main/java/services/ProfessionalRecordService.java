package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.ProfessionalRecord;
import repositories.ProfessionalRecordRepository;

@Service
@Transactional
public class ProfessionalRecordService {
	
	//Repositories

	@Autowired
	private  ProfessionalRecordRepository  professionalRecordRepository;

	//Services
	@Autowired
	private CurriculaService curriculaService;
	
	//Constructor
	
	public ProfessionalRecordService() {
		super();
	}

	//CRUD Methods
	
	public ProfessionalRecord clone(ProfessionalRecord o){
		ProfessionalRecord res = new ProfessionalRecord();
		res.setAttachment(new String(o.getAttachment()));
		res.setComments(new LinkedList<String>(o.getComments()));
		res.setCompanyName(new String(o.getCompanyName()));
		res.setCopy(true);
		res.setFinalWork(new Date(o.getFinalWork().getTime()));
		res.setInitialWork(new Date(o.getInitialWork().getTime()));
		res.setRole(new String(o.getRole()));
		
		return res;
	}
	
	public List<ProfessionalRecord> clone(List<ProfessionalRecord> o) {
		List<ProfessionalRecord> cloned = new LinkedList<ProfessionalRecord>();
		
		for(ProfessionalRecord e : o) {
			cloned.add(clone(e));
		}
		
		return professionalRecordRepository.save(cloned);
	}
	
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

	public void delete(ProfessionalRecord entity) {
		professionalRecordRepository.delete(entity);
	}
	
	
	
	public void delete(Iterable<ProfessionalRecord> entities) {
		Assert.notNull(entities);
		professionalRecordRepository.delete(entities);
	}

	public void delete(ProfessionalRecord entity, int curricula_id) {
		Assert.notNull(entity);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		curricula.getProfessionalRecords().remove(entity);
		curriculaService.saveEditing(curricula);
		
		professionalRecordRepository.delete(entity);
	}

	public List<ProfessionalRecord> findAll() {
		return professionalRecordRepository.findAll();
	}


	public ProfessionalRecord findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return professionalRecordRepository.findOne(arg0);
	}

	public List<ProfessionalRecord> save(List<ProfessionalRecord> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return professionalRecordRepository.save(entities);
	}

	public ProfessionalRecord save(ProfessionalRecord record, int curricula_id) {
		Assert.notNull(record);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		ProfessionalRecord saved = professionalRecordRepository.save(record);
		curricula.getProfessionalRecords().add(saved);
		curriculaService.saveEditing(curricula);
		
		return saved;
	}
	
	public void flush() {
		professionalRecordRepository.flush();
	}

	public ProfessionalRecord saveEditing(ProfessionalRecord arg0) {
		Assert.notNull(arg0);
		
		return professionalRecordRepository.save(arg0);
	}

}
