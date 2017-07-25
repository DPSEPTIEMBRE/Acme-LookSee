package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.ActivityReport;
import domain.Company;
import domain.Folder;
import domain.MailMessage;
import domain.Offer;
import domain.Payment;
import forms.CompanyRegisterForm;
import repositories.CompanyRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class CompanyService {

	//Repositories

	@Autowired
	private CompanyRepository companyRepository;

	//Services
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private Validator validator;

	
	//Constructor
	
	public CompanyService() {
		super();
	}

	//CRUD Methods
	
	public Company create() {
		Company company= new Company();
		
		company.setActivities(new ArrayList<ActivityReport>());
		company.setactorName(new String());
		company.setAddress(new String());
		company.setEmail(new String());
		company.setPhone(new String());
		company.setSurname(new String());
		company.setUserAccount(new UserAccount());
		company.setCompanyName(new String());
		company.setOffers(new ArrayList<Offer>());
		company.setPayments(new ArrayList<Payment>());
		company.setVAT(new String());
		
		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		company.setUserAccount(account);
		
		return company;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return companyRepository.findOne(arg0);
	}

	public List<Company> save(List<Company> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return companyRepository.save(entities);
	}

	public Company save(Company arg0) {
		Assert.notNull(arg0);
		
		return companyRepository.save(arg0);
	}
	
	//Others Methods
	
	public Company companyByOffer(int offer_id) {
		Assert.notNull(offer_id);
		
		return companyRepository.companyByOffer(offer_id);
	}

	public List<Company> orderByNumOffers() {
		return companyRepository.orderByNumOffers();
	}

	public Double avgNumberOfferByCompany() {
		return companyRepository.avgNumberOfferByCompany();
	}

	public List<Company> companyMaxOffers() {
		return companyRepository.companyMaxOffers();
	}
	
	public Company save_create(Company user) {
		
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
	
	public Company reconstruct(final CompanyRegisterForm companyRegisterForm, final BindingResult binding) {
		Assert.notNull(companyRegisterForm);
		Assert.notNull(binding);

		// Comprobaciones basicas: acepta condiciones de uso y confirma
		// contraseñas
		Assert.isTrue(companyRegisterForm.isTermsAndConditions());

		Company result;
		UserAccount userAccount;

		userAccount = this.userAccountService.createByCompany();
		Assert.notNull(userAccount);
		userAccount.setUsername(companyRegisterForm.getUsername());
		userAccount.setPassword(companyRegisterForm.getPassword());

		result = this.create();
		result.setCompanyName(companyRegisterForm.getName());
		result.setSurname(companyRegisterForm.getSurname());
		result.setEmail(companyRegisterForm.getEmail());
		result.setPhone(companyRegisterForm.getPhone());
		result.setAddress(companyRegisterForm.getAddress());
		result.setUserAccount(userAccount);

		// La validacion va sobre el FORM (que es lo que se va a devolver a la vista) si hay errores.
		this.validator.validate(companyRegisterForm, binding);

		return result;
	}



}
