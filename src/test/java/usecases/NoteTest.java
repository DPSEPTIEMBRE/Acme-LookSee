package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Candidate;
import domain.Curricula;
import domain.Note;
import domain.StatusNote;
import services.CandidateService;
import services.CurriculaService;
import services.NoteService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class NoteTest extends AbstractTest {

	// System under test ------------------------------------------------------
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private CurriculaService curriculaservice;
	
	@Autowired
	private CandidateService candidateService;
	
	
	//Templates
	
	/*
	 * 14.3: A candidate can list the notes a verifier has written about him or her.
	 */
	public void listTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "candidate1" || username == "candidate2");
			Candidate c = candidateService.findAll().iterator().next();
			Integer id = c.getId();
			noteService.list_group_by(id);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 14.4: A candidate can reply to one of his or her notes.
	 */
	public void remarkTemplate(final String username, String remark, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "candidate1" || username == "candidate2");
			Assert.notNull(remark);
			Note n = noteService.findAll().iterator().next();
			n.setRemark(remark);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 16.4: A verifier must be able to write a note.
	 */
	protected void template(final String username, final Date createdMoment, final String remark, final String reply,
			final Date replyMoment, final StatusNote status, final Curricula curricula, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			Curricula c = curriculaservice.findAll().iterator().next();

			Note note = noteService.create(c);
			note.setCreatedMoment(createdMoment);
			note.setRemark(remark);
			note.setReply(reply);
			note.setReplyMoment(replyMoment);
			note.setStatus(status);
			note.setCurricula(curricula);

			noteService.save(note);
			noteService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
	
	/*
	 * 16.5, 16.6: A verifier must be able to display his or her notes grouped by candidate or by status.
	 */
	public void displayTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "verifier1" || username == "verifier2");
			noteService.list_group_by(0);
			noteService.list_group_by(1);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 16.7: A verifier can change the status of a note.
	 */
	public void statusTemplate(final String username, String status, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "verifier1" || username == "verifier2");
			Assert.isTrue(status == "PENDING" || status == "CANCELLED" || status == "CORRECTED" || status == "REJECTED");
			StatusNote s = new StatusNote();
			s.setValue(status);
			Note n = noteService.findAll().iterator().next();
			n.setStatus(s);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "remark", "reply", new Date(), status, curricula, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "remark", "reply", new Date(), status, curricula, null);

	}

	//Test #03: Empty fields. Expected false.
	@Test
	public void negativeTest0() {

		StatusNote status = new StatusNote();
		status.setValue("value");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "", "", new Date(), status, curricula, ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "", "", new Date(), status, curricula, ConstraintViolationException.class);

	}

	//Test #05: Null fields. Expected false.
	@Test
	public void negativeTest2() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", null, null, null, null, status, curricula, ConstraintViolationException.class);

	}
	
	@Test
	public void listDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			//{"candidate1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"administrator", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void remarkDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"candidate1", "remark", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "remark", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null remark. Expected false.
			{"candidate1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.remarkTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	@Test
	public void displayDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"verifier1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"administrator", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.displayTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void statusDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"verifier1", "CANCELLED", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "CANCELLED", IllegalArgumentException.class},
				
			//Test #03: Attempt to change status to a nonexistent status. Expected false.
			{"verifier1", "aeiou", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.statusTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
