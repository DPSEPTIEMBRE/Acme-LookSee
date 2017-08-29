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
	
	
	//Templates
	
	/*
	 * 14.1, 14.2: A candidate can create a cached search which will return no more than 10 offers and cache them for an hour.
	 */
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

	//Drivers

	//Test #01: Correct search. Expected true.
	@Test
	public void positiveTest0() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "sessionId", resultIds, 10, null);

	}
	
	//Test #02: Correct search. Expected true.
	@Test
	public void positiveTest1() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "sessionId", resultIds, 10, null);

	}

	//Test #03: Search with a nonexistent session ID. Expected false.
	@Test
	public void negativeTest0() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", "", resultIds, 10, ConstraintViolationException.class);

	}

	//Test #04: Search with a null session ID and amount of results to return. Expected false.
	@Test
	public void negativeTest1() {

		List<Integer> resultIds = new ArrayList<Integer>();

		template("administrator", null, resultIds, null, ConstraintViolationException.class);

	}
}
