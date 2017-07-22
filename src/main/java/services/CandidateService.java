package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ActivityReport;
import domain.Application;
import domain.Candidate;
import domain.Curricula;
import repositories.CandidateRepository;
import security.UserAccount;

@Service
@Transactional
public class CandidateService {

	//Repositories

	@Autowired
	private CandidateRepository CandidateRepository;

	//Services
	
	@Autowired
	private FolderService folderService;
	
	//Constructor
	
	public CandidateService() {
		super();
	}

	
	//CRUD Methods
	
	public Candidate create() {
		Candidate candidate=new Candidate();
		
		candidate.setActivities(new ArrayList<ActivityReport>());
		candidate.setactorName(new String());
		candidate.setAddress(new String());
		candidate.setEmail(new String());
		candidate.setFolders(folderService.createDefaultFolders());
		candidate.setPhone(new String());
		candidate.setSurname(new String());
		candidate.setUserAccount(new UserAccount());
		candidate.setApplications(new ArrayList<Application>());
		candidate.setCurriculas(new ArrayList<Curricula>());
		
		return candidate;
	}

	public List<Candidate> findAll() {
		return CandidateRepository.findAll();
	}

	public Candidate findOne(Integer arg0) {
		return CandidateRepository.findOne(arg0);
	}


	public <S extends Candidate> List<S> save(Iterable<S> entities) {
		return CandidateRepository.save(entities);
	}

	public <S extends Candidate> S save(S arg0) {
		return CandidateRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Candidate> CandidateWithMoreCurriculas() {
		return CandidateRepository.CandidateWithMoreCurriculas();
	}
	
	public List<Candidate> orderByNumCurriculas() {
		return CandidateRepository.orderByNumCurriculas();
	}
	
	
}
