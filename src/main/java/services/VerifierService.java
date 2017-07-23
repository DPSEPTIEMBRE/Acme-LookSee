package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Note;
import domain.Verifier;
import repositories.VerifierRepository;
import security.UserAccount;

@Service
@Transactional
public class VerifierService {

	//Repositories

	@Autowired
	private  VerifierRepository  verifierRepository;

	//Services
	
	@Autowired
	private FolderService folderService;
	
	//Constructor
	
	public VerifierService() {
		super();
	}


	//CRUD Methods
	
	public Verifier create() {
		Verifier verifier= new Verifier();
		
		verifier.setActivities(new ArrayList<ActivityReport>());
		verifier.setactorName(new String());
		verifier.setAddress(new String());
		verifier.setEmail(new String());
		verifier.setFolders(folderService.createDefaultFolders());
		verifier.setPhone(new String());
		verifier.setSurname(new String());
		verifier.setUserAccount(new UserAccount());
		verifier.setNotes(new ArrayList<Note>());
		
		return verifier;
	}
	
	public List<Verifier> findAll() {
		return verifierRepository.findAll();
	}
	
	public Verifier findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return verifierRepository.findOne(arg0);
	}

	public List<Verifier> save(List<Verifier> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return verifierRepository.save(entities);
	}

	public Verifier save(Verifier arg0) {
		Assert.notNull(arg0);
		
		return verifierRepository.save(arg0);
	}


}
