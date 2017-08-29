
package usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.ActivityReport;
import domain.Company;
import domain.CreditCard;
import domain.Folder;
import domain.Payment;
import security.Authority;
import security.UserAccount;
import services.CompanyService;
import services.CreditCardService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CompanyTest extends AbstractTest {

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private CreditCardService	creditCardService;


	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("company1", "compi", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("company2", "compi2", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);

	}

	//Text #03: All parameters correct. Expected true.
	@Test
	public void positiveTest2() {
		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("company3", "compi3", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);

	}

	//Test #04: Empty username. Expected false.
	@Test
	public void negativeTest0() {
		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));
		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("", "compi3", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);
	}

	//Test 05: Incorrect mail format. Expected false.
	@Test
	public void negativeTest1() {

		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));
		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("compi", "compi3", "compigmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);
	}

	//Test #06: Incorrect phone number. Expected false.
	@Test
	public void negativeTest2() {

		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));
		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("holi", "compi3", "compi@gmail.com", " 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);
	}

	//Ancillary tests
	@Test
	public void driver() {
		Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));
		CreditCard creditCard = creditCardService.findAll().iterator().next();

		template("company1", "compi", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);
		template("company2", "compi", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);
		template("company3", "compi", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, null);
		template("", "compi3", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);
		template("mal", "compi3", "compigmail.com", ") 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);
		template("mal", "compi3", "compi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), "hellowrld", "1234", new ArrayList<Payment>(), creditCard, false, ConstraintViolationException.class);

	}

	/*
	 * 10.2: A non authenticated user must be able to register as a company.
	 */
	protected void template(final String actorname, final String surname, final String email, final String phone, final String address, final UserAccount userAccount, final List<ActivityReport> activities, final List<Folder> folders,
		final String companyName, final String vat, final List<Payment> payments, final CreditCard creditcard, final Boolean bloked, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Company company = companyService.findAll().iterator().next();
			company.setactorName(actorname);
			company.setSurname(surname);
			company.setEmail(email);
			company.setPhone(phone);
			company.setAddress(address);
			company.setUserAccount(userAccount);
			company.setActivities(activities);
			company.setFolders(folders);
			company.setCompanyName(companyName);
			company.setVAT(vat);
			company.setPayments(payments);
			company.setCreditCard(creditcard);
			company.setBloked(bloked);

			companyService.save(company);
			companyService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

	}
}
