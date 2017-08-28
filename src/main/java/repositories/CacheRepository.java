package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cache;

@Repository
public interface CacheRepository extends JpaRepository<Cache, Integer> {

	@Query("select c from Cache c where c.sessionId = ?1")
	Cache selectBySessionId(String session_id);
	
	@Query("select count(c) from Cache c where c.sessionId = ?1")
	Long existBySessionId(String session_id);
}
