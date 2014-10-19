package projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projetweb.model.Employes;
import projetweb.repository.EmployesRepository;


@Controller
public class EmployesController {

	@Autowired
	private EmployesRepository EmployesRepository;
	
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String accueil(Model model) {
		//model.addAttribute("Employes", new Employes());
		return "/admin/accueil";
	}
	
	@RequestMapping(value = "/createemploye", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Employes Employes, Model model) {
		
		EmployesRepository.save(Employes);
		return "redirect:/";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listemployeProducts(Model model) {
		
		model.addAttribute("Employes", EmployesRepository.findAll());
		return "/admin/listemploye";
	}
	
	@RequestMapping(value = "/createemploye", method = RequestMethod.GET)
	public String submitForme( Model model) {
		model.addAttribute("Employes", new Employes());
		
		return "/admin/createemploye";
	}

	
	
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		EmployesRepository.delete(id);
		
		return "redirect:/";
	}
	
	
	// modification
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("Employes", EmployesRepository.findOne(id));
		return "/admin/createemploye";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Employes Employes, Model model) {
		EmployesRepository.save(Employes);
		return "redirect:/";
	}

	
//
}