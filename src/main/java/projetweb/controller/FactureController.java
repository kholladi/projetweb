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
import projetweb.repository.FactureRepository;
import projetweb.repository.ProduitsRepository;


@Controller
public class FactureController {

	 @Autowired
	private FactureRepository FactureRepository;
    private ProduitsRepository ProduitsRepository;

		
		@RequestMapping(value = "/caisse", method = RequestMethod.POST)
		public String submitForm(@ModelAttribute Facture facture, Model model) {
			
			FactureRepository.save(facture);
			
			System.out.println(facture.getProduit());		
			
	
			return "redirect:/caisse";
		}
		



		
		
		
		
		


}