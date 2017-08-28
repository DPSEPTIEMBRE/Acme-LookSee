package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Curricula;
import domain.Note;
import domain.StatusNote;
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

	@Test
	public void positiveTest0() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "remark", "reply", new Date(), status, curricula, null);

	}

	@Test
	public void positiveTest1() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "remark", "reply", new Date(), status, curricula, null);

	}

	@Test
	public void negativeTest0() {

		StatusNote status = new StatusNote();
		status.setValue("value");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "", "", new Date(), status, curricula, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", new Date(), "", "", new Date(), status, curricula, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		StatusNote status = new StatusNote();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		template("candidate2", null, null, null, null, status, curricula, ConstraintViolationException.class);

	}

	// Ancillary methods ------------------------------------------------------
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
}
