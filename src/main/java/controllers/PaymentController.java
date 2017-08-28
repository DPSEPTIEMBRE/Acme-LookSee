package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Company;
import domain.Payment;
import security.LoginService;
import services.CompanyService;
import services.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "company/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		Company company = (Company) loginService.findActorByUsername(LoginService.getPrincipal().getUsername());

		List<Payment> paymentsPaid = paymentService.paymentByCompanyAndPaid(company.getId(), true);
		List<Payment> paymentUnpaid = paymentService.paymentByCompanyAndPaid(company.getId(), false);
		
		result = new ModelAndView("payment/list");
		result.addObject("paymentsPaid", paymentsPaid);
		result.addObject("paymentsUnpaid", paymentUnpaid);

		return result;
	}

}
