package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends
		JpaRepository<Application, Integer> {
	
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by status")
	List<Application> applicationOrderStatus(int candidate_id);
	
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by createMoment DESC")
	List<Application> applicationOrderCreatedMoment(int candidate_id);
	
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by createMoment+7 DESC")
	List<Application> applicationOrderLimit(int candidate_id);

}
