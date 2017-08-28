package services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Brand;
import domain.Company;
import domain.CreditCard;
import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CreditCardService {

	//Repositories

	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	CompanyService companyService;
	
	//Services
	
	//Constructor
	
	public CreditCardService() {
		super();
	}

	//CRUD Methods
	
	public CreditCard create() {
		CreditCard card= new CreditCard();
		
		Brand brand= new Brand();
		brand.setValue("VISA");
		card.setBrandName(brand);
		card.setCVV(new Integer(100));
		card.setExpirationMonth(new Integer(1));
		card.setExpirationYear(new Integer(2000));
		card.setHolderName(new String());
		card.setNumber(new BigInteger("0"));
		
		return card;
	}
	
	public void flush() {
		creditCardRepository.flush();
	}

	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

	public CreditCard findOne(Integer arg0) {
		Assert.notNull(arg0);
		
		return creditCardRepository.findOne(arg0);
	}

	public List<CreditCard> save(List<CreditCard> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return creditCardRepository.save(entities);
	}

	public CreditCard save(CreditCard arg0) {
		Assert.notNull(arg0);
		
		UserAccount userAccount = LoginService.getPrincipal();
		Company company = companyService.selectByUsername(userAccount.getUsername());
		
		CreditCard creditCard = creditCardRepository.save(arg0);
		company.setCreditCard(creditCard);
		
		companyService.saveEditing(company);
		
		return creditCard;
	}

}
