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

import services.CandidateService;
import services.FolderService;
import services.MailMessageService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Candidate;
import domain.Folder;
import domain.MailMessage;
import domain.Priority;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MailMessageTest extends AbstractTest {

	// System under test ------------------------------------------------------
	
	@Autowired
	private MailMessageService mailmessageService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private FolderService folderService;
	
	
	//Templates
	
	/*
	 * 26.1: An authenticated actor must be able to exchange messages with other actors.
	 */
	protected void template(final String username, final Date sent, final String subject, final String body,
			final Priority priority, final Actor sender, final Actor recipient, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Assert.notNull(username);
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
	
	/*
	 * 26.3: An actor can manage his or her messages, such as moving them and deleting them.
	 */
	public void manageTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
			
			Assert.notNull(username);
			MailMessage m = mailmessageService.create();
			Folder f = folderService.findOne(id);
			mailmessageService.moveTo(m, f);
			mailmessageService.flush();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers

	//Test #01: All parameters correct. Expect true.
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
	
	//Test #02: All parameters correct. Expected true.
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

	//Test #03: Empty sender. Expected false.
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

	//Test #04: Empty date. Expected false.
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

	//Test #05: Sending a correct message. Expected true.
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
	
	//Test #06: Attempt to send a message without authentication. Expected false.
	@Test
	public void negativeTest3() {
		try {
			mailmessageService.send("hello", "body sample", "LOW", "falseMail@false.com");
		} catch(Exception ex) {
			checkExceptions(IllegalArgumentException.class, ex.getClass());
		}
	}
	
	@Test
	public void manageDriver() {

		final Object testingData[][] = {
					
			//Test #01: . Expected true.
			//{"dancer1", 330, null},
				
			//Test #02: . Expected false.
			{null, 330, IllegalArgumentException.class},
				
			//Test #03: . Expected false.
			{"dancer1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
