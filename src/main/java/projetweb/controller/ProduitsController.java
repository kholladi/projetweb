package projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projetweb.model.Produits;
import projetweb.repository.ProduitsRepository;


@Controller
public class ProduitsController {

	@Autowired
	private ProduitsRepository ProduitsRepository;
	
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String accueil(Model model) {
		model.addAttribute("produits", new Produits());
		return "/admin/accueil";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String accueiladmin(Model model) {
		model.addAttribute("produits", new Produits());
		return "/admin/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("produits", new Produits());
		return "/admin/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Produits Produits, Model model) {
		
		ProduitsRepository.save(Produits);
		return "redirect:/admin/list";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listProducts(Model model) {
		
		model.addAttribute("produits", ProduitsRepository.findAll());
		return "/admin/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		ProduitsRepository.delete(id);
		
		return "redirect:/admin/list";
	}
	
	
	// modification
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("produits", ProduitsRepository.findOne(id));
		return "/admin/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Produits Produits, Model model) {
		ProduitsRepository.save(Produits);
		return "redirect:/list";
	}

	

}