package usecases;

import java.util.Arrays;
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

import domain.ActivityReport;
import domain.Company;
import services.ActivityReportService;
import services.CompanyService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class ActivityReportTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	
	@Autowired
	private ActivityReportService activityReportService;
	
	@Autowired
	private CompanyService companyService;
	
	
	//Templates
	
	/*
	 * 12.1: Any actor can display the activity records of a company.
	 */
	public void displayTemplate(final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			
			Company c = companyService.findOne(id);
			c.getAddress();
			activityReportService.ActivitiesByActor(id);
			
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 13.1: An authenticated actor must be able to manage his or her activity records.
	 */
	protected void template(final String username, final Date writtenMoment, final String title, final String description, final List<String> attachments,
			final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			Assert.notNull(username);			
			ActivityReport activityReport = activityReportService.create();
			activityReport.setAttachments(attachments);
			activityReport.setDescription(description);
			activityReport.setTitle(title);
			activityReport.setWrittenMoment(writtenMoment);
			
			activityReportService.save(activityReport);
			activityReportService.flush();
			
			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
	}

	//Test #01: All parameters correct. Expected true.
	@Test
	public void positiveTest0() {
		
		template("company1", new Date(), "title0", "description0", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
		
	}
	
	//Test #02: All parameters correct. Expected true.
	@Test
	public void positiveTest1() {
		
		template("company1", new Date(), "title1", "description1", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
		
	}
	
	//Test #03: Empty title. Expected false.
	@Test
	public void negativeTest0() {
		
		template("company1", new Date(), "", "description", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	//Test #04: Empty description. Expected false.
	@Test
	public void negativeTest1() {
		
		template("company1", new Date(), "title", "", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	//Test #05: Anonymous user and null date. Expected false.
	@Test
	public void negativeTest2() {
		
		template(null, null, "title1", "description1", Arrays.asList("picture"), IllegalArgumentException.class);
		
	}
	
	//Test #06: Attempt to access by anonymous user. Expected false.
	@Test
	public void negativeTest3() {
		
		template(null, new Date(), "title0", "description0", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), IllegalArgumentException.class);
		
	}
	
	//Test #07: Null title. Expected false.
	@Test
	public void negativeTest4() {
		
		template("company1", new Date(), null, "description0", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	//Test #08: Null description. Expected false.
	@Test
	public void negativeTest5() {
		
		template("company1", new Date(), "title0", null, Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	//Test #09: Null list of pictures. Expected false.
	@Test
	public void negativeTest6() {
		
		template("company1", new Date(), "title0", "description0", null, ConstraintViolationException.class);
		
	}
	
	//Test #10: All null fields. Expected false.
	@Test
	public void negativeTest7() {
		
		template("company1", null, null, null, null, ConstraintViolationException.class);
		
	}
	
	@Test
	public void displayDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{350, null},
				
			//Test #02: Attempt to access nonexistent company. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access another entity as company. Expected false.
			{330, NullPointerException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.displayTemplate((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
