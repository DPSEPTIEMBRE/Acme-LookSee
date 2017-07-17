package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
	@Query("select o from Offer o")
	public List<Offer> offers();
	
	@Query("select o from Offer o where o.deadlineApply >= CURRENT_DATE and"
			+ "(?1 is null or o.title like '%?1%' or o.description like '%?1%') and"
			+ "(?2 is null or (o.minRange <= ?2 and o.maxRange >= ?2)) and"
			+ "(?3 is null or o.currency like '%?3%'")
	public List<Offer> offersSearch(String key, Integer price, String currency);
	
	//Crear un buscador de ofertas en el cual él/ella pueda especificar una palabra clave y un rango de salarios.
	@Query("select o from Offer o where (?1 is null or o.title like '%?1%' or o.description like '%?1%') and (?2 is null or ?3 is null or (minRange <= ?2 and maxRange >= ?3))")
	public List<Offer> offerSearchRange(String key, Double min, Double max);
}
