package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	//Media de pagos no finalizados por compa�ia
	@Query("select avg(c.payments.size) from Company c join c.payments pa where pa.createMoment+7 < CURRENT_TIMESTAMP")
	Double AvgPaymentsNoFinishByCompany();

	//El m�nimo, m�ximo y media de pagos por compa��a.
	@Query("select min(c.payments.size), max(c.payments.size), avg(c.payments.size) from Company c")
	Object[] minMaxAvgPaymentsByCompany();

	//El ratio de compa��as con pagos no finalizados.
	@Query("select count(c) from Company c join c.payments pa where pa.createMoment + 7 <= CURRENT_TIMESTAMP/(select count(c) from Company c)")
	Double RatioCompanyNoFinish();

	//Pagos por compa�ia.
	@Query("select c.payments from Company c where c.id=?1")
	List<Payment> paymentByCompany(int company_id);

}
