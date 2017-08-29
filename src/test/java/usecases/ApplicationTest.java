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

import domain.Application;
import domain.Candidate;
import domain.Company;
import domain.Curricula;
import domain.Offer;
import domain.StatusApplication;
import services.ApplicationService;
import services.CandidateService;
import services.CompanyService;
import services.CurriculaService;
import services.OfferService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class ApplicationTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private OfferService offerservice;
	
	@Autowired
	private CurriculaService curriculaservice;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private CompanyService companyService;
	
	//Templates
	
	/*
	 * 12.4: A candidate must be able to apply for an offer.
	 */
	protected void template(final String username, final Date createMoment, final StatusApplication status, final Curricula curricula, final Offer offer, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			Application application = applicationService.create();
			application.setCreateMoment(createMoment);
			application.setStatus(status);
			application.setCurricula(curriculaservice.clone(curricula));
			application.setOffer(offer);

			application = applicationService.save(application);

			Candidate candidate = candidateService.findAll().iterator().next();
			candidate.getApplications().add(application);
			
			candidateService.saveEditing(candidate);
			
			Offer o = offerService.findAll().iterator().next();
			o.getApplications().add(application);
			offerservice.save(offer);
			
			applicationService.flush();
			
			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
	}
	
	/*
	 * 12.5: A candidate can list and sort his or her applications by status, application moment or deadline.
	 */
	public void sortTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "candidate1" || username == "candidate2");
			Candidate c = candidateService.findAll().iterator().next();
			Integer id = c.getId();
			applicationService.applicationOrderCreatedMoment(id);
			applicationService.applicationOrderLimit(id);
			applicationService.applicationOrderStatus(id);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 13.2: A company can accept or reject an application for one of their courses. 
	 */
	public void rejectTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "company1" || username == "company2");
			Company c = companyService.findAll().iterator().next();
			Integer id = c.getId();
			applicationService.rejectSchedule(id);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	
	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, null);
		
	}
	
	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, null);
		
	}
	
	//Test #03: Wrong status. Expected false.
	@Test
	public void negativeTest0() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	//Test #04: Wrong status. Expected false.
	@Test
	public void negativeTest1() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	//Test #05: Wrong status and date. Expected false.
	@Test
	public void negativeTest2() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", null, status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	@Test
	public void sortDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"candidate1", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"academy1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.sortTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void rejectDriver() {

		final Object testingData[][] = {
					
			//Test #01: . Expected true.
			{"company1", null},
				
			//Test #02: . Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: . Expected false.
			{"candidate2", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.rejectTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
