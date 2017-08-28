
package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.CreditCard;
import domain.Payment;
import services.AdministratorService;
import services.CreditCardService;
import services.PaymentService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PaymentTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private PaymentService			paymentService;
	@Autowired
	private CreditCardService		creditcardservice;
	@Autowired
	private AdministratorService	administratorService;


	@Test
	public void positiveTest0() {

		CreditCard creditCard = creditcardservice.findAll().iterator().next();

		template("administrator", new Date(), "description sample", 1d, 1.21d, creditCard, false, null);

	}

	@Test
	public void positiveTest1() {

		CreditCard creditCard = creditcardservice.findAll().iterator().next();

		template("administrator", new Date(), "description", 1d, 1.21d, creditCard, false, null);

		administratorService.payment();
	}

	@Test
	public void negativeTest0() {

		CreditCard creditCard = creditcardservice.findAll().iterator().next();

		template("administrator", new Date(), "", 50d, 50d, creditCard, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		CreditCard creditCard = creditcardservice.findAll().iterator().next();

		template("administrator", null, "", 1d, 1.21d, creditCard, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		CreditCard creditCard = creditcardservice.findAll().iterator().next();

		template("administrator", null, null, null, null, creditCard, null, ConstraintViolationException.class);

	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final Date createMoment, final String description, final Double price, final Double tax, final CreditCard creditCard, final Boolean paid, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Payment payment = paymentService.create();
			payment.setCreateMoment(createMoment);
			payment.setDescription(description);
			payment.setPrice(price);
			payment.setTax(tax);
			payment.setCreditCard(creditCard);
			payment.setPaid(paid);

			paymentService.save(payment);
			paymentService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
