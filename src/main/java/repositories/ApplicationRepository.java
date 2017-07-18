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
	
	//La media, m�nimo y m�ximo de solicitudes por candidato
	@Query("select avg(c.applications.size),max(c.applications.size),min(c.applications.size) from Candidate c")
	Object[] AvgMaxMinApplicationsByCandidate();
	
	//La media, m�nimo y m�ximo de solicitudes por oferta
	@Query("select avg(o.applications.size),max(o.applications.size),min(o.applications.size) from Offer o")
	Object[] AvgMaxMinApplicationsByOffers();
	
	//La media, m�nimo y m�ximo de solicitudes pendientes por compa��a.
	@Query("select avg(a.size),max(a.size),min(a.size) from Company c join c.offers o join o.applications a")
	Object[] AvgMaxMinApplicationsPendingByCompany();
	
	

}