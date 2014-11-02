package projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projetweb.model.Facture;
import projetweb.model.Produits;
import projetweb.repository.ProduitsRepository;



@Controller
public class ProduitsController {

	 @Autowired
	private ProduitsRepository ProduitsRepository;
	
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String accueil(Model model) {
		//model.addAttribute("produits", new Produits());
		return "/admin/accueil";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String accueilr(Model model) {
		//model.addAttribute("produits", new Produits());
		return "/admin/accueil";
	}
	
	
	@RequestMapping(value = "/ac", method = RequestMethod.GET)
	public String accueill(Model model) {
		//model.addAttribute("produits", new Produits());
		return "/admin/accueil";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		//model.addAttribute("produits", new Produits());
		return "/admin/login";
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Produits Produits, Model model) {
		
		ProduitsRepository.save(Produits);
		return "redirect:/prod";
	}
	@RequestMapping(value = "/prod", method = RequestMethod.GET)
	public String listProducts(Model model) {
		
		model.addAttribute("produits", ProduitsRepository.findAll());
		return "/admin/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String submitForme( Model model) {
		model.addAttribute("produits", new Produits());
		
		return "/admin/create";
	}

	
	

	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {
		
		ProduitsRepository.delete(id);
		
		return "redirect:/prod";
	}
	
	
	// modification
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("produits", ProduitsRepository.findOne(id));
		return "/admin/create";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Produits Produits, Model model) {
		ProduitsRepository.save(Produits);
		return "redirect:/prod";
	}

	
	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String caisseemploye(Model model) {
		model.addAttribute("prod", new Produits());
		model.addAttribute("products", ProduitsRepository.findAll());	
		
		model.addAttribute("factures", new Facture());
		
		return "/caisse";
	}	
	


}