package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Company;
import domain.CreditCard;
import security.LoginService;
import security.UserAccount;
import services.CompanyService;
import services.CreditCardService;

@Controller
@RequestMapping("/creditcard")
public class CreditCardController {

	@Autowired
	CreditCardService creditcardService;
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/company/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(creditcardService.create(), null);

		return result;
	}

	@RequestMapping(value="/company/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid CreditCard creditcard, BindingResult binding) {
		ModelAndView result;
			if (binding.hasErrors()) {
				result = createNewModelAndView(creditcard, null);
			} else {
				try {
					creditcardService.save(creditcard);
					result = new ModelAndView("redirect:/creditcard/company/view.do");
				} catch (Throwable th) {
					result = createNewModelAndView(creditcard, "creditcard.commit.error");
				}
			}
		return result;
	}

	protected ModelAndView createNewModelAndView(CreditCard creditcard, String message) {
		ModelAndView result;
		result = new ModelAndView("creditcard/create");
		result.addObject("creditcard", creditcard);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/company/view", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		UserAccount userAccount = LoginService.getPrincipal();
		Company company = companyService.selectByUsername(userAccount.getUsername());

		result = new ModelAndView("creditcard/view");
		result.addObject("creditcard", company.getCreditCard());

		return result;
	}

	@RequestMapping(value = "/company/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		UserAccount userAccount = LoginService.getPrincipal();
		Company company = companyService.selectByUsername(userAccount.getUsername());
		
		ModelAndView result;
		result = new ModelAndView("creditcard/edit");
		result.addObject("creditcard", company.getCreditCard());
		return result;
	}

	@RequestMapping(value="/company/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid CreditCard creditcard, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(creditcard, null);
	} else {
		try {
			creditcardService.save(creditcard);
				result = new ModelAndView("redirect:/creditcard/company/view.do");
			} catch (Throwable th) {
				result = createEditModelAndView(creditcard, "creditcard.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditcard, String message) {
		ModelAndView result = new ModelAndView("creditcard/edit");

		result.addObject("creditcard", creditcard);
		result.addObject("message", message);

		return result;
	}

}
