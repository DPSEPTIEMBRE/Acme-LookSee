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

import domain.Company;
import domain.Curricula;
import domain.Offer;
import security.LoginService;
import services.ActivityReportService;
import services.CacheService;
import services.CompanyService;
import services.OfferService;
import services.PaymentService;

@Controller
@RequestMapping("/offer")
public class OfferController {

	@Autowired
	OfferService offerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ActivityReportService activityReportService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	CacheService cacheService;
	
	@RequestMapping(value = "/candidate/searcher", method = RequestMethod.GET)
	public ModelAndView offerSearcher(HttpServletRequest request) {
		ModelAndView result;
		
		result = new ModelAndView("offer/searcher");
		
		return result;
	}

	@RequestMapping(value = "/company/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(offerService.create(), null);

		return result;
	}

	@RequestMapping(value="/company/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Offer offer, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(offer, null);
		} else {
			try {
				if(!offerService.isValidDate(offer)) {
					result = createNewModelAndView(offer, null);
					result.addObject("dateError", true);
				} else {
					Offer saved = offerService.save(offer);
					activityReportService.createDefault("Offer publish", "An offer was publish!");
					paymentService.create_auto(saved);
					result = new ModelAndView("redirect:/offer/company/list.do?q=" + offer.getCompany().getId());
				}
			} catch (Throwable th) {
				result = createNewModelAndView(offer, "offer.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Offer offer, String message) {
		ModelAndView result;
		result = new ModelAndView("offer/create");
		result.addObject("offer", offer);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/candidate/list-advanced", method = RequestMethod.GET)
	public ModelAndView listAdvanced(HttpServletRequest request, @RequestParam(defaultValue = "0") Double min, @RequestParam(defaultValue = "-1") Double max, @RequestParam(defaultValue = "") String q) {
		ModelAndView result;

		List<Offer> offers = offerService.advanced_search(min, max, q, request.getSession().getId());
		result = new ModelAndView("offer/advanced_list");
		result.addObject("offer", offers);
		
		cacheService.createOffer(offers, request.getSession().getId());

		return result;
	}

	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) Company q) {
		ModelAndView result;

		result = new ModelAndView("offer/list");
		if(q != null) {
			result.addObject("offer", offerService.filterOf(q));
		} else {
			result.addObject("offer", companyService.selectSelfCompanyOffer());
		}

		return result;
	}
	
	@RequestMapping(value = "/actor/listAll", method = RequestMethod.GET)
	public ModelAndView listAll(@RequestParam(required = false) String q) {
		ModelAndView result;

		result = new ModelAndView("offer/listAll");
		result.addObject("offer", offerService.findQ(q, LoginService.isAnyAuthenticated() ? LoginService.hasRole("ADMINISTRATOR") : false));

		return result;
	}
	
	@RequestMapping(value = "/candidate/solicitation", method = RequestMethod.GET)
	public ModelAndView solicitation(HttpServletRequest request, @RequestParam Offer q) {
		ModelAndView result;

		request.getSession().setAttribute("offer_id", q.getId());
		
		result = new ModelAndView("offer/solicitation");
		result.addObject("curricula", offerService.getAvaliableCurriculas());

		return result;
	}
	
	@RequestMapping(value = "/candidate/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request, @RequestParam Curricula curricula) {
		ModelAndView result;
		
		result = new ModelAndView("offer/okpage");
		
		try {
			int offer_id = (int) request.getSession().getAttribute("offer_id");
			
			offerService.apply(offer_id, curricula);
			activityReportService.createDefault("Offer solicitated", "An offer was solicitated!");
			result.addObject("error", false);
		} catch(Exception e) {
			result.addObject("error", true);
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value = "/actor/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam Offer q) {
		ModelAndView result;

		result = new ModelAndView("offer/view");
		result.addObject("offer", q);

		return result;
	}

	@RequestMapping(value = "/company/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Offer q) {
		ModelAndView result;
		
		Company c = companyService.selectByUsername(LoginService.getPrincipal().getUsername());
		
		if(c != null) {
			if(c.getOffers().contains(q)) {
				result = new ModelAndView("offer/edit");
				result.addObject("offer", q);
			} else {
				result = list(null);
			}
		} else {
			return list(null);
		}
		
		return result;
	}

	@RequestMapping(value="/company/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Offer offer, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(offer, null);
	} else {
		try {
			offerService.save(offer);
				result = new ModelAndView("redirect:/offer/actor/list.do?q=" + offer.getCompany().getId());
			} catch (Throwable th) {
				result = createEditModelAndView(offer, "offer.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Offer offer, String message) {
		ModelAndView result = new ModelAndView("offer/edit");

		result.addObject("offer", offer);
		result.addObject("message", message);

		return result;
	}

}
