
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
import repositories.CandidateRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class CandidateService {

	//Repositories

	@Autowired
	private CandidateRepository	candidateRepository;

	//Services

	@Autowired
	private FolderService		folderService;


	//Constructor

	public CandidateService() {
		super();
	}

	//CRUD Methods

	public Candidate selectByUsername(String username) {
		Assert.notNull(username);

		return candidateRepository.selectByUsername(username);
	}

	public boolean exists(Integer id) {
		return candidateRepository.exists(id);
	}

	public Candidate create() {
		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);

		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		Candidate candidate = new Candidate();

		candidate.setUserAccount(account);
		candidate.setActivities(new ArrayList<ActivityReport>());
		candidate.setactorName(new String());
		candidate.setAddress(new String());
		candidate.setEmail(new String());
		candidate.setFolders(folderService.createDefaultFolders());
		candidate.setPhone(new String());
		candidate.setSurname(new String());
		candidate.setApplications(new ArrayList<Application>());
		candidate.setCurriculas(new ArrayList<Curricula>());

		return candidate;
	}

	public List<Candidate> findAll() {
		return candidateRepository.findAll();
	}

	public List<Candidate> findAll(Iterable<Integer> ids) {
		return candidateRepository.findAll(ids);
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

	public Candidate save(Candidate arg0) {
		Assert.notNull(arg0);

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		arg0.getUserAccount().setPassword(encoder.encodePassword(arg0.getUserAccount().getPassword(), null));

		arg0.setFolders(folderService.save(folderService.createDefaultFolders()));

		return candidateRepository.save(arg0);
	}

	public Candidate saveEditing(Candidate c) {
		Assert.notNull(c);

		return candidateRepository.save(c);
	}

	public void flush() {
		candidateRepository.flush();
	}

	//Others Methods

	public List<Candidate> CandidateWithMoreCurriculas() {
		return candidateRepository.CandidateWithMoreCurriculas();
	}

	public List<Candidate> orderByNumCurriculas() {
		return candidateRepository.orderByNumCurriculas();
	}

}
