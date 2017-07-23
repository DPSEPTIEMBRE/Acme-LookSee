package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Company;
import domain.Offer;
import domain.Payment;
import repositories.CompanyRepository;
import security.UserAccount;

@Service
@Transactional
public class CompanyService {

	//Repositories

	@Autowired
	private CompanyRepository companyRepository;

	//Services
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CreditCardService creditCardService;
	
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
		company.setFolders(folderService.createDefaultFolders());
		company.setPhone(new String());
		company.setSurname(new String());
		company.setUserAccount(new UserAccount());
		company.setCompanyName(new String());
		company.setCreditCard(creditCardService.create());
		company.setOffers(new ArrayList<Offer>());
		company.setPayments(new ArrayList<Payment>());
		company.setVAT(new String());
		
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

}
