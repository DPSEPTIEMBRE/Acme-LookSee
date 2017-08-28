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
import org.springframework.util.Assert;

import domain.Candidate;
import domain.Folder;
import domain.MailMessage;
import services.CandidateService;
import services.FolderService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private FolderService folderService;
	@Autowired
	private CandidateService candidateService;

	@Test
	public void positiveTest0() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "folderName_2", messages, null);

	}

	@Test
	public void positiveTest1() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "folderName", messages, null);

	}
	
	@Test
	public void positiveTest2() {

		Assert.isTrue(folderService.createDefaultFolders().size() == 4);
	}

	@Test
	public void negativeTest0() {

		Candidate candidate = candidateService.findAll().iterator().next();
		
		for(Candidate e : candidateService.findAll()) {
			if(e.getUserAccount().getUsername().equals("candidate2")) {
				candidate = e;
				break;
			}
		}
		
		Folder folder = candidate.getFolders().iterator().next();
		
		folder.setFolderName("inbox");
		
		try {
			folderService.saveCreate(folder);
		} catch(Exception e) {
			checkExceptions(IllegalArgumentException.class, e.getClass());
		}

	}

	@Test
	public void negativeTest1() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "", messages, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", null, messages, ConstraintViolationException.class);

	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final String folderName, final List<MailMessage> messages,
			final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Folder folder = folderService.create();
			folder.setFolderName(folderName);
			folder.setMessages(messages);

			Candidate candidate = candidateService.findAll().iterator().next();
			
			for(Candidate e : candidateService.findAll()) {
				if(e.getUserAccount().getUsername().equals("candidate2")) {
					candidate = e;
					break;
				}
			}
			
			candidate.getFolders().add(folderService.save(folder));
			folderService.flush();
			
			candidateService.saveEditing(candidate);

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
