package projetweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
		public String caisseemploye(Model model, @ModelAttribute Facture facture, HttpSession session) {
			model.addAttribute("prod", new Produits());
			model.addAttribute("products", ProduitsRepository.findAll());	
			
			model.addAttribute("facture", new Facture());
			
			
			
			List<Produits> listproduit = (List<Produits>) ProduitsRepository.findAll();
			List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
			
			
			if (listfacture != null && !listfacture.isEmpty()){
				model.addAttribute("facturess", listfacture);


			for(int i=0; i<listproduit.size(); i++){
				for(int j=0; j<listfacture.size(); j++){
					
					if(listproduit.get(i).getNom().equals(listfacture.get(j).getProduit()))	
					  	listfacture.get(j).setPrix(listproduit.get(i).getPrix());
				//facture.setPrix(listfacture.get(j).getPrix());
				//	System.out.println(listproduit.get(i).getNom());
				//	System.out.println(listfacture.get(j).getProduit());
				}		
			}
			}
			

				

			return "caisse";
		}	
		
		
		
		
		@RequestMapping(value = "/caisse", method = RequestMethod.POST)
		public String submitForm(@ModelAttribute Facture facture, Model model) {
			
			FactureRepository.save(facture);
			
			System.out.println(facture.getProduit());	
			System.out.println(facture.getPrix());
			
	
			return "redirect:/caisse";
		}
		

		@RequestMapping(value = "/deletefacture", method = RequestMethod.GET)
		public String deleteProduct(@RequestParam("id") Long id, Model model) {
			
			FactureRepository.delete(id);
			
			return "redirect:/caisse";
		}

		
		
		
		
		


}