package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;
import services.ActivityReportService;
import services.ApplicationService;

@Controller
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	@Autowired
	ActivityReportService activityReportService;

	protected ModelAndView createNewModelAndView(Application application, String message) {
		ModelAndView result;
		result = new ModelAndView("application/create");
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/candidate/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("application/list");
		result.addObject("application", applicationService.selectSelf());

		return result;
	}
	
	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	public ModelAndView listCompany() {
		ModelAndView result;

		result = new ModelAndView("application/listCompany");
		result.addObject("application", applicationService.selectSelfCompany());

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Application q) {
		ModelAndView result;
		result = new ModelAndView("application/edit");
		result.addObject("application", q);
		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Application application, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(application, null);
		} else {
			try {
				applicationService.save(application);
				activityReportService.createDefault("Applications status", "Applications status was changed!");
				result = new ModelAndView("redirect:/application/company/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(application, "application.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Application application, String message) {
		ModelAndView result = new ModelAndView("application/edit");

		result.addObject("application", application);
		result.addObject("message", message);

		return result;
	}

}
