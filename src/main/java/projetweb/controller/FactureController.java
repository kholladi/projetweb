package projetweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projetweb.model.Employes;
import projetweb.model.Facture;
import projetweb.model.Produits;
import projetweb.repository.EmployesRepository;
import projetweb.repository.FactureRepository;
import projetweb.repository.ProduitsRepository;

@Controller
public class FactureController {

	@Autowired
	private FactureRepository FactureRepository;
	@Autowired
	private ProduitsRepository ProduitsRepository;
	
	@Autowired
	private EmployesRepository EmployesRepository;
	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String caisseemploye(Model model, @ModelAttribute Facture facture,
			HttpSession session) {
		
	
		boolean aut=false;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				model.addAttribute("prod", new Produits());
				model.addAttribute("products", ProduitsRepository.findAll());
				aut=true;

				String pm="" ;
				long mid=0;
				model.addAttribute("MOD", pm);
				model.addAttribute("MD", mid);
				model.addAttribute("facture", new Facture());
				Facture facremove = null;

				List<Produits> listproduit = (List<Produits>) ProduitsRepository
						.findAll();
				List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();

				if (listfacture != null && !listfacture.isEmpty()) {
					model.addAttribute("facturess", listfacture);

					for (int i = 0; i < listfacture.size(); i++) {
						if (listfacture.get(i).getExist()) {
							listfacture.get(i).setExist(false);
							facremove = listfacture.get(listfacture.size() - 1);
							FactureRepository.delete(facremove);
							 listfacture.remove(listfacture.size() - 1);
							 

						}
					}

					for (int i = 0; i < listproduit.size(); i++) {
						for (int j = 0; j < listfacture.size(); j++) {

							if (listproduit.get(i).getNom()
									.equals(listfacture.get(j).getProduit()))
								listfacture.get(j)
										.setPrix(listproduit.get(i).getPrix());
							
						}
					}
				}

				for (int i = 0; i < listfacture.size(); i++) {

					listfacture.get(i).setTotal(
							listfacture.get(i).getPrix()
									* listfacture.get(i).getQuantite());
					System.out.println(listfacture.get(i).getQuantite());
				}

				long somme = 0;
				for (int i = 0; i < listfacture.size(); i++) {
					somme = somme + listfacture.get(i).getTotal();
				}

				facture.setTtotal(somme);
				model.addAttribute("somme", facture.getTtotal());

				System.out.println("la somme est" + somme);
				System.out.println("get  " + facture.getTtotal());
				
			}
			
			
		}
		
		
		
		
if(aut){
			
			return "caisse";
			
		}else{
			return "redirect:/loginemploi";
		}
		
		
	}

	@RequestMapping(value = "/caisse", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute @Valid Facture facture, Model model,HttpSession session,BindingResult bindingResult) {
		List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
		List<Produits> listpr = (List<Produits>) ProduitsRepository.findAll();
		boolean aut=false;
		boolean stock=false;
		long Quan = 0;
		String pro = null;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				
				aut=true;
				
				
				

				facture.setStock(false);
   for (int j = 0; j < listpr.size(); j++) {
					
					if (facture.getProduit().equals(listpr.get(j).getNom())) {
						
						if(facture.getQuantite()>listpr.get(j).getQuantite()){
							stock=true;
							facture.setStock(false);
							ObjectError error = new ObjectError("quantite","Stock épuisé");
							bindingResult.addError(error);
							bindingResult.rejectValue("quantite", "error.user", "Stock épuisé");
							facture.setStock(false);
							System.out.println("llsdjfs");
							
						}}
   }
				
				
				for (int j = 0; j < listfacture.size(); j++) {
					
					if (facture.getProduit().equals(listfacture.get(j).getProduit())) {
					
						
						
						listfacture.get(j).setQuantite(listfacture.get(j).getQuantite() + facture.getQuantite());
						listfacture.get(j).setExist(true);
					}

				}

				
				
			}
			
			
		}



		if(aut & stock){
			
			
			FactureRepository.save(facture);
			return "redirect:/strockProduit";
			
		}else{

		if(aut){
			 for (int j = 0; j < listpr.size(); j++) {
					
					if (facture.getProduit().equals(listpr.get(j).getNom())) {
						if(listpr.get(j).getQuantite()<=0){
							listpr.get(j).setQuantite(0);
						}else{
						listpr.get(j).setQuantite(listpr.get(j).getQuantite()-facture.getQuantite());
						}
						ProduitsRepository.delete(listpr.get(j));
						ProduitsRepository.save(listpr.get(j));
					}
}
			FactureRepository.save(facture);
			return "redirect:/caisse";

		
	}else{
		return "redirect:/loginemploi";
	}
		}
	

	}

	@RequestMapping(value = "/deletefacture", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam(value="id", required = false) Long id, Model model,HttpSession session) {

		
		boolean aut=false;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				
				aut=true;
				FactureRepository.delete(id);
			}
			
			
		}

		
		
	
