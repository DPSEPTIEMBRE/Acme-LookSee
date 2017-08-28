package usecases;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.EndorserRecord;
import services.EndorserRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EndorserRecordTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private EndorserRecordService endorserrecordService;

	@Test
	public void positiveTest0() {

		List<String> comments = new ArrayList<String>();
		comments.add("sample");
		
		template("candidate2", "endorserName", "endorserEmail@mail.es", "+5 (10) 9132", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, null);

	}

	@Test
	public void positiveTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "endorserName", "endorserEmail@mail.es", "+8 (20) 9138", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, null);

	}

	@Test
	public void negativeTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", "", "+5 (10) 9132", "linkedIn", comments, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", "", "", "", comments, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", null, null, null, null, comments, null, ConstraintViolationException.class);

	}

	@Test
	public void driver() {
		List<String> comments = new ArrayList<String>();
		
		template("candidate2", "endorserName", "endorserEmail@mail.es", "+8 (20) 9138", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, null);
		template("candidate2", "endorserName", "endorserEmail@mail.es", "+9 (20) 9938", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, null);
		template("candidate2", "endorserName", "endorserEmail", "endorserPhone", "linkedIn", comments, false,
				ConstraintViolationException.class);
		template("candidate2", "endorserName", "endorserEmail", "endorserPhone", "linkedIn", comments, false,
				ConstraintViolationException.class);
		template("candidate2", "endorserName", "endorserEmail", "endorserPhone", "linkedIn", comments, false,
				ConstraintViolationException.class);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final String endorserName, final String endorserEmail,
			final String endorserPhone, final String linkedIn, final List<String> comments, final Boolean copy,
			final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			EndorserRecord endorserrecord = endorserrecordService.create();
			endorserrecord.setEndorserName(endorserName);
			endorserrecord.setEndorserEmail(endorserEmail);
			endorserrecord.setEndorserPhone(endorserPhone);
			endorserrecord.setLinkedIn(linkedIn);
			endorserrecord.setComments(comments);
			endorserrecord.setCopy(copy);

			endorserrecordService.save(endorserrecord);
			endorserrecordService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
