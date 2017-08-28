package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curricula;
import domain.EndorserRecord;
import services.EndorserRecordService;

@Controller
@RequestMapping("/endorserrecord")
public class EndorserRecordController {

	@Autowired
	EndorserRecordService endorserrecordService;

	@RequestMapping(value = "/candidate/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		result = createNewModelAndView(endorserrecordService.create(), null);

		return result;
	}

	@RequestMapping(value="/candidate/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid EndorserRecord endorserrecord,
			BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(endorserrecord, null);
		} else {
			try {
				endorserrecordService.save(endorserrecord, curricula_id);
				result = new ModelAndView("redirect:/curricula/candidate/edit.do?q=" + curricula_id);
			} catch (Throwable th) {
				result = createNewModelAndView(endorserrecord, "endorserrecord.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(EndorserRecord endorserrecord, String message) {
		ModelAndView result;
		result = new ModelAndView("endorserrecord/create");
		result.addObject("endorserrecord", endorserrecord);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/candidate/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		
		result = new ModelAndView("endorserrecord/list");
		result.addObject("endorserrecord", q.getEndorserRecords());

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam EndorserRecord q) {
		ModelAndView result;
		result = new ModelAndView("endorserrecord/edit");
		result.addObject("endorserrecord", q);
		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(HttpServletRequest request, @Valid EndorserRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");

		try {
			endorserrecordService.delete(q);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/endorserrecord/candidate/list.do?q="+curricula_id);
		} catch (Throwable th) {
			result = createEditModelAndView(q, "endorserrecord.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(HttpServletRequest request, @Valid EndorserRecord endorserrecord, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(endorserrecord, null);
		} else {
			try {
				endorserrecordService.save(endorserrecord);
				result = new ModelAndView("redirect:/endorserrecord/candidate/list.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditModelAndView(endorserrecord, "endorserrecord.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/candidate/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam EndorserRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		try {
			endorserrecordService.delete(q, curricula_id);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/endorserrecord/candidate/list.do?q=" + curricula_id);
		} catch(Throwable th) {
			result = createEditModelAndView(q, "endorserrecord.commit.error");
		}
		
		return result;
	}

	protected ModelAndView createEditModelAndView(EndorserRecord endorserrecord, String message) {
		ModelAndView result = new ModelAndView("endorserrecord/edit");

		result.addObject("endorserrecord", endorserrecord);
		result.addObject("message", message);

		return result;
	}

}
