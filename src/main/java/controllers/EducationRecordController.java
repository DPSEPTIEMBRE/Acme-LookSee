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
import domain.EducationRecord;
import services.EducationRecordService;

@Controller
@RequestMapping("/educationrecord")
public class EducationRecordController {

	@Autowired
	EducationRecordService educationrecordService;

	@RequestMapping(value = "/candidate/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		result = createNewModelAndView(educationrecordService.create(), null);

		return result;
	}

	@RequestMapping(value="/candidate/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid EducationRecord educationrecord, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
	
		if (binding.hasErrors()) {
			result = createNewModelAndView(educationrecord, null);
		} else {
			try {
				educationrecordService.save(educationrecord, curricula_id);
				result = new ModelAndView("redirect:/curricula/candidate/edit.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createNewModelAndView(educationrecord, "educationrecord.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(EducationRecord educationrecord, String message) {
		ModelAndView result;
		result = new ModelAndView("educationrecord/create");
		result.addObject("educationrecord", educationrecord);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/candidate/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;
		
		request.getSession().setAttribute("curricula_id", q.getId());
		
		result = new ModelAndView("educationrecord/list");
		result.addObject("educationrecord", q.getEducationRecords());

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam EducationRecord q) {
		ModelAndView result;
		result = new ModelAndView("educationrecord/edit");
		result.addObject("educationrecord", q);
		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(HttpServletRequest request, @Valid EducationRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");

		try {
			educationrecordService.delete(q, curricula_id);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/educationrecord/candidate/list.do?q="+curricula_id);
		} catch (Throwable th) {
			result = createEditModelAndView(q, "educationrecord.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/candidate/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam EducationRecord q) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		try {
			educationrecordService.delete(q, curricula_id);
			request.getSession().setAttribute("curricula_id", curricula_id);
			result = new ModelAndView("redirect:/educationrecord/candidate/list.do?q=" + curricula_id);
		} catch(Throwable th) {
			result = createEditModelAndView(q, "educationrecord.commit.error");
		}
		
		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(HttpServletRequest request, @Valid EducationRecord educationrecord, BindingResult binding) {
	ModelAndView result;
	
	int curricula_id = (int) request.getSession().getAttribute("curricula_id");
	
	if (binding.hasErrors()) {
		result = createEditModelAndView(educationrecord, null);
	} else {
			try {
				educationrecordService.saveEditing(educationrecord);
				result = new ModelAndView("redirect:/curricula/candidate/edit.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditModelAndView(educationrecord, "educationrecord.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(EducationRecord educationrecord, String message) {
		ModelAndView result = new ModelAndView("educationrecord/edit");

		result.addObject("educationrecord", educationrecord);
		result.addObject("message", message);

		return result;
	}

}
