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

import domain.Curricula;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.Note;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
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
	public void driver() {
		List<EducationRecord> educationRecords = new ArrayList<EducationRecord>();

		List<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<MiscellaneousRecord>();
		List<EndorserRecord> endorserRecords = new ArrayList<EndorserRecord>();
		List<Note> notes = new ArrayList<Note>();
		
		template("candidate2", "2017-07-10-asdfg", educationRecords, personalrecordservice.clone(personalrecordservice.findAll().iterator().next()), professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, null);
		template("candidate1", "2017-07-10-addfg", educationRecords, personalrecordservice.clone(personalrecordservice.findAll().iterator().next()), professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, null);
		template("candidate2", "2017-", null, personalrecordservice.clone(personalrecordservice.findAll().iterator().next()), professionalRecords, miscellaneousRecords,
				endorserRecords, notes, false, IllegalArgumentException.class);
		template("candidate2", "", educationRecords, null, professionalRecords, null,
				endorserRecords, notes, false, IllegalArgumentException.class);
		template("candidate1", null, educationRecords, personalrecordservice.clone(personalrecordservice.findAll().iterator().next()), professionalRecords, miscellaneousRecords,
				endorserRecords, notes, null, ConstraintViolationException.class);
	}

	// Ancillary methods ------------------------------------------------------
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
}