if(aut){
			
				return "redirect:/caisse";

			
		}else{
			return "redirect:/loginemploi";
		}
		
		

	}

	@RequestMapping(value = "/editfacture", method = RequestMethod.GET)
	public String editForm(@RequestParam(value="id", required = false) Long id, Model model,HttpSession session) {

		
		boolean aut=false;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				
				aut=true;
				model.addAttribute("prod", new Produits());
				model.addAttribute("products", ProduitsRepository.findAll());

				model.addAttribute("facture", new Facture());

				List<Produits> listproduit = (List<Produits>) ProduitsRepository
						.findAll();
				List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();

				if (listfacture != null && !listfacture.isEmpty()) {
					model.addAttribute("facturess", listfacture);

					for (int i = 0; i < listproduit.size(); i++) {
						for (int j = 0; j < listfacture.size(); j++) {

							if (listproduit.get(i).getNom()
									.equals(listfacture.get(j).getProduit()))
								listfacture.get(j)
										.setPrix(listproduit.get(i).getPrix());
							
						}
					}
				}
				Facture fac=FactureRepository.findOne(id);
				long mid=0;
				for (int i = 0; i < listproduit.size(); i++) {
					if(fac.getProduit().equals(listproduit.get(i).getNom())){
						mid=listproduit.get(i).getId();
						
					}
				
				}

				String pm="Produit " +fac.getProduit();
				model.addAttribute("facturess", FactureRepository.findOne(id));
				model.addAttribute("MD", mid);
				model.addAttribute("MOD", pm);
				
				
			}
			
			
		}


		
	
if(aut){
			
	      return "caisse";

			
		}else{
			return "redirect:/loginemploi";
		}
		

		
	}

	@RequestMapping(value = "/editfacture", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Facture facture, Model model) {
		FactureRepository.save(facture);
		return "redirect:/caisse";
	}

	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public String facturation(Model model, @ModelAttribute Facture facture,HttpSession session) {

		
		boolean aut=false;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				
				aut=true;
				
				model.addAttribute("facturesss", FactureRepository.findAll());

				List<Produits> listproduit = (List<Produits>) ProduitsRepository
						.findAll();
				List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();

				if (listfacture != null && !listfacture.isEmpty()) {
					model.addAttribute("facturess", listfacture);

					for (int i = 0; i < listproduit.size(); i++) {
						for (int j = 0; j < listfacture.size(); j++) {

							if (listproduit.get(i).getNom()
									.equals(listfacture.get(j).getProduit()))
								listfacture.get(j)
										.setPrix(listproduit.get(i).getPrix());
							
						}
					}
				}
				for (int i = 0; i < listfacture.size(); i++) {

					listfacture.get(i).setTotal(
							listfacture.get(i).getPrix()
									* listfacture.get(i).getQuantite());
					System.out.println(listfacture.get(i).getQuantite());
				}

				long somme = 0;
				for (int i = 0; i < listfacture.size(); i++) {
					somme = somme + listfacture.get(i).getTotal();
				}

				facture.setTtotal(somme);
				model.addAttribute("somme", facture.getTtotal());

				System.out.println("la somme est" + somme);
				System.out.println("get  " + facture.getTtotal());
				
				
				long totaltva = 0;
				totaltva = (long) (somme + (somme * 19.6/100));
				model.addAttribute("totaltva", totaltva);
				
				
				
			}
			
			
		}

	
		
	
if(aut){
			
	return "facture";

			
		}else{
			return "redirect:/loginemploi";
		}
		

		
	}
	@RequestMapping(value = "/vider", method = RequestMethod.POST)
	public String videPost(@ModelAttribute Facture facture, Model model) {
		List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
		for(int i=0;i<listfacture.size();i++){
			FactureRepository.delete(listfacture.get(i));
		}
		return "redirect:/caisse";
	}
	@RequestMapping(value = "/strockProduit", method = RequestMethod.GET)
	public String stock(@ModelAttribute Facture facture, Model model) {
		List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
		List<Produits> listpr = (List<Produits>) ProduitsRepository.findAll();

		for (int i = 0; i < listfacture.size(); i++) {
			if(!listfacture.get(i).getStock()){
				for (int j = 0; j < listpr.size(); j++) {
                if(listfacture.get(i).getProduit().equals(listpr.get(j).getNom())){
				System.out.println("kkkkkkkk");
				model.addAttribute("quan", listpr.get(j).getQuantite());
				model.addAttribute("pro", listpr.get(j).getNom());	
				FactureRepository.delete(listfacture.get(i));
				}}
				
				
				
			}
		}
		
		
		
		return "strockProduit";
	}

}