package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Cache;
import domain.Candidate;
import domain.Curricula;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.Note;
import domain.ProfessionalRecord;
import repositories.CurriculaRepository;
import security.LoginService;

@Service
@Transactional
public class CurriculaService {


	//Repositories

	@Autowired
	private CurriculaRepository curriculaRepository;

	//Services
	
	@Autowired
	private PersonalRecordService personalRecordService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private EducationRecordService educationRecordService;
	@Autowired
	private ProfessionalRecordService professionalRecordService;
	@Autowired
	private MiscellaneousRecordService miscellaneousRecordService;
	@Autowired
	private EndorserRecordService endorserRecordService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private CacheService cacheService;
	
	//Constructor
	
	public CurriculaService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Curricula clone(Curricula o) {
		Curricula res = new Curricula();
		res.setPersonalRecord(personalRecordService.clone(o.getPersonalRecord()));
		res.setEducationRecords(educationRecordService.clone(o.getEducationRecords()));
		res.setMiscellaneousRecords(miscellaneousRecordService.clone(o.getMiscellaneousRecords()));
		res.setEndorserRecords(endorserRecordService.clone(o.getEndorserRecords()));
		res.setNotes(new LinkedList<Note>(o.getNotes()));
		res.setProfessionalRecords(professionalRecordService.clone(o.getProfessionalRecords()));
		res.setTicker(new String(o.getTicker()));
		res.setCopy(true);
		
		Candidate candidate = candidateService.selectByUsername(LoginService.getPrincipal().getUsername());
		Curricula saved = curriculaRepository.save(res);
		candidate.getCurriculas().add(saved);
		candidateService.saveEditing(candidate);
		
		return saved;
	}

	//CRUD Methods
	
	public List<Curricula> list_group_by(Integer q) {
		if(q == 0) {
			return curriculaRepository.curriculasGroupByCompany();
		} else if(q == 1) {
			return curriculaRepository.curriculasGroupByOffer();
		} else if(q == 2) {
			return curriculaRepository.getCurriculasGroupByCandidate();
		}
		
		return new LinkedList<Curricula>();
	}
	
	public List<Curricula> curriculasOfSelf() {
		return curriculaRepository.curriculasOfSelf(LoginService.getPrincipal().getUsername());
	}
	
	public List<Curricula> curriculasOf(Integer id) {
		Assert.notNull(id);
		
		return curriculaRepository.curriculasOfSelf(id);
	}
	
	public List<Curricula> selectCurriculasNotCopy() {
		return curriculaRepository.selectCurriculasNotCopy(LoginService.getPrincipal().getUsername());
	}
	
	public Curricula create() {
		Curricula curricula= new Curricula();
		
		curricula.setCopy(false);
		curricula.setEducationRecords(new ArrayList<EducationRecord>());
		curricula.setEndorserRecords(new ArrayList<EndorserRecord>());
		curricula.setMiscellaneousRecords(new ArrayList<MiscellaneousRecord>());
		curricula.setNotes(new ArrayList<Note>());
		curricula.setPersonalRecord(personalRecordService.create());
		curricula.setProfessionalRecords(new ArrayList<ProfessionalRecord>());
		curricula.setTicker(nextTicker());
		
		return curricula;
	}
	
