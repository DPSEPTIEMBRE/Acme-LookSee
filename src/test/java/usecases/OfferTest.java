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
import org.springframework.util.Assert;

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
	private CompanyService companyService;
		
	
	//Templates
	
	/*
	 * 10.4: Any actor can browse the list of companies and navigate to their offers.
	 */
	public void browseTemplate(final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			
			Assert.notNull(id);
			Offer o = offerService.findOne(id);
			o.getCurrency();
			
			offerService.findAll();
			companyService.companyByOffer(id);
			
			
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 10.5: Search for offers that contain a single key word in their title or description,
	 * 		 provide a given salary (in a given currency), and are still open.
	 */
	public void searchTemplate(String q, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Assert.notNull(q);
			Assert.isTrue(q != "");
			offerService.findQ(q, false);
			
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
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
	
	//Drivers

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {

		Company company = companyService.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "title", "description", 50d, 50d, "currency", new Date(), company, applications, null);

	}

	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {

		Company company = companyService.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "title", "description", 50d, 50d, "currency", new Date(), company, applications, null);

	}

	//Test #03: Empty fields. Expected false.
	@Test
	public void negativeTest0() {

		Company company = companyService.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "", "", 50d, 50d, "", new Date(), company, applications,
				ConstraintViolationException.class);

	}

	//Test #04: Empty fields. Expected false.
	@Test
	public void negativeTest1() {

		Company company = companyService.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", "", "", 50d, 50d, "", new Date(), company, applications,
				ConstraintViolationException.class);

	}

	//Test #05: Null fields. Expected false.
	@Test
	public void negativeTest2() {

		Company company = companyService.findAll().iterator().next();

		List<Application> applications = new ArrayList<Application>();
		template("company1", null, null, null, null, null, null, company, applications,
				ConstraintViolationException.class);

	}
	
	@Test
	public void browseDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{352, null},
				
			//Test #02: Attempt to access a nonexistent company. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access a different entity as company. Expected false.
			{332, NullPointerException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseTemplate((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	@Test
	public void searchDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct search. Expected true.
			{"offer", null},
				
			//Test #02: Attempt to introduce null. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to search an empty string. Expected false.
			{"", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.searchTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
