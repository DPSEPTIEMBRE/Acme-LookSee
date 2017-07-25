package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Administrator;
import repositories.AdministratorRepository;
import security.LoginService;
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
		Assert.notNull(arg0);
		
		return administratorRepository.findOne(arg0);
	}

	public  List<Administrator> save(List<Administrator> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return administratorRepository.save(entities);
	}

	public Administrator save(Administrator arg0) {
		Assert.notNull(arg0);
		
		return administratorRepository.save(arg0);
	}
	
	//Others Methods
	public static boolean check_phone(CharSequence phone) {
		Assert.notNull(phone);
		
		Pattern p = Pattern.compile("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})");
		Matcher m = p.matcher(phone);
		
		return m.matches();
	}
	
	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		return res;
	}
	
	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Administrator res;
		res = administratorRepository.findOneByAdminId(userAccount.getId());
		return res;
	}
	
	public Administrator findByUserAccountId(int userAccountId) {
		Assert.notNull(userAccountId);
		Administrator res;
		res = administratorRepository.findOneByAdminId(userAccountId);
		return res;
	}
}
