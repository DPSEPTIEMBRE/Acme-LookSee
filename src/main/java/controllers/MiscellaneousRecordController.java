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
import domain.MiscellaneousRecord;
import services.MiscellaneousRecordService;

@Controller
@RequestMapping("/miscellaneousrecord")
public class MiscellaneousRecordController {

	@Autowired
	MiscellaneousRecordService miscellaneousrecordService;

	@RequestMapping(value = "/candidate/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		result = createNewModelAndView(miscellaneousrecordService.create(), null);

		return result;
	}

	@RequestMapping(value = "/candidate/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid MiscellaneousRecord miscellaneousrecord,
			BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(miscellaneousrecord, null);
		} else {
			try {
				miscellaneousrecordService.save(miscellaneousrecord, curricula_id);
				result = new ModelAndView("redirect:/curricula/candidate/edit.do?q=" + curricula_id);
			} catch (Throwable th) {
				result = createNewModelAndView(miscellaneousrecord, "miscellaneousrecord.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(MiscellaneousRecord miscellaneousrecord, String message) {
		ModelAndView result;
		result = new ModelAndView("miscellaneousrecord/create");
		result.addObject("miscellaneousrecord", miscellaneousrecord);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/candidate/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		
		result = new ModelAndView("miscellaneousrecord/list");
		result.addObject("miscellaneousrecord", q.getMiscellaneousRecords());

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam MiscellaneousRecord q) {
		ModelAndView result;
		result = new ModelAndView("miscellaneousrecord/edit");
		result.addObject("miscellaneousrecord", q);
		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(HttpServletRequest request, @Valid MiscellaneousRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");

		try {
			miscellaneousrecordService.delete(q);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/miscellaneousrecord/candidate/list.do?q="+curricula_id);
		} catch (Throwable th) {
			result = createEditModelAndView(q, "miscellaneousrecord.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(HttpServletRequest request, @Valid MiscellaneousRecord miscellaneousrecord, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(miscellaneousrecord, null);
		} else {
			try {
				miscellaneousrecordService.saveEditing(miscellaneousrecord);
				result = new ModelAndView("redirect:/miscellaneousrecord/candidate/list.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditModelAndView(miscellaneousrecord, "miscellaneousrecord.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/candidate/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam MiscellaneousRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		try {
			miscellaneousrecordService.delete(q, curricula_id);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/miscellaneousrecord/candidate/list.do?q=" + curricula_id);
		} catch(Throwable th) {
			result = createEditModelAndView(q, "miscellaneousrecord.commit.error");
		}
		
		return result;
	}

	protected ModelAndView createEditModelAndView(MiscellaneousRecord miscellaneousrecord, String message) {
		ModelAndView result = new ModelAndView("miscellaneousrecord/edit");

		result.addObject("miscellaneousrecord", miscellaneousrecord);
		result.addObject("message", message);

		return result;
	}

}
