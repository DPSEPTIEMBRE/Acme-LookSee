package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.AdministratorKey;
import services.AdministratorKeyService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdministratorKeyTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AdministratorKeyService administratorkeyService;


	@Test
	public void positiveTest0() {

		template("administrator", "keyName", "keyValue", null);

	}

	@Test
	public void positiveTest1() {

		template("administrator", "keyName", "keyValue", null);

	}

	@Test
	public void negativeTest0() {

		template("administrator", "", "", ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		template("administrator", "", "", ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		template("administrator", null, null, NullPointerException.class);

	}

	protected void template(final String username, final String keyName, final String keyValue, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			AdministratorKey administratorkey = administratorkeyService.create();
			administratorkey.setKeyName(keyName);
			administratorkey.setKeyValue(keyValue);

			administratorkeyService.save(administratorkey);
			administratorkeyService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
