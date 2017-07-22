package services;

import java.util.Date;
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
	
	@Autowired
	private CreditCardService creditCardService;
	
	//Constructor
	
	public PaymentService() {
		super();
	}

	//CRUD Methods
	
	public Payment create() {
		Payment payment= new Payment();
		
		payment.setCreateMoment(new Date());
		payment.setCreditCard(creditCardService.create());
		payment.setDescription(new String());
		payment.setPaid(false);
		payment.setPrice(new Double(0.0));
		payment.setTax(new Double(0.0));
		
		return payment;
	}

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
