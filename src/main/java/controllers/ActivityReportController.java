package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.ActivityReport;
import domain.Actor;
import domain.Company;
import security.LoginService;
import services.ActivityReportService;
import services.CompanyService;

@Controller
@RequestMapping("/activityreport")
public class ActivityReportController extends AbstractController{

	@Autowired
	ActivityReportService activityreportService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Company q) {
		ModelAndView result;

		result = new ModelAndView("activityreport/list");
		result.addObject("activityreport", q.getActivities());

		return result;
	}

	@RequestMapping(value = "/actor/mylist", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getUsername());

		List<ActivityReport> acts = actor.getActivities();
		result = new ModelAndView("activityreport/mylist");
		result.addObject("activityreport", acts);

		return result;
	}
	
	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam ActivityReport q) {
		ModelAndView result;
		result = new ModelAndView("activityreport/edit");
		result.addObject("activityreport", q);
		return result;
	}

	@RequestMapping(value="/actor/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid ActivityReport activityreport, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(activityreport, null);
	} else {
		try {
			activityreportService.save(activityreport);
			result = new ModelAndView("redirect:/activityreport/actor/mylist.do");
			} catch (Throwable th) {
				result = createEditModelAndView(activityreport, "activityreport.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/actor/delete", method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam ActivityReport q) {
		ModelAndView res;
		
		activityreportService.delete(q);
		res = new ModelAndView("redirect:/activityreport/actor/mylist.do");
		
		return res;
	}

	protected ModelAndView createEditModelAndView(ActivityReport activityreport, String message) {
		ModelAndView result = new ModelAndView("activityreport/edit");

		result.addObject("activityreport", activityreport);
		result.addObject("message", message);

		return result;
	}

}
