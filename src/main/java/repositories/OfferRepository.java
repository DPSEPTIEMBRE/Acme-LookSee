package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
	
	//Ofertas por compañia
	@Query("select c.offers from Company c where c.id = ?1")
	List<Offer> offersByCompany(int company_id);

}
