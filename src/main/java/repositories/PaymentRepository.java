package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	@Query("select avg(c.payments.size) from Company c join c.payments pa where pa.createMoment+7 < CURRENT_TIMESTAMP")
	public Double AvgPaymentsNoFinishByCompany();

}
