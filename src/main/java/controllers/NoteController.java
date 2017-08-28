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
import domain.Note;
import security.LoginService;
import services.ActivityReportService;
import services.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Autowired
	NoteService noteService;
	@Autowired
	ActivityReportService activityReportService;

	@RequestMapping(value = "/verifier/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		result = createNewModelAndView(noteService.create(q), null);

		return result;
	}

	@RequestMapping(value="/verifier/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid Note note, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(note, null);
		} else {
			try {
				noteService.save(note, curricula_id);
				result = new ModelAndView("redirect:/curricula/actor/view.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
				activityReportService.createDefault("Note publish", "An note was publish!");
			} catch (Throwable th) {
				result = createNewModelAndView(note, "note.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Note note, String message) {
		ModelAndView result;
		result = new ModelAndView("note/create");
		result.addObject("note", note);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/verifier/list-groupby", method = RequestMethod.GET)
	public ModelAndView list_group_by(@RequestParam(defaultValue = "0") Integer q) {
		ModelAndView result;

		result = new ModelAndView("note/list_group_by");
		result.addObject("note", noteService.list_group_by(q));
		result.addObject("q", q);

		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, @RequestParam Curricula q) {
		ModelAndView result;

		request.getSession().setAttribute("curricula_id", q.getId());
		
		result = new ModelAndView("note/list");
		result.addObject("note", q.getNotes());

		return result;
	}
	
	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, @RequestParam Note q) {
		ModelAndView result;
		
		if(LoginService.hasRole("VERIFIER")) {
			result = new ModelAndView("note/editVerifier");
		} else {
			result = new ModelAndView("note/edit");
		}
		
		result.addObject("note", q);
		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(HttpServletRequest request, @Valid Note note, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			if(LoginService.hasRole("CANDIDATE")) {
				result = createEditModelAndView(note, null);
			} else {
				result = new ModelAndView("note/editVerifier");
			}
		} else {
			try {
				noteService.saveCandidate(note);
				result = new ModelAndView("redirect:/curricula/actor/view.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditModelAndView(note, "note.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/verifier/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEditVerifier(HttpServletRequest request, @Valid Note note, BindingResult binding) {
		ModelAndView result;
		
		int curricula_id = (int) request.getSession().getAttribute("curricula_id");
		
		if (binding.hasErrors()) {
			result = createEditVerifierModelAndView(note, null);
		} else {
			try {
				noteService.save(note);
				result = new ModelAndView("redirect:/note/actor/list.do?q=" + curricula_id);
				request.getSession().removeAttribute("curricula_id");
			} catch (Throwable th) {
				result = createEditVerifierModelAndView(note, "note.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditVerifierModelAndView(Note note, String message) {
		ModelAndView result = new ModelAndView("note/edit");

		result.addObject("note", note);
		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Note note, String message) {
		ModelAndView result = new ModelAndView("note/edit");

		result.addObject("note", note);
		result.addObject("message", message);

		return result;
	}

}
