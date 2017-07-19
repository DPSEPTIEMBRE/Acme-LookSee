package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
	
	//Lista de ofertas
	@Query("select o from Offer o")
	List<Offer> offers();
	
	//Ofertas por compañia
	@Query("select o from Company c join c.offers o where c.id = ?1")
	List<Offer> offersByCompany(int company_id);

}
