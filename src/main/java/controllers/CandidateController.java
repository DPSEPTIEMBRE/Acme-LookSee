package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Candidate;
import services.CandidateService;

@Controller
@RequestMapping("/candidate")
public class CandidateController extends AbstractController {

	//Services
	@Autowired
	private CandidateService candidateService;


	//Constructor
	public CandidateController() {
		super();
	}


	//Creation
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(candidateService.create(), null);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@Valid Candidate user, BindingResult binding) {

		ModelAndView result;

		for(FieldError e : binding.getFieldErrors()) {
			System.err.println(e.getField());
			System.err.println(e.getDefaultMessage());
		}

		if (binding.hasErrors()) {
			result = createNewModelAndView(user, null);
		} else {
			try {

				candidateService.save(user);

				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable e) {
				result = createNewModelAndView(user, "candidate.commit.error");
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


	//Ancillary methods -----------------------------

	protected ModelAndView createEditModelAndView(Candidate candidate){
		ModelAndView result;
		result= createEditModelAndView(candidate, null, null);
		return result;

	}

	private ModelAndView createEditModelAndView(Candidate candidate, String message, String msgType) {
		ModelAndView result;

		//if(candidate.getId()==0)
		result= new ModelAndView("candidate/create");
		//else
		//result= new ModelAndView("candidate/edit");
		result.addObject("candidate", candidate);
		result.addObject("message", message);
		result.addObject("msgType", msgType);

		return result;
	}
}
