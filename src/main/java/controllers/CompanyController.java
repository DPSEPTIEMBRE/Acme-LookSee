package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Company;
import services.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	//Services

	@Autowired
	private CompanyService companyService;


	//Constructor

	public CompanyController() {
		super();
	}


	//Creation
	
	

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(companyService.create(), null);

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save(@Valid Company user, BindingResult binding) {
		ModelAndView result;

		for(FieldError e : binding.getFieldErrors()) {
			System.err.println(e.getField());
			System.err.println(e.getDefaultMessage());
		}

		if (binding.hasErrors()) {
			result = createNewModelAndView(user, null);
		} else {
			try {
				companyService.save_create(user);

				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable e) {
				result = createNewModelAndView(user, "company.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createNewModelAndView(Company company, String message) {
		ModelAndView result;

		result = new ModelAndView("company/create");

		result.addObject("company", company);
		result.addObject("message", message);

		return result;
	}

}


/*package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Company;
import forms.CompanyRegisterForm;
import security.Credentials;
import services.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	//Services

	@Autowired
	private CompanyService companyService;


	//Constructor

	public CompanyController() {
		super();
	}


	//Creation

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CompanyRegisterForm companyRegisterForm;

		companyRegisterForm = new CompanyRegisterForm();
		Assert.notNull(companyRegisterForm);

		result = this.createEditFormModelAndView(companyRegisterForm);

		return result;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST, params = "save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			final CompanyRegisterForm companyRegisterForm, final BindingResult binding,
			@Valid @ModelAttribute final Credentials credentials,
			@RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Company user;


		if (!companyRegisterForm.isTermsAndConditions()) {
			result = this.createEditFormModelAndView(companyRegisterForm,
					"company.commit.error.termsAndConditions", credentials, showError);
		} else {

			companyRegisterForm.getPassword();
			companyRegisterForm.getUsername();
			user = this.companyService.reconstruct(companyRegisterForm, binding);

			if (binding.hasErrors())
				result = this.createEditFormModelAndViewError(companyRegisterForm);
			else
				try {
					this.companyService.save(user);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable oops) {
					result = this.createEditFormModelAndView(companyRegisterForm, "company.commit.error",
							credentials, showError);
				}
		}
		return result;
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid Company user, BindingResult binding) {
		ModelAndView result;

		for(FieldError e : binding.getFieldErrors()) {
			System.err.println(e.getField());
			System.err.println(e.getDefaultMessage());
		}

		if (binding.hasErrors()) {
			result = createNewModelAndView(user, null);
		} else {
			try {
				companyService.save_create(user);

				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable e) {
				result = createNewModelAndView(user, "company.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createNewModelAndView(Company company, String message) {
		ModelAndView result;

		result = new ModelAndView("company/signUp");

		result.addObject("company", company);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView createEditFormModelAndView(final CompanyRegisterForm companyRegisterForm) {
		ModelAndView result;

		result = this.createEditFormModelAndView(companyRegisterForm, null, null, false);

		return result;
	}

	private ModelAndView createEditFormModelAndView(final CompanyRegisterForm companyRegisterForm,
			final String message, Credentials credentials, boolean showError) {
		ModelAndView result;

		result = new ModelAndView("welcome/index");

		result.addObject("credentials", credentials);

		result.addObject("showError", showError);
		result.addObject("companyRegisterForm", companyRegisterForm);
		result.addObject("message", message);
		if (message != null) {
			result.addObject("showError", true);
		}

		return result;
	}

	private ModelAndView createEditFormModelAndViewError(final CompanyRegisterForm tourFriendRegisterForm) {
		ModelAndView result;

		result = this.createEditFormModelAndViewError(tourFriendRegisterForm, null);

		return result;
	}

	private ModelAndView createEditFormModelAndViewError(final CompanyRegisterForm companyRegisterForm,final String message) {
		ModelAndView result;

		result = new ModelAndView("welcome/index");

		result.addObject("companyRegisterForm", companyRegisterForm);
		result.addObject("message", message);
		if (message != null) {
			result.addObject("showError", true);
		}

		return result;
	}

}
 */
