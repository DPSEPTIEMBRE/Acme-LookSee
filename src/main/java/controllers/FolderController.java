package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Folder;
import services.FolderService;

@Controller
@RequestMapping("/folder")
public class FolderController {

	@Autowired
	FolderService folderService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(folderService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Folder folder, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(folder, null);
		} else {
			try {
				folderService.saveCreate(folder);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (Throwable th) {
				result = createNewModelAndView(folder, "folder.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Folder folder, String message) {
		ModelAndView result;
		result = new ModelAndView("folder/create");
		result.addObject("folder", folder);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/actor/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Folder folder) {
		ModelAndView result;
		
		folderService.delete(folder);
		
		result = new ModelAndView("folder/list");
		result.addObject("folder", folderService.folderOfSelf());

		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		result = new ModelAndView("folder/list");
		result.addObject("folder", folderService.folderOfSelf());

		return result;
	}

	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Folder folder) {
		ModelAndView result;
		result = new ModelAndView("folder/edit");
		result.addObject("folder", folder);
		return result;
	}

	@RequestMapping(value = "/actor/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Folder folder) {
		ModelAndView result;

		try {
			folderService.delete(folder);
			result = new ModelAndView("redirect:/folder/actor/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(folder, "folder.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/actor/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Folder folder, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(folder, null);
	} else {
		try {
			folderService.save(folder);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(folder, "folder.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Folder folder, String message) {
		ModelAndView result = new ModelAndView("folder/edit");

		result.addObject("folder", folder);
		result.addObject("message", message);

		return result;
	}

}
