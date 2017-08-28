package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Candidate;
import security.LoginService;
import security.UserAccount;
import services.CacheService;
import services.CandidateService;
import services.CurriculaService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	@Autowired
	CurriculaService curriculaService;
	@Autowired
	CacheService cacheService;
	
	public CandidateController() {
		super();
	}
	
	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(candidateService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Candidate candidate, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(candidate, null);
		} else {
			try {
				candidateService.save(candidate);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createNewModelAndView(candidate, "candidate.commit.error");
			}
		}
		
		return result;
	}

	protected ModelAndView createNewModelAndView(Candidate candidate, String message) {
		ModelAndView result;
		result = new ModelAndView("candidate/create");
		result.addObject("candidate", candidate);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("candidate/edit");
		
		UserAccount userAccount = LoginService.getPrincipal();
		Candidate candidate = candidateService.selectByUsername(userAccount.getUsername());
		
		result.addObject(candidate);
		return result;
	}
	
	@RequestMapping(value = "/company/list-advanced", method = RequestMethod.GET)
	public ModelAndView listAdvanced(HttpServletRequest request, @RequestParam(defaultValue = "") String q) {
		ModelAndView result;
		
		result = new ModelAndView("company/advanced_list");
		
		List<Candidate> candidates = curriculaService.findAllCandidates(q, request.getSession().getId());
		result.addObject("candidate", candidates);
		cacheService.createCandidate(candidates, request.getSession().getId());
		
		return result;
	}

	@RequestMapping(value="/actor/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Candidate candidate, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(candidate, null);
		} else {
			try {
				candidateService.saveEditing(candidate);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				th.printStackTrace();
				result = createEditModelAndView(candidate, "candidate.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Candidate candidate, String message) {
		ModelAndView result = new ModelAndView("candidate/edit");

		result.addObject("candidate", candidate);
		result.addObject("message", message);

		return result;
	}

}