	private String nextTicker() {
		final String dictionary = new String("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
		
		StringBuilder str = new StringBuilder();
		Random rand = new Random();
		for(int i = 0; i < 5; i++) {
			str.append(dictionary.charAt(rand.nextInt(dictionary.length()))); }
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		str.insert(0, format.format(new Date()) + "-");
		
		return str.toString();
	}
	
	public void delete(Curricula entity) {
		if(!entity.getCopy()) {
			educationRecordService.delete(entity.getEducationRecords());
			personalRecordService.delete(entity.getPersonalRecord());
			professionalRecordService.delete(entity.getProfessionalRecords());
			miscellaneousRecordService.delete(entity.getMiscellaneousRecords());
			endorserRecordService.delete(entity.getEndorserRecords());
			noteService.delete(entity.getNotes());
			
			curriculaRepository.delete(entity);
		}
	}

	public List<Curricula> findAll() {
		return curriculaRepository.findAll();
	}
	
	public List<Curricula> findAll(String q) {
		if(q == null || q.trim().isEmpty()) {
			return curriculaRepository.findAllNotCopy();
		}
		
		List<Curricula> curriculas = new LinkedList<Curricula>();
		
		final String rq = q.toLowerCase().trim();
		
		for(Candidate c : candidateService.findAll()) {
			loop: for(Curricula e : c.getCurriculas()) {
				if(!e.getCopy()) {
					if(e.getTicker().toLowerCase().contains(rq)) {
						curriculas.add(e);
						continue;
					}
					
					if(e.getPersonalRecord().getEmail().toLowerCase().contains(rq)) {
						curriculas.add(e);
						continue loop;
					}
					
					if(e.getPersonalRecord().getFullName().toLowerCase().contains(rq)) {
						curriculas.add(e);
						continue loop;
					}
					
					if(e.getPersonalRecord().getLinkedIn().toLowerCase().contains(rq)) {
						curriculas.add(e);
						continue loop;
					}
					
					if(e.getPersonalRecord().getPhone().toLowerCase().contains(rq)) {
						curriculas.add(e);
						continue loop;
					}
					
					for(EducationRecord o : e.getEducationRecords()) {
						if(o.getDiplomaTitle().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getInstitution().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
					}
					
					for(EndorserRecord o : e.getEndorserRecords()) {
						if(o.getEndorserEmail().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getEndorserName().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getEndorserPhone().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getLinkedIn().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
					}
					
					for(Note o : e.getNotes()) {
						if(o.getRemark().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getReply().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
					}
					
					for(ProfessionalRecord o : e.getProfessionalRecords()) {
						if(o.getCompanyName().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
						
						if(o.getRole().toLowerCase().contains(rq)) {
							curriculas.add(e);
							continue loop;
						}
					}
				}
			}
		}
		
		return curriculas;
	}
	
	public List<Candidate> findAllCandidates(String q, String session_id) {
		if(q == null || q.trim().isEmpty()) {
			if(cacheService.existBySessionId(session_id)) {
				Cache cache = cacheService.selectBySessionId(session_id);
				if(cache.getCacheType() != 0) {
					return new LinkedList<Candidate>();
				}
				
				return candidateService.findAll(cache.getResultIds());
			}
			
			return new LinkedList<Candidate>();
		}
		
		List<Candidate> curriculas = new LinkedList<Candidate>();
		
		final String rq = q.toLowerCase().trim();
		
		loop: for(Candidate c : candidateService.findAll()) {
			for(Curricula e : c.getCurriculas()) {
				if(!e.getCopy()) {
					if(e.getTicker().toLowerCase().contains(rq)) {
						curriculas.add(c);
						continue;
					}
					
					if(e.getPersonalRecord().getEmail().toLowerCase().contains(rq)) {
						curriculas.add(c);
						continue loop;
					}
					
					if(e.getPersonalRecord().getFullName().toLowerCase().contains(rq)) {
						curriculas.add(c);
						continue loop;
					}
					
					if(e.getPersonalRecord().getLinkedIn().toLowerCase().contains(rq)) {
						curriculas.add(c);
						continue loop;
					}
					
					if(e.getPersonalRecord().getPhone().toLowerCase().contains(rq)) {
						curriculas.add(c);
						continue loop;
					}
					
					for(EducationRecord o : e.getEducationRecords()) {
						if(o.getDiplomaTitle().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getInstitution().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
					}
					
					for(EndorserRecord o : e.getEndorserRecords()) {
						if(o.getEndorserEmail().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getEndorserName().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getEndorserPhone().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getLinkedIn().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
					}
					
					for(Note o : e.getNotes()) {
						if(o.getRemark().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getReply().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
					}
					
					for(ProfessionalRecord o : e.getProfessionalRecords()) {
						if(o.getCompanyName().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
						
						if(o.getRole().toLowerCase().contains(rq)) {
							curriculas.add(c);
							continue loop;
						}
					}
				}
			}
		}
		
		return curriculas;
	}

	public Curricula findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return curriculaRepository.findOne(arg0);
	}

	public List<Curricula> save(List<Curricula> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return curriculaRepository.save(entities);
	}

	public Curricula save(Curricula arg0) {
		Assert.notNull(arg0);
		Assert.notNull(arg0.getEducationRecords());
		Assert.notNull(arg0.getEndorserRecords());
		Assert.notNull(arg0.getMiscellaneousRecords());
		Assert.notNull(arg0.getNotes());
		Assert.notNull(arg0.getProfessionalRecords());
		
		Curricula saved = curriculaRepository.save(arg0);
		Candidate candidate = candidateService.selectByUsername(LoginService.getPrincipal().getUsername());
		candidate.getCurriculas().add(saved);
		
		candidateService.saveEditing(candidate);
		
		return saved;
	}
	
	public Curricula saveEditing(Curricula curricula) {
		return curriculaRepository.save(curricula);
	}
	
	
	
	public void flush() {
		curriculaRepository.flush();
	}

	//Others Methods
	
	public List<Curricula> getCurriculasByCandidate(int candidate_id) {
		Assert.notNull(candidate_id);
		
		return curriculaRepository.getCurriculasByCandidate(candidate_id);
	}

	public List<Curricula> getCurriculasGroupByCandidate() {
		return curriculaRepository.getCurriculasGroupByCandidate();
	}

	public Double avgCurriculasByCandidate() {
		return curriculaRepository.avgCurriculasByCandidate();
	}

	public List<Curricula> curriculasGroupByOffer() {
		return curriculaRepository.curriculasGroupByOffer();
	}

	public List<Curricula> curriculasGroupByCompany() {
		return curriculaRepository.curriculasGroupByCompany();
	}
	
	public static String createRandomTicker() {
		String ticker=new String();
		Calendar calendar = Calendar.getInstance();
		
		ticker = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" +calendar.get(Calendar.DAY_OF_MONTH)
		+ createRandomTickerLetters();		
		
		return ticker;
	}
	
	public static String createRandomTickerLetters() {
		String res= new String();
		String allsLetters = new String("ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz");
		
		for(int i = 0; i<6; i++) {
			Random random= new Random();
			int index =  random.nextInt(53);
			res = res + allsLetters.substring(index, index);
		}
		
		return res;
	}
}
