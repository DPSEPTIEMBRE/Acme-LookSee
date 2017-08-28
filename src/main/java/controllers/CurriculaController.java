package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curricula;
import services.CurriculaService;

@Controller
@RequestMapping("/curricula")
public class CurriculaController {

	@Autowired
	CurriculaService curriculaService;

	@RequestMapping(value = "/candidate/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(curriculaService.create(), null);

		return result;
	}

	@RequestMapping(value="/candidate/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Curricula curricula, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(curricula, null);
		} else {
			try {
				curriculaService.save(curricula);
				result = new ModelAndView("redirect:/curricula/actor/list.do");
			} catch (Throwable th) {
				result = createNewModelAndView(curricula, "curricula.commit.error");
			}
		}
			
		return result;
	}

	protected ModelAndView createNewModelAndView(Curricula curricula, String message) {
		ModelAndView result;
		result = new ModelAndView("curricula/create");
		result.addObject("curricula", curricula);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/verifier/list-groupby", method = RequestMethod.GET)
	public ModelAndView list_group_by(@RequestParam(defaultValue = "0") Integer q) {
		ModelAndView result;

		result = new ModelAndView("curricula/list_group_by");
		result.addObject("curricula", curriculaService.list_group_by(q));
		result.addObject("q", q);

		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) Integer q) {
		ModelAndView result;

		result = new ModelAndView("curricula/list");
		result.addObject("curricula", q == null ? curriculaService.curriculasOfSelf() : curriculaService.curriculasOf(q));

		return result;
	}
	
	@RequestMapping(value = "/actor/list-all", method = RequestMethod.GET)
	public ModelAndView list_all(@RequestParam(required = false) String q) {
		ModelAndView result;

		result = new ModelAndView("curricula/listAll");
		result.addObject("curricula", curriculaService.findAll(q));

		return result;
	}
	
	@RequestMapping(value = "/actor/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam Curricula q) {
		ModelAndView result;

		result = new ModelAndView("curricula/view");
		result.addObject("curricula", q);

		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Curricula q) {
		ModelAndView result;
		result = new ModelAndView("curricula/edit");
		result.addObject("curricula", q);
		return result;
	}

	@RequestMapping(value = "/candidate/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Curricula q) {
		ModelAndView result;

		try {
			curriculaService.delete(q);
			result = new ModelAndView("redirect:/curricula/actor/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(q, "curricula.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/candidate/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Curricula curricula, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(curricula, null);
	} else {
		try {
			curriculaService.save(curricula);
				result = new ModelAndView("redirect:/curricula/actor/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(curricula, "curricula.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Curricula curricula, String message) {
		ModelAndView result = new ModelAndView("curricula/edit");

		result.addObject("curricula", curricula);
		result.addObject("message", message);

		return result;
	}

}
