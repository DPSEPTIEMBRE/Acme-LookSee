
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

import domain.Cache;
import services.CacheService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CacheTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private CacheService cacheService;


	@Test
	public void positiveTest0() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "sessionId", resultIds, 10, null);

	}

	@Test
	public void positiveTest1() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "sessionId", resultIds, 10, null);

	}

	@Test
	public void negativeTest0() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "", resultIds, 10, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest1() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "", resultIds, 10, ConstraintViolationException.class);

	}

	@Test
	public void negativeTest2() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", null, resultIds, null, ConstraintViolationException.class);

	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final String sessionId, final List<Integer> resultIds, final Integer cacheType, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Cache cache = cacheService.createOfferByIds(resultIds, sessionId);
			cache.setSessionId(sessionId);
			cache.setResultIds(resultIds);
			cache.setCacheType(cacheType);

			cacheService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
