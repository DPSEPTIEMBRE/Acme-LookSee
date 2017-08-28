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
import domain.EducationRecord;
import repositories.EducationRecordRepository;

@Service
@Transactional
public class EducationRecordService {

	//Repositories

	@Autowired
	private EducationRecordRepository educationRecordRepository;

	//Services
	@Autowired
	private CurriculaService curriculaService;
	//Constructor
	
	public EducationRecordService() {
		super();
	}
	
	private EducationRecord clone(EducationRecord o) {
		EducationRecord res = new EducationRecord();
		res.setAttachment(new String(o.getAttachment()));
		res.setComments(new LinkedList<String>(o.getComments()));
		res.setCopy(true);
		res.setFinalStudying(new Date(o.getFinalStudying().getTime()));
		res.setDiplomaTitle(new String(o.getDiplomaTitle()));
		res.setInitialStudying(new Date(o.getInitialStudying().getTime()));
		res.setInstitution(new String(o.getInstitution()));
		
		return res;
	}
	
	public List<EducationRecord> clone(List<EducationRecord> o) {
		List<EducationRecord> cloned = new LinkedList<EducationRecord>();
		
		for(EducationRecord e : o) {
			cloned.add(clone(e));
		}
		
		return educationRecordRepository.save(cloned);
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
	
	public void delete(EducationRecord entity, int curricula_id) {
		Assert.notNull(entity);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		curricula.getEducationRecords().remove(entity);
		curriculaService.saveEditing(curricula);
		
		educationRecordRepository.delete(entity);
	}
	
	public void delete(Iterable<EducationRecord> entities) {
		Assert.notNull(entities);
		educationRecordRepository.delete(entities);
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

	public EducationRecord save(EducationRecord arg0, int curricula_id) {
		Assert.notNull(arg0);
		
		Curricula curricula = curriculaService.findOne(curricula_id);
		EducationRecord educationRecord = educationRecordRepository.save(arg0);
		
		List<EducationRecord> educationRecords = curricula.getEducationRecords();
		educationRecords.add(educationRecord);
		curricula.setEducationRecords(educationRecords);
		
		curriculaService.saveEditing(curricula);
		
		return educationRecord;
	}
	
	public EducationRecord saveEditing(EducationRecord record) {
		return educationRecordRepository.save(record);
	}

	public void flush() {
		curriculaService.flush();
	}

}
