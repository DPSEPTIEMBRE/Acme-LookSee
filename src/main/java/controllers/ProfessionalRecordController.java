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
import domain.ProfessionalRecord;
import services.ProfessionalRecordService;

@Controller
@RequestMapping("/professionalrecord")
public class ProfessionalRecordController {

	@Autowired
	ProfessionalRecordService professionalrecordService;

	@RequestMapping(value = "/candidate/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		result = createNewModelAndView(professionalrecordService.create(), null);

		return result;
	}

	@RequestMapping(value="/candidate/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid ProfessionalRecord professionalrecord, BindingResult binding) {
		ModelAndView result;
	
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
	
		if (binding.hasErrors()) {
			result = createNewModelAndView(professionalrecord, null);
		} else {
			try {
				professionalrecordService.save(professionalrecord, curricula_id);
				result = new ModelAndView("redirect:/curricula/candidate/edit.do?q=" + curricula_id);
			} catch (Throwable th) {
				result = createNewModelAndView(professionalrecord, "professionalrecord.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(ProfessionalRecord professionalrecord, String message) {
		ModelAndView result;
		result = new ModelAndView("professionalrecord/create");
		result.addObject("professionalrecord", professionalrecord);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/candidate/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;
		
		request.getSession().setAttribute("curricula_id", q.getId());

		result = new ModelAndView("professionalrecord/list");
		result.addObject("professionalrecord", q.getProfessionalRecords());

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam ProfessionalRecord q) {
		ModelAndView result;
		result = new ModelAndView("professionalrecord/edit");
		result.addObject("professionalrecord", q);
		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(HttpServletRequest request, @Valid ProfessionalRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");

		try {
			professionalrecordService.delete(q);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/professionalrecord/candidate/list.do?q="+curricula_id);
		} catch (Throwable th) {
			result = createEditModelAndView(q, "professionalrecord.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(HttpServletRequest request, @Valid ProfessionalRecord professionalrecord,
			BindingResult binding) {
		ModelAndView result;

		int curricula_id = (int) request.getSession().getAttribute("curricula_id");

		if (binding.hasErrors()) {
			result = createEditModelAndView(professionalrecord, null);
		} else {
			try {
				professionalrecordService.saveEditing(professionalrecord);
				result = new ModelAndView("redirect:/professionalrecord/candidate/list.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditModelAndView(professionalrecord, "professionalrecord.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/candidate/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam ProfessionalRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		try {
			professionalrecordService.delete(q, curricula_id);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/professionalrecord/candidate/list.do?q=" + curricula_id);
		} catch(Throwable th) {
			result = createEditModelAndView(q, "professionalrecord.commit.error");
		}
		
		return result;
	}

	protected ModelAndView createEditModelAndView(ProfessionalRecord professionalrecord, String message) {
		ModelAndView result = new ModelAndView("professionalrecord/edit");

		result.addObject("professionalrecord", professionalrecord);
		result.addObject("message", message);

		return result;
	}

}
