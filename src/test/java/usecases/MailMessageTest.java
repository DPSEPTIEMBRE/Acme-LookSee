package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Actor;
import domain.Candidate;
import domain.MailMessage;
import domain.Priority;
import services.CandidateService;
import services.MailMessageService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MailMessageTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private MailMessageService mailmessageService;
	@Autowired
	private CandidateService candidateService;

	@Test
	public void positiveTest0() {

		Priority priority = new Priority();
		priority.setValue("NEUTRAL");

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}

		template("candidate2", new Date(), "subject", "body", priority, candidate, candidate, null);

	}

	@Test
	public void positiveTest1() {

		Priority priority = new Priority();
		priority.setValue("LOW");

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}

		template("candidate2", new Date(), "subject0", "body sample body", priority, candidate, candidate, null);

	}

	@Test
	public void negativeTest0() {

		Priority priority = new Priority();
		priority.setValue("LOW");

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}

		template("candidate2", new Date(), "subject0", "body sample body", priority, null, candidate, ConstraintViolationException.class);

	}

	@Test
	public void positiveTest2() {

		Priority priority = new Priority();
		priority.setValue("LOW");

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}

		template("candidate2", null, "subject0", "body sample body", priority, candidate, candidate, ConstraintViolationException.class);

	}

	@Test
	public void positiveTest3() {

		Priority priority = new Priority();
		priority.setValue("LOW");

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}

		mailmessageService.send("hello", "body sample", "LOW", candidate.getEmail());
	}
	
	@Test
	public void negativeTest3() {
		try {
			mailmessageService.send("hello", "body sample", "LOW", "falseMail@false.com");
		} catch(Exception ex) {
			checkExceptions(IllegalArgumentException.class, ex.getClass());
		}
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final Date sent, final String subject, final String body,
			final Priority priority, final Actor sender, final Actor recipient, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			MailMessage mailmessage = mailmessageService.create();
			mailmessage.setSent(sent);
			mailmessage.setSubject(subject);
			mailmessage.setBody(body);
			mailmessage.setPriority(priority);
			mailmessage.setSender(sender);
			mailmessage.setRecipient(recipient);

			mailmessageService.save(mailmessage);
			mailmessageService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
