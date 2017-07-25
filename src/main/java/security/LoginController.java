/*
 * LoginController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import forms.CompanyRegisterForm;

@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	LoginService	service;


	// Constructors -----------------------------------------------------------

	public LoginController() {
		super();
	}
	
	// Login ------------------------------------------------------------------

	@RequestMapping("/login")
	public ModelAndView login(@Valid @ModelAttribute final Credentials credentials, final BindingResult bindingResult, @RequestParam(required = false) final boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);

		ModelAndView result;

		CompanyRegisterForm companyRegisterForm = new CompanyRegisterForm();
		Assert.notNull(companyRegisterForm);
		
		result = createEditFormModelAndView1(companyRegisterForm, null, credentials, showError);

		result = new ModelAndView("security/login");
		result.addObject("credentials", credentials);
		result.addObject("showError", showError);

		return result;
	}
	
	//LoginFailure -----------------------------------------------------------

	@RequestMapping("/loginFailure")
	public ModelAndView failure(@Valid @ModelAttribute final Credentials credentials, final BindingResult bindingResult,
			@RequestParam(required = false) final boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);
		ModelAndView result;

		CompanyRegisterForm companyRegisterForm = new CompanyRegisterForm();
		Assert.notNull(companyRegisterForm);

		result = createEditFormModelAndView(companyRegisterForm, "security.login.failed", credentials, showError);

		return result;
	}

	private ModelAndView createEditFormModelAndView1(final CompanyRegisterForm companyRegisterForm,
			final String message, Credentials credentials, boolean showError) {
		ModelAndView result;

		result = new ModelAndView("welcome/index");
		result.addObject("credentials", credentials);
		result.addObject("showError", showError);
		result.addObject("companyRegisterForm", companyRegisterForm);
		result.addObject("message", message);
		result.addObject("showErrorLogin", true);

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
			result.addObject("showErrorLogin", true);
			result.addObject("showMessage", true);
		} else {
			result.addObject("showErrorLogin", false);
		}

		return result;
	}
}
