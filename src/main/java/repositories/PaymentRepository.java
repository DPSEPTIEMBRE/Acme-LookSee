package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	//Media de pagos no finalizados por compañia
	@Query("select count(p) * 1. / (select sum(o.payments.size) from Company o) from Company c join c.payments p where p.paid = false")
	Double AvgPaymentsNoFinishByCompany();

	//El mínimo, máximo y media de pagos por compañía.
	@Query("select min(c.payments.size), max(c.payments.size), avg(c.payments.size) from Company c")
	Object[] minMaxAvgPaymentsByCompany();

	//El ratio de compañías con pagos no finalizados.
	@Query("select count(c) * 1. / (select count(o) from Company o) from Company c join c.payments p where p.paid = false")
	Double RatioCompanyNoFinish();

	//Pagos por compañia.
	@Query("select c.payments from Company c where c.id = ?1")
	List<Payment> paymentByCompany(int company_id);

	//Lista de pagos por compañia pagados o no pagados.
	@Query("select p from Company c join c.payments p where c.id = ?1 and p.paid = ?2")
	List<Payment> paymentByCompanyAndPaid(int companyId, Boolean paid);
	
	@Query("select c from Payment c where c.paid = false")
	List<Payment> getUnpaidments();
}
