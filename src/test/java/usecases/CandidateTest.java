
package usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.ActivityReport;
import domain.Application;
import domain.Candidate;
import domain.Curricula;
import domain.Folder;
import security.Authority;
import security.UserAccount;
import services.CandidateService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CandidateTest extends AbstractTest {

	//The SUT
	
	@Autowired
	private CandidateService candidateService;


	//Templates

	/*
	 * 10.1: An actor who is not authenticated must be able to register as candidate.
	 */
	protected void template(final String actorname, final String surname, final String email, final String phone, final String address, final UserAccount userAccount, final List<ActivityReport> activities, final List<Folder> folders,
		final List<Curricula> curriculas, final List<Application> applications, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Candidate candidate = candidateService.findAll().iterator().next();
			candidate.setactorName(actorname);
			candidate.setSurname(surname);
			candidate.setEmail(email);
			candidate.setPhone(phone);
			candidate.setAddress(address);
			candidate.setUserAccount(userAccount);
			candidate.setActivities(activities);
			candidate.setFolders(folders);
			candidate.setCurriculas(curriculas);
			candidate.setApplications(applications);

			candidateService.save(candidate);
			candidateService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);

	}
	
	/*
	 * 11.2: An authenticated actor can edit his or her personal data.
	 */
	public void editTemplate(final String username, String surname, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(surname);
			Candidate c = candidateService.findAll().iterator().next();
			c.setSurname(surname);
			candidateService.save(c);

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

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("candidate1", "candi", "candi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("candidate2", "candi", "candi@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), null);

	}

	//Test #03: All parameters correct. Expected true.
	@Test
	public void positiveTest2() {

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("candidate3", "candi", "cand3i@gmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), null);

	}

	//Test #04: Incorrect username. Expected false.
	@Test
	public void negativeTest0() {
		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("", "candi", "cand3i@gmail.com", "+34", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), ConstraintViolationException.class);

	}

	//Test #05: Incorrect mail. Expected false.
	@Test
	public void negativeTest1() {

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("candidate3", "candi", "cand3igmail.com", "+34 (88) 9999", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), ConstraintViolationException.class);

	}

	//Test #06: Incorrect phone number. Expected false.
	@Test
	public void negativeTest2() {

		Authority a = new Authority();
		a.setAuthority(Authority.CANDIDATE);
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(Arrays.asList(a));

		template("candidate3", "candi", "cand3i@gmail.com", "6555", "41100", userAccount, new ArrayList<ActivityReport>(), new ArrayList<Folder>(), new ArrayList<Curricula>(), new ArrayList<Application>(), ConstraintViolationException.class);

	}
	
	@Test
	public void editDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct edition. Expected true.
			{"candidate1", "surname", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "surname", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null value. Expected false.
			{"candidate1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
