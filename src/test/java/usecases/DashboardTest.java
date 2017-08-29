package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActivityReportService;
import services.ApplicationService;
import services.CandidateService;
import services.CompanyService;
import services.NoteService;
import services.PaymentService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DashboardTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ActivityReportService activityReportService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private PaymentService paymentService;

	
	//Templates
	
	/*
	 * 14.2: An administrator can display a dashboard with system information.
	 */
	public void dashboardTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "administrator");
			activityReportService.MinMaxAvgActiviesByActor();
			applicationService.AvgMaxMinApplicationsAcceptedByCompany();
			candidateService.orderByNumCurriculas();
			companyService.companyMaxOffers();
			noteService.avgNotesByVerifier();
			paymentService.minMaxAvgPaymentsByCompany();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers
	
	@Test
	public void dashboardDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"administrator", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"candidate2", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dashboardTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
