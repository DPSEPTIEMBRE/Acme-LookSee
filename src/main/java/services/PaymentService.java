package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Payment;
import repositories.PaymentRepository;

@Service
@Transactional
public class PaymentService {

	//Repositories

	@Autowired
	private  PaymentRepository  paymentRepository;

	//Services

	//CRUD Methods

	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	public Payment findOne(Integer arg0) {
		return paymentRepository.findOne(arg0);
	}

	public <S extends Payment> List<S> save(Iterable<S> entities) {
		return paymentRepository.save(entities);
	}

	public <S extends Payment> S save(S arg0) {
		return paymentRepository.save(arg0);
	}
	
	//Others Methods
	
	public Double AvgPaymentsNoFinishByCompany() {
		return paymentRepository.AvgPaymentsNoFinishByCompany();
	}

	public Double RatioCompanyNoFinish() {
		return paymentRepository.RatioCompanyNoFinish();
	}
	
	public Object[] minMaxAvgPaymentsByCompany() {
		return paymentRepository.minMaxAvgPaymentsByCompany();
	}

	public List<Payment> paymentByCompany(int company_id) {
		return paymentRepository.paymentByCompany(company_id);
	}

}
