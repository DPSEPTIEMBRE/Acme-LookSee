package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	@Query("select c from Company c")
	public Company companies();
	
	@Query("select c from Company c order by c.offers.size DESC")
	List<Company> orderByNumOffers();
	
	@Query("select avg(c.offers.size) from Company c")
	Double avgNumberOfferByCompany();
	
	@Query("select c from Company c where c.offers.size = (select max(c.offers.size) from Company c) ")
	List<Company> companyMaxOffers();
	
	//El mínimo, máximo y media de pagos por compañía.
	@Query("select min(c.payments.size), max(c.payments.size), avg(c.payments.size) from Company c")
	public Object[] minMaxAvgPaymentsByCompany();
	
	//El ratio de compañías con pagos no finalizados.
	@Query("select count(c) from Company c join c.payments pa where pa.createMoment + 7 <= CURRENT_TIMESTAMP/(select count(c) from Company c)")
	public Double RatioCompanyNoFinish();



}
