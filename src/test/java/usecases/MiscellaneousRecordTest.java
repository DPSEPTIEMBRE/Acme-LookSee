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

import domain.Candidate;
import domain.Curricula;
import domain.MiscellaneousRecord;
import services.CandidateService;
import services.MiscellaneousRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MiscellaneousRecordTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private MiscellaneousRecordService miscellaneousrecordService;
	@Autowired
	private CandidateService candidateService;

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "title", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, null);

	}
	
	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		List<String> comments = new ArrayList<String>();
		comments.add("sample");
		
		template("candidate2", "title 2", "https://www.w3schools.com/css/img_fjords.jpg", comments, false, null);

	}

	//Test #03: Empty fields. Expected false.
	@Test
	public void negativeTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", "", comments, false, ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", "", comments, false, ConstraintViolationException.class);

	}

	//Test #05: Null fields. Expected false.
	@Test
	public void negativeTest2() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", null, null, comments, null, ConstraintViolationException.class);

	}

	/*
	 * 12.3: A candidate must be able to edit his or her curricula.
	 */
	protected void template(final String username, final String title, final String attachment,
			final List<String> comments, final Boolean copy, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			MiscellaneousRecord miscellaneousrecord = miscellaneousrecordService.create();
			miscellaneousrecord.setTitle(title);
			miscellaneousrecord.setAttachment(attachment);
			miscellaneousrecord.setComments(comments);
			miscellaneousrecord.setCopy(copy);

			Candidate candidate = candidateService.findAll().iterator().next();
			
			for(Candidate e : candidateService.findAll()) {
				if(e.getUserAccount().getUsername().equals("candidate2")) {
					candidate = e;
					break;
				}
			}
			
			Curricula c = candidate.getCurriculas().get(0);

			miscellaneousrecordService.save(miscellaneousrecord, c.getId());
			miscellaneousrecordService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
