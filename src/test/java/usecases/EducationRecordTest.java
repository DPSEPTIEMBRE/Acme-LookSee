package usecases;

import java.util.ArrayList;
import java.util.Date;
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
import domain.EducationRecord;
import services.CandidateService;
import services.EducationRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EducationRecordTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private EducationRecordService educationrecordService;
	@Autowired
	private CandidateService candidateService;

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "diplomaTitle", new Date(), new Date(), "institution", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false,
				null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "diplomaTitle", new Date(), new Date(), "institution", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false,
				null);

	}

	//Test #03: Empty fields and null date. Expected false.
	@Test
	public void negativeTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", new Date(), null, "", "", comments, false, ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", new Date(), new Date(), "", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false, ConstraintViolationException.class);

	}

	//Test #05: All fields null. Expected false.
	@Test
	public void negativeTest2() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", null, null, null, null, null, comments, null, ConstraintViolationException.class);

	}

	//Ancillary tests.
	@Test
	public void driver() {
		List<String> comments = new ArrayList<String>();
		
		template("candidate2", "diplomaTitle", new Date(), new Date(), "institution", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false,
				null);
		template("candidate2", "diplomaTitle", new Date(), new Date(), "institution", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false,
				null);
		template("candidate2", "diplomaTitle", null, new Date(), "institution", "", comments, false,
				ConstraintViolationException.class);
		template("candidate2", "diplomaTitle", new Date(), null, "institution", "http://simpleicon.com/wp-content/uploads/camera.png", comments, false,
				ConstraintViolationException.class);
		template("candidate2", "diplomaTitle", new Date(), new Date(), "institution", "http://simpleicon.com/wp-content/uploads/camera.png", null, false,
				ConstraintViolationException.class);
	}

	/*
	 * 12.3: A candidate must be able to edit his or her curricula.
	 */
	protected void template(final String username, final String diplomaTitle, final Date initialStudying,
			final Date finalStudying, final String institution, final String attachment, final List<String> comments,
			final Boolean copy, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			EducationRecord educationrecord = educationrecordService.create();
			educationrecord.setDiplomaTitle(diplomaTitle);
			educationrecord.setInitialStudying(initialStudying);
			educationrecord.setFinalStudying(finalStudying);
			educationrecord.setInstitution(institution);
			educationrecord.setAttachment(attachment);
			educationrecord.setComments(comments);
			educationrecord.setCopy(copy);
			
			Candidate candidate = candidateService.findAll().iterator().next();
			
			for(Candidate e : candidateService.findAll()) {
				if(e.getUserAccount().getUsername().equals("candidate2")) {
					candidate = e;
					break;
				}
			}
			
			Curricula c = candidate.getCurriculas().get(0);

			educationrecordService.save(educationrecord, c.getId());
			educationrecordService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
