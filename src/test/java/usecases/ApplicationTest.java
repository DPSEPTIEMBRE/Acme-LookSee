package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Application;
import domain.Candidate;
import domain.Curricula;
import domain.Offer;
import domain.StatusApplication;
import services.ApplicationService;
import services.CandidateService;
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

	@Test
	public void positiveTest0() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, null);
		
	}
	
	@Test
	public void positiveTest1() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("PENDING");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, null);
		
	}
	
	@Test
	public void negativeTest0() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	@Test
	public void negativeTest1() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", new Date(), status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	@Test
	public void negativeTest2() {
		
		StatusApplication status = new StatusApplication();
		status.setValue("status");

		Curricula curricula = curriculaservice.findAll().iterator().next();

		Offer offer = offerservice.findAll().iterator().next();

		template("candidate1", null, status, curricula, offer, ConstraintViolationException.class);
		
	}
	
	// Ancillary methods ------------------------------------------------------
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
}
