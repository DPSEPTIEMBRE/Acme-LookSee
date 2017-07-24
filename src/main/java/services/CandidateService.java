package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Application;
import domain.Candidate;
import domain.Curricula;
import domain.Folder;
import domain.MailMessage;
import repositories.CandidateRepository;
import security.Authority;
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
		candidate.setPhone(new String());
		candidate.setSurname(new String());
		candidate.setApplications(new ArrayList<Application>());
		candidate.setCurriculas(new ArrayList<Curricula>());
		
		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		candidate.setUserAccount(account);
		
		return candidate;
	}

	public List<Candidate> findAll() {
		return CandidateRepository.findAll();
	}

	public Candidate findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return CandidateRepository.findOne(arg0);
	}


	public List<Candidate> save(List<Candidate> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return CandidateRepository.save(entities);
	}

	public Candidate save(Candidate arg0) {
		Assert.notNull(arg0);
		
		return CandidateRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Candidate> CandidateWithMoreCurriculas() {
		return CandidateRepository.CandidateWithMoreCurriculas();
	}
	
	public List<Candidate> orderByNumCurriculas() {
		return CandidateRepository.orderByNumCurriculas();
	}
	
	public Candidate save_create(Candidate user) {
		
		Folder inbox = folderService.create();
		inbox.setFolderName("INBOX");
		inbox.setMessages(new ArrayList<MailMessage>());

		Folder outbox = folderService.create();
		outbox.setFolderName("OUTBOX");
		outbox.setMessages(new ArrayList<MailMessage>());

		Folder thrasbox = folderService.create();
		thrasbox.setFolderName("THRASBOX");
		thrasbox.setMessages(new ArrayList<MailMessage>());

		Folder spambox = folderService.create();
		spambox.setFolderName("SPAMBOX");
		spambox.setMessages(new ArrayList<MailMessage>());
		
		user.setFolders(Arrays.asList(inbox, outbox, thrasbox, spambox));
		
		
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String pass = user.getUserAccount().getPassword();
		user.getUserAccount().setPassword(encoder.encodePassword(pass, null));
		
		
		return save(user);
	}
	
	
}
