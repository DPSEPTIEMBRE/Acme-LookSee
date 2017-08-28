package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Company;
import security.LoginService;
import security.UserAccount;
import services.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(companyService.create(), null);

		return result;
	}
	
	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Company company, BindingResult binding) {
		ModelAndView result;
			if (binding.hasErrors()) {
				result = createNewModelAndView(company, null);
			} else {
				try {
					companyService.save(company);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (Throwable th) {
					th.printStackTrace();
					result = createNewModelAndView(company, "company.commit.error");
				}
			}
		return result;
	}
	
	@RequestMapping(value="/actor/list", method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		result = new ModelAndView("company/list");
		result.addObject("company", companyService.findAll());
		
		return result;
	}
	
	@RequestMapping(value="/actor/view", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam Company q) {
		ModelAndView result;
		
		result = new ModelAndView("company/view");
		result.addObject("company", q);
		
		return result;
	}

	protected ModelAndView createNewModelAndView(Company company, String message) {
		ModelAndView result;
		result = new ModelAndView("company/create");
		result.addObject("company", company);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		
		UserAccount userAccount = LoginService.getPrincipal();
		Company company = companyService.selectByUsername(userAccount.getUsername());
		
		result = new ModelAndView("company/edit");
		result.addObject("company", company);
		return result;
	}

	@RequestMapping(value="/actor/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Company company, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(company, null);
		} else {
			try {
				companyService.saveEditing(company);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (Throwable th) {
					result = createEditModelAndView(company, "company.commit.error");
				}
			}
		return result;
	}
	
	@RequestMapping(value = "/administrator/block", method = RequestMethod.GET)
	public ModelAndView blockCompany(@RequestParam Company q) {
		ModelAndView result;
		
		companyService.lockCompany(q);
		result = new ModelAndView("redirect:/company/actor/view.do?q="+q.getId());
		
		return result;
	}

	protected ModelAndView createEditModelAndView(Company company, String message) {
		ModelAndView result = new ModelAndView("company/edit");

		result.addObject("company", company);
		result.addObject("message", message);

		return result;
	}

}
