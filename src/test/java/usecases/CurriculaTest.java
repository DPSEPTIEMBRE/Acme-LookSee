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
import domain.Curricula;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.Note;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import services.CandidateService;
import services.CurriculaService;
import services.PersonalRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CurriculaTest extends AbstractTest {

	// System under test ------------------------------------------------------
	
	@Autowired
	private CurriculaService curriculaService;
	
	@Autowired
	private PersonalRecordService personalrecordservice;
	
	@Autowired
	private CandidateService candidateService;
	
	//Template
	
	/*
	 * 12.1: A candidate can list his or her curricula.
	 */
	public void listTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
			
			Assert.isTrue(username == "candidate1" || username == "candidate2");
			Candidate c = candidateService.findAll().iterator().next();
			curriculaService.curriculasOf(c.getId());

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 12.2: A candidate can search curricula using a keyword.
	 */
	public void searchTemplate(final String username, String q, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "candidate1" || username == "candidate2");
			curriculaService.findAll(q);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 12.3: A candidate must be able to edit his or her curricula.
	 */
	protected void template(final String username, final String ticker, final List<EducationRecord> educationRecords,
			final PersonalRecord personalRecord, final List<ProfessionalRecord> professionalRecords,
			final List<MiscellaneousRecord> miscellaneousRecords, final List<EndorserRecord> endorserRecords,
			final List<Note> notes, final Boolean copy, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Curricula curricula = curriculaService.create();
			curricula.setTicker(ticker);
			curricula.setEducationRecords(educationRecords);
			curricula.setPersonalRecord(personalRecord);
			curricula.setProfessionalRecords(professionalRecords);
			curricula.setMiscellaneousRecords(miscellaneousRecords);
			curricula.setEndorserRecords(endorserRecords);
			curricula.setNotes(notes);
			curricula.setCopy(copy);

			curriculaService.save(curricula);
			curriculaService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
	
	/*
	 * 16.1, 16.2, 16.3: A verifier can list all the curricula in the system grouped by candidate, offer or company.
	 */
	public void listVerifierTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "verifier1" || username == "verifier2");
			curriculaService.curriculasGroupByCompany();
			curriculaService.curriculasGroupByOffer();
			curriculaService.getCurriculasGroupByCandidate();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Driver

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		PersonalRecord personalrecord = personalrecordservice.clone(personalrecordservice.findAll().iterator().next());

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		template("candidate2", "2017-07-10-ADTfg", educationRecords, personalrecord, professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		PersonalRecord personalrecord = personalrecordservice.clone(personalrecordservice.findAll().iterator().next());

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		template("candidate1", "2017-07-10-aDTfg", educationRecords, personalrecord, professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, null);

	}

	//Test #03: Empty curriculum. Expected false.
	@Test
	public void negativeTest0() {

		PersonalRecord personalrecord = personalrecordservice.clone(personalrecordservice.findAll().iterator().next());

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		template("candidate2", "", null, personalrecord, professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, IllegalArgumentException.class);

	}

	//Test #04: Empty curriculum and faulty records. Expected false.
	@Test
	public void negativeTest1() {

		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		PersonalRecord personalrecord = personalrecordservice.clone(personalrecordservice.findAll().iterator().next());

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		template("candidate2", "", educationRecords, personalrecord, professionalRecords, null,
				endorserRecords, notes, false, IllegalArgumentException.class);

	}

	//Test #05: Null copy value. Expected false.
	@Test
	public void negativeTest2() {

		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		PersonalRecord personalrecord = personalrecordservice.clone(personalrecordservice.findAll().iterator().next());
		personalrecord.setLinkedIn("http://simpleicon.com/wp-content/uploads/camera.png");
		personalrecord.setPicture("http://simpleicon.com/wp-content/uploads/camera.png");

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		template("candidate1", null, educationRecords, personalrecord, professionalRecords, miscellaneousRecords,
				endorserRecords, notes, null, ConstraintViolationException.class);

	}
	
	@Test
	public void listDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"candidate1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by aunauthorized user. Expected false.
			{"administrator", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void searchDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"candidate1", "personal", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "personal", IllegalArgumentException.class},
				
			//Test #03: Attempt to access by aunauthorized user. Expected false.
			{"academy1", "personal", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.searchTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	@Test
	public void listVerifierDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"verifier1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by aunauthorized user. Expected false.
			{"candidate1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listVerifierTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
