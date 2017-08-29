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
	
	
	//Templates
	
	/*
	 * 26.2: An authenticated actor must be able to manage his or her message folders.
	 */
	protected void template(final String username, final String folderName, final List<MailMessage> messages,
			final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			Assert.notNull(username);
			folderService.findAll();

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
	
	//Drivers

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "folderName_2", messages, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "folderName", messages, null);

	}
	
	//Test #03: Correct number of folders. Expected true.
	@Test
	public void positiveTest2() {

		Assert.isTrue(folderService.createDefaultFolders().size() == 4);
	}

	//Test #04: Attempt to create a folder with a protected name. Expected false.
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

	//Test #05: Name field empty. Expected false.
	@Test
	public void negativeTest1() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", "", messages, ConstraintViolationException.class);

	}

	//Test #06: Name field null. Expected false.
	@Test
	public void negativeTest2() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("candidate2", null, messages, ConstraintViolationException.class);

	}
}
