package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Curricula;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.Note;
import domain.ProfessionalRecord;
import repositories.CurriculaRepository;

@Service
@Transactional
public class CurriculaService {


	//Repositories

	@Autowired
	private CurriculaRepository curriculaRepository;

	//Services
	
	@Autowired
	private PersonalRecordService personalRecordService;
	
	//Constructor
	
	public CurriculaService() {
		super();
		// TODO Auto-generated constructor stub
	}

	//CRUD Methods
	
	public Curricula create() {
		Curricula curricula= new Curricula();
		
		curricula.setCopy(false);
		curricula.setEducationRecords(new ArrayList<EducationRecord>());
		curricula.setEndorserRecords(new ArrayList<EndorserRecord>());
		curricula.setMiscellaneousRecords(new ArrayList<MiscellaneousRecord>());
		curricula.setNotes(new ArrayList<Note>());
		curricula.setPersonalRecord(personalRecordService.create());
		curricula.setProfessionalRecords(new ArrayList<ProfessionalRecord>());
		curricula.setTicker(new String());
		
		return curricula;
	}
	
	public List<Curricula> findAll() {
		return curriculaRepository.findAll();
	}


	public Curricula findOne(Integer arg0) {
		return curriculaRepository.findOne(arg0);
	}

	public <S extends Curricula> List<S> save(Iterable<S> entities) {
		return curriculaRepository.save(entities);
	}

	public <S extends Curricula> S save(S arg0) {
		return curriculaRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Curricula> getCurriculasByCandidate(int candidate_id) {
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
