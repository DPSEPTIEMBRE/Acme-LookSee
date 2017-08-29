package usecases;

import java.math.BigInteger;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Brand;
import domain.CreditCard;
import services.CreditCardService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class CreditCardTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private CreditCardService creditcardService;
	
	
	//Templates
	
	/*
	 * 15.3: A company must be able to provide a credit card to settle payments with.
	 */
	protected void template(final String username, final String holderName, final Brand brandName, final BigInteger number, final Integer expirationMonth, final Integer expirationYear, final Integer CVV, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			CreditCard creditcard = creditcardService.findAll().iterator().next();
			creditcard.setHolderName(holderName);
			creditcard.setBrandName(brandName);
			creditcard.setNumber(number);
			creditcard.setExpirationMonth(expirationMonth);
			creditcard.setExpirationYear(expirationYear);
			creditcard.setCVV(CVV);

			creditcardService.save(creditcard);
			creditcardService.flush();

			
			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
	}
	
	//Drivers

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {
		
		Brand brandName = new Brand();
		brandName.setValue("VISA");

		template("company1", "holderName", brandName, new BigInteger("5419601375809935"), 8, 2, 150, null);
		
	}
	
	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {
		
		Brand brandName = new Brand();
		brandName.setValue("DISCOVER");

		template("company1", "holderName", brandName, new BigInteger("6011500718805834"), 5, 5, 300, null);
		
	}
	
	//Test #03: Empty holder name. Expected false.
	@Test
	public void negativeTest0() {
		
		Brand brandName = new Brand();
		brandName.setValue("VISA");

		template("company1", "", brandName, new BigInteger("10"), 500, 10, 10, ConstraintViolationException.class);
		
	}
	
	//Test #04: Empty holder name and invalid expiration date. Expected false.
	@Test
	public void negativeTest1() {
		
		template("company1", "", null, new BigInteger("10"), 10, -12000, 10, ConstraintViolationException.class);
		
	}
	
	//Test #05: All fields null. Expected false.
	@Test
	public void negativeTest2() {
		
		Brand brandName = new Brand();
		brandName.setValue("value");

		template("company1", null, brandName, null, null, null, null, ConstraintViolationException.class);
		
	}
}
