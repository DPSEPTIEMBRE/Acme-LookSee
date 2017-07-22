package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ActivityReport;
import domain.Administrator;
import repositories.AdministratorRepository;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {
	
	//Repositories

	@Autowired
	private AdministratorRepository administratorRepository;

	//Services
	
	@Autowired
	private FolderService folderService;
	
	//Constructor
	
	public AdministratorService() {
		super();
	}

	
	//CRUD Methods
	
	public Administrator create() {
		Administrator admin= new Administrator();
		
		admin.setActivities(new ArrayList<ActivityReport>());
		admin.setactorName(new String());
		admin.setAddress(new String());
		admin.setEmail(new String());
		admin.setFolders(folderService.createDefaultFolders());
		admin.setPhone(new String());
		admin.setSurname(new String());
		admin.setUserAccount(new UserAccount());
		
		return admin;
	}
	
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Administrator findOne(Integer arg0) {
		return administratorRepository.findOne(arg0);
	}

	public <S extends Administrator> List<S> save(Iterable<S> entities) {
		return administratorRepository.save(entities);
	}

	public <S extends Administrator> S save(S arg0) {
		return administratorRepository.save(arg0);
	}
	
	//Others Methods
	public static boolean check_phone(CharSequence phone) {
		Pattern p = Pattern.compile("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})");
		Matcher m = p.matcher(phone);
		
		return m.matches();
	}
	
	
	
}
