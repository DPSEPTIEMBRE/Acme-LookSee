package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends
JpaRepository<Application, Integer> {

	//Lista de solicitudes ordenadas por su estado
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by a.status")
	List<Application> applicationOrderStatus(int candidate_id);

	//Lista de solicitudes ordenadas por su fecha de creacion
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by a.createMoment DESC")
	List<Application> applicationOrderCreatedMoment(int candidate_id);

	//Lista de solicitudes ordenadas por su fecha limite
	@Query("select a from Candidate c join c.applications a where c.id = ?1 order by a.createMoment+7 DESC")
	List<Application> applicationOrderLimit(int candidate_id);

	//La media, mínimo y máximo de solicitudes por candidato
	@Query("select avg(c.applications.size),max(c.applications.size),min(c.applications.size) from Candidate c")
	Object[] AvgMaxMinApplicationsByCandidate();

	//La media, mínimo y máximo de solicitudes por oferta
	@Query("select avg(o.applications.size),max(o.applications.size),min(o.applications.size) from Offer o")
	Object[] AvgMaxMinApplicationsByOffers();

	//La media, mínimo y máximo de solicitudes pendientes por compañía.
	@Query("select avg(a.size),max(a.size),min(a.size) from Company c join c.offers o join o.applications a where a.status='PENDING'")
	Object[] AvgMaxMinApplicationsPendingByCompany();

	//La media, mínimo y máximo de solicitudes aceptadas por compañía.
	@Query("select avg(a.size),max(a.size),min(a.size) from Company c join c.offers o join o.applications a where a.status='ACCEPTED'")
	Object[] AvgMaxMinApplicationsAcceptedByCompany();

	//La media, mínimo y máximo de solicitudes rechazadas por compañía.
	@Query("select avg(a.size),max(a.size),min(a.size) from Company c join c.offers o join o.applications a where a.status='REJECTED'")
	Object[] AvgMaxMinApplicationsRejectedByCompany();



}
