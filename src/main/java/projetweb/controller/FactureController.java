package projetweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projetweb.model.Employes;
import projetweb.model.Facture;
import projetweb.model.Produits;
import projetweb.repository.FactureRepository;
import projetweb.repository.ProduitsRepository;


@Controller
public class FactureController {

	 @Autowired
	private FactureRepository FactureRepository;
	 @Autowired
    private ProduitsRepository ProduitsRepository;

 
		@RequestMapping(value = "/caisse", method = RequestMethod.GET)
		public String caisseemploye(Model model, @ModelAttribute Produits produit) {
			model.addAttribute("prod", new Produits());
			model.addAttribute("products", ProduitsRepository.findAll());	
			
			model.addAttribute("facture", new Facture());
			
			
			List<Facture> l = (List<Facture>) FactureRepository.findAll();
			if (l != null && !l.isEmpty())
				model.addAttribute("facturess", l);
			
			
	/*		List<Produits> listproduit = (List<Produits>) ProduitsRepository.findAll();
			int sizeListe=listproduit.size();
			int i=0;
			while(i<sizeListe){
				System.out.println(produit.getNom());
				i++;		
			}
			*/
			

			return "caisse";
		}	
		
		
		
		
		@RequestMapping(value = "/caisse", method = RequestMethod.POST)
		public String submitForm(@ModelAttribute Facture facture, Model model) {
			
			FactureRepository.save(facture);
			
			System.out.println(facture.getProduit());		
			
	
			return "redirect:/caisse";
		}
		



		
		
		
		
		


}