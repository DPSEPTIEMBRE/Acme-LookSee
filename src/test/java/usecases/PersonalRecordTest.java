package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Candidate;
import domain.Curricula;
import domain.PersonalRecord;
import services.CandidateService;
import services.PersonalRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PersonalRecordTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private PersonalRecordService personalrecordService;
	@Autowired
	private CandidateService candidateService;

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		template("candidate2", "fullName", "https://www.w3schools.com/css/img_fjords.jpg", "mail@mail.es", "+5 (10) 9132", "http://linkedIn.es", false, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		template("candidate2", "fullName", "https://www.w3schools.com/css/img_fjords.jpg", "mail@mail.es", "+5 (15) 9932", "http://linkedIn.es", false, null);

	}

	//Test #03: Empty fields. Expected false.
	@Test
	public void negativeTest0() {

		template("candidate2", "", "", "https://www.w3schools.com/css/img_fjords.jpg", "", "http://linkedIn.es", false, ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		template("candidate2", "", "", "", "", "http://linkedIn.es", false, ConstraintViolationException.class);

	}

	//Test #05: Null fields. Expected false.
	@Test
	public void negativeTest2() {

		template("candidate2", null, null, null, null, null, null, ConstraintViolationException.class);

	}

	/*
	 * 12.3: A candidate must be able to edit his or her curricula.
	 */
	protected void template(final String username, final String fullName, final String picture, final String email,
			final String phone, final String linkedIn, final Boolean copy, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			PersonalRecord personalrecord = personalrecordService.create();
			personalrecord.setFullName(fullName);
			personalrecord.setPicture(picture);
			personalrecord.setEmail(email);
			personalrecord.setPhone(phone);
			personalrecord.setLinkedIn(linkedIn);
			personalrecord.setCopy(copy);

			Candidate candidate = candidateService.findAll().iterator().next();
			
			for(Candidate e : candidateService.findAll()) {
				if(e.getUserAccount().getUsername().equals("candidate2")) {
					candidate = e;
					break;
				}
			}
			
			Curricula c = candidate.getCurriculas().get(0);
			
			personalrecordService.save(personalrecord, c.getId());
			personalrecordService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
