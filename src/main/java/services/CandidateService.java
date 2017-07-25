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
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CandidateService {

	//Repositories

	@Autowired
	private CandidateRepository candidateRepository;

	//Services

	@Autowired
	private FolderService folderService;

	//Constructor

	public CandidateService() {
		super();
	}


	//CRUD Methods

	public Candidate create() {
		Candidate candidate = new Candidate();
		List<Folder> defaultFolders = folderService.createDefaultFolders();
		folderService.save(defaultFolders);

		candidate.setActivities(new ArrayList<ActivityReport>());
		candidate.setactorName(new String());
		candidate.setAddress(new String());
		candidate.setEmail(new String());
		candidate.setPhone(new String());
		candidate.setSurname(new String());
		candidate.setApplications(new ArrayList<Application>());
		candidate.setCurriculas(new ArrayList<Curricula>());

		candidate.setFolders(defaultFolders);

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		candidate.setUserAccount(account);


		return candidate;
	}

	public List<Candidate> findAll() {
		return candidateRepository.findAll();
	}

	public Candidate findOne(Integer arg0) {
		Assert.notNull(arg0);

		return candidateRepository.findOne(arg0);
	}


	public List<Candidate> save(List<Candidate> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());

		return candidateRepository.save(entities);
	}

	public void save(Candidate user) {
		Assert.notNull(user);

		UserAccount account = user.getUserAccount();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password = account.getPassword();

		password = encoder.encodePassword(password, null);
		account.setPassword(password);
		
		List<Folder> defaultFolders = folderService.createDefaultFolders();
		folderService.save(defaultFolders);
		user.setFolders(defaultFolders);

		candidateRepository.save(user);
	}

	//Others Methods

	public List<Candidate> CandidateWithMoreCurriculas() {
		return candidateRepository.CandidateWithMoreCurriculas();
	}

	public List<Candidate> orderByNumCurriculas() {
		return candidateRepository.orderByNumCurriculas();
	}

	public Candidate findByPrincipal() {
		Candidate res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		return res;
	}

	public Candidate findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Candidate res;
		res = candidateRepository.findByUserAccount(userAccount.getId());
		return res;
	}


	public void save_create(Candidate user) {

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

	}


}
