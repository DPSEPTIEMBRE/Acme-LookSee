/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import domain.AdministratorKey;
import security.LoginService;
import security.UserAccount;
import services.ActivityReportService;
import services.AdministratorKeyService;
import services.AdministratorService;
import services.ApplicationService;
import services.CandidateService;
import services.CompanyService;
import services.CurriculaService;
import services.NoteService;
import services.PaymentService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ActivityReportService activityReportService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private NoteService noteService;

	@Autowired
	private CurriculaService curriculaService;
	
	@Autowired
	private AdministratorKeyService administratorKeyService;

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping("/dashboard")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/dashboard");

		// Level c

		// El listado de candidatos, ordenados en orden descendente por número
		// de currículos.
		result.addObject("orderByNumCurriculas", candidateService.orderByNumCurriculas());

		// El listado de compañías, ordenados en orden descendente por número de
		// ofertas.
		result.addObject("orderByNumOffers", companyService.orderByNumOffers());

		// La media de currículos por candidato.
		result.addObject("avgCurriculasByCandidate", curriculaService.avgCurriculasByCandidate());

		// La media de ofertas por compañía.
		result.addObject("avgNumberOfferByCompany", companyService.avgNumberOfferByCompany());

		// Los candidatos que han registrado más currículos.
		result.addObject("CandidateWithMoreCurriculas", candidateService.CandidateWithMoreCurriculas());

		// Las compañías que han registrado más ofertas.
		result.addObject("companyMaxOffers", companyService.companyMaxOffers());

		// La media, mínimo y máximo de solicitudes por candidato.
		result.addObject("AvgMaxMinApplicationsByCandidate", applicationService.AvgMaxMinApplicationsByCandidate());

		// La media, mínimo y máximo de solicitudes por oferta.
		result.addObject("AvgMaxMinApplicationsByOffers", applicationService.AvgMaxMinApplicationsByOffers());

		// La media, mínimo y máximo de solicitudes pendientes por compañía.
		result.addObject("AvgMaxMinApplicationsPendingByCompany",
				applicationService.AvgMaxMinApplicationsPendingByCompany());

		// La media, mínimo y máximo de solicitudes aceptadas por compañía.
		result.addObject("AvgMaxMinApplicationsAcceptedByCompany",
				applicationService.AvgMaxMinApplicationsAcceptedByCompany());

		// La media, mínimo y máximo de solicitudes rechazadas por compañía.
		result.addObject("AvgMaxMinApplicationsRejectedByCompany",
				applicationService.AvgMaxMinApplicationsRejectedByCompany());

		// Level b

		// El mínimo, máximo y media de registros de actividad por actor.
		result.addObject("MinMaxAvgActiviesByActor", activityReportService.MinMaxAvgActiviesByActor());

		// Los actores que tienen ±10% de la media de registros de actividad por
		// actor.
		result.addObject("actorsBetweenTenPercentActivities",
				activityReportService.actorsBetweenTenPercentActivities());

		// El mínimo, máximo y media de pagos por compañía.
		result.addObject("minMaxAvgPaymentsByCompany", paymentService.minMaxAvgPaymentsByCompany());

		// El ratio de compañías con pagos no finalizados.
		result.addObject("RatioCompanyNoFinish", paymentService.RatioCompanyNoFinish());

		// La media de pagos no finalizados por compañía.
		result.addObject("AvgPaymentsNoFinishByCompany", paymentService.AvgPaymentsNoFinishByCompany());

		// La media de notas por verificador.
		result.addObject("avgNotesByVerifier", noteService.avgNotesByVerifier());

		// La media de notas, agrupadas por estado.
		result.addObject("avgNotesByVerifierGroupByStatus", noteService.avgNotesByVerifierGroupByStatus());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");

		UserAccount userAccount = LoginService.getPrincipal();
		Administrator administrator = (Administrator) loginService.findActorByUsername(userAccount.getUsername());

		result.addObject("administrator",administrator);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Administrator administrator, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(administrator, null);
		} else {
			try {
				administratorService.saveEditing(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(administrator, "administrator.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator, String message) {
		ModelAndView result = new ModelAndView("administrator/edit");

		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/administratorKey/list", method = RequestMethod.GET)
	public ModelAndView listVariable() {
		ModelAndView result;
		result = new ModelAndView("administrator/listAdministratorkey");
		result.addObject("administratorkey", administratorKeyService.findAll());
		return result;
	}
	
	@RequestMapping(value = "/administratorKey/edit", method = RequestMethod.GET)
	public ModelAndView editVariable(@RequestParam AdministratorKey q, @RequestParam(required = false) String msg) {
		ModelAndView result;
		result = new ModelAndView("administrator/editAdministratorkey");
		result.addObject("administratorkey", q);
		result.addObject("msg", msg);
		return result;
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView paid() {
		ModelAndView result;
		
		result = new ModelAndView("administrator/payment");
		result.addObject("canPaid", administratorService.payment());
		
		return result;
	}

	@RequestMapping(value="/administratorKey/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEditVariable(@Valid AdministratorKey editAdministratorkey, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createAdministratorKeyEditModelAndView(editAdministratorkey, null);
		} else {
			try {
				try {
					administratorKeyService.save(editAdministratorkey);
					
					result = new ModelAndView("redirect:/administrator/administratorKey/list.do");
				} catch(IllegalArgumentException e) {
					result = new ModelAndView(String.format("redirect:/administrator/administratorKey/edit.do?q=%d&msg=%s", editAdministratorkey.getId(), e.getMessage()));
				}
			} catch (Throwable th) {
				result = createAdministratorKeyEditModelAndView(editAdministratorkey, "administrator.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createAdministratorKeyEditModelAndView(AdministratorKey administratorkey, String message) {
		ModelAndView result = new ModelAndView("administrator/editAdministratorkey");

		result.addObject("administratorkey", administratorkey);
		result.addObject("message", message);

		return result;
	}
	
}
