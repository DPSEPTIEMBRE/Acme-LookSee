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
import domain.ProfessionalRecord;
import services.CandidateService;
import services.ProfessionalRecordService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProfessionalRecordTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ProfessionalRecordService professionalrecordService;
	@Autowired
	private CandidateService candidateService;

	@Test
	public void positiveTest0() {

		List<String> comments = new ArrayList<String>();
		comments.add("Hello world");
		template("candidate2", "companyName", new Date(), new Date(), "role", "https://www.w3schools.com/css/img_fjords.jpg", comments, false, null);

	}

	@Test
	public void positiveTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "companyName", new Date(), new Date(), "role", "https://www.w3schools.com/css/img_fjords.jpg", comments, false, null);

	}

	@Test
	public void negativeTest0() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", new Date(), new Date(), "", "https://www.w3schools.com/css/img_fjords.jpg", comments, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", "", new Date(), new Date(), "a role", "", comments, false, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		List<String> comments = new ArrayList<String>();
		template("candidate2", null, null, null, null, null, comments, null, ConstraintViolationException.class);

	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final String companyName, final Date initialWork,
			final Date finalWork, final String role, final String attachment, final List<String> comments,
			final Boolean copy, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			ProfessionalRecord professionalrecord = professionalrecordService.create();
			professionalrecord.setCompanyName(companyName);
			professionalrecord.setInitialWork(initialWork);
			professionalrecord.setFinalWork(finalWork);
			professionalrecord.setRole(role);
			professionalrecord.setAttachment(attachment);
			professionalrecord.setComments(comments);
			professionalrecord.setCopy(copy);
			
			Candidate candidate = candidateService.findAll().iterator().next();
			
			for(Candidate e : candidateService.findAll()) {
				if(e.getUserAccount().getUsername().equals("candidate2")) {
					candidate = e;
					break;
				}
			}
			
			Curricula c = candidate.getCurriculas().get(0);

			professionalrecordService.save(professionalrecord, c.getId());
			professionalrecordService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
