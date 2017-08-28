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

import domain.ActivityReport;
import services.ActivityReportService;
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

	@Test
	public void positiveTest0() {
		
		template("company1", new Date(), "title0", "description0", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
		
	}
	
	@Test
	public void positiveTest1() {
		
		template("company1", new Date(), "title1", "description1", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
		
	}
	
	@Test
	public void negativeTest0() {
		
		template("company1", new Date(), "", "description", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	@Test
	public void negativeTest1() {
		
		template("company1", new Date(), "title", "", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
		
	}
	
	@Test
	public void negativeTest2() {
		
		template("company1", null, null, null, null, ConstraintViolationException.class);
		
	}
	
//	@Test
//	public void driver() {
//		template("company1", new Date(), "title0", "description0", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
//		template("company1", new Date(), "title1", "description1", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), null);
//		template("company1", new Date(), "", "description", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
//		template("company1", new Date(), "title", "", Arrays.asList("http://saveabandonedbabies.org/wp-content/uploads/2015/08/default.png"), ConstraintViolationException.class);
//		template("company1", null, null, null, null, ConstraintViolationException.class);
//	}
	
	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final Date writtenMoment, final String title, final String description, final List<String> attachments,
			final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
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
}
