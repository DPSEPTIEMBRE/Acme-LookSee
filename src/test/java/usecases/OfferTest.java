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

import domain.Application;
import domain.Company;
import domain.Offer;
import services.CompanyService;
import services.OfferService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OfferTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private OfferService offerService;
	@Autowired
	private CompanyService companyservice;

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		Company company = companyservice.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "title", "description", 50d, 50d, "currency", new Date(), company, applications, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		Company company = companyservice.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "title", "description", 50d, 50d, "currency", new Date(), company, applications, null);

	}

	//Test #03: Empty fields. Expected false.
	@Test
	public void negativeTest0() {

		Company company = companyservice.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "", "", 50d, 50d, "", new Date(), company, applications,
				ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		Company company = companyservice.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "", "", 50d, 50d, "", new Date(), company, applications,
				ConstraintViolationException.class);

	}

	//Test #05: Null fields. Expected false.
	@Test
	public void negativeTest2() {

		Company company = companyservice.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", null, null, null, null, null, null, company, applications,
				ConstraintViolationException.class);

	}

	/*
	 * 13.1: A company must be able to publish an offer.
	 */
	protected void template(final String username, final String title, final String description, final Double minRange,
			final Double maxRange, final String currency, final Date deadlineApply, final Company company,
			final List<Application> applications, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Offer offer = offerService.create();
			offer.setTitle(title);
			offer.setDescription(description);
			offer.setMinRange(minRange);
			offer.setMaxRange(maxRange);
			offer.setCurrency(currency);
			offer.setDeadlineApply(deadlineApply);
			offer.setCompany(company);
			offer.setApplications(applications);

			offerService.save(offer);
			offerService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
