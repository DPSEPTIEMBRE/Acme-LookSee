package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {


	//Compa�ia por oferta
	@Query("select o.company from Offer o where o.id=?1 ")
	Company companyByOffer(int offer_id);

	//Las compa�ias ordenadas por su numero de ofertas
	@Query("select c from Company c order by c.offers.size DESC")
	List<Company> orderByNumOffers();

	//La media de ofertas por compa�ia
	@Query("select avg(c.offers.size) from Company c")
	Double avgNumberOfferByCompany();

	//La compa�ia que han registrado mas ofertas
	@Query("select c from Company c where c.offers.size = (select max(c.offers.size) from Company c) ")
	List<Company> companyMaxOffers();
	
	@Query("select c from Company c where c.userAccount.username = ?1")
	Company selectByUsername(String username);




}
