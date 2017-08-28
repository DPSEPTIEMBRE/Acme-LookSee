package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Administrator;
import domain.AdministratorKey;
import domain.Payment;
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
	@Autowired
	private AdministratorKeyService administratorKeyService;
	@Autowired
	private PaymentService paymentService;
	
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
	
	public Administrator saveEditing(Administrator arg0) {
		Assert.notNull(arg0);
		
		return administratorRepository.save(arg0);
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
	
	public boolean payment() {
		AdministratorKey administratorKey = administratorKeyService.select_lastPaid();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date date = format.parse(administratorKey.getKeyValue());
			
			int days = daysBetween(date);
			
			if(days > 7) {
				List<Payment> payments = paymentService.getUnpaidments();
				
				for(Payment e : payments) {
					e.setPaid(true);
				}
				
				if(!payments.isEmpty()) {
					paymentService.save(payments);
				}
				
				administratorKey.setKeyValue(format.format(new Date()));
				administratorKeyService.saveEditing(administratorKey);
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	private int daysBetween(Date date) {
		Date now = new Date();
		
		return (int) ((now.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	}
	
}
