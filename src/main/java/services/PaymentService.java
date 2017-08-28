package services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.AdministratorKey;
import domain.Company;
import domain.Offer;
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
	@Autowired
	private AdministratorKeyService administratorKeyService;
	@Autowired
	private CompanyService companyService;
	
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
		Assert.notNull(arg0);
		
		return paymentRepository.findOne(arg0);
	}

	public List<Payment> save(List<Payment> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return paymentRepository.save(entities);
	}

	public Payment save(Payment arg0) {
		Assert.notNull(arg0);
		
		return paymentRepository.save(arg0);
	}
	
	//Others Methods
	
	public void flush() {
		paymentRepository.flush();
	}

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
		Assert.notNull(company_id);
		
		return paymentRepository.paymentByCompany(company_id);
	}

	public List<Payment> paymentByCompanyAndPaid(int companyId, Boolean paid){
		return paymentRepository.paymentByCompanyAndPaid(companyId, paid);
	}
	
	public List<Payment> getUnpaidments() {
		return paymentRepository.getUnpaidments();
	}
	
	public Payment create_auto(Offer offer) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		AdministratorKey price_key = administratorKeyService.select_price();
		AdministratorKey iva_key = administratorKeyService.select_iva();
		
		double price = Double.valueOf(price_key.getKeyValue());
		double iva = Double.valueOf(iva_key.getKeyValue());
		
		Payment payment = new Payment();
		payment.setCreateMoment(new Date());
		payment.setCreditCard(offer.getCompany().getCreditCard());
		payment.setDescription(String.format("Auto generated at %s", format.format(new Date())));
		payment.setPaid(false);
		payment.setPrice(price * (1 + (iva / 100d)));
		payment.setTax(iva);
		
		Payment saved = paymentRepository.save(payment);
		
		Company company = offer.getCompany();
		company.getPayments().add(saved);
		
		companyService.save(company);
		
		return saved;
	}
}
