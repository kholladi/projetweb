package projetweb.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.hibernate.metamodel.relational.Size;
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
	
	
	
	@RequestMapping(value = "/createemploye", method = RequestMethod.POST)
	public String submitFormemploye(@ModelAttribute Employes Employes, Model model) {
		
		EmployesRepository.save(Employes);
		return "redirect:/employes";
	}
	@RequestMapping(value = "/employes", method = RequestMethod.GET)
	public String listemployeemploye(Model model) {
		
		model.addAttribute("employes", EmployesRepository.findAll());
		return "/admin/listemploye";
	}
	
	@RequestMapping(value = "/createemploye", method = RequestMethod.GET)
	public String submitFormeemploye( Model model) {
		model.addAttribute("employes", new Employes());
		
		return "/admin/createemploye";
	}

	
	
	
	
	
	@RequestMapping(value = "/deleteemploye", method = RequestMethod.GET)
	public String deleteemploye(@RequestParam("id") Long id, Model model) {
		
		EmployesRepository.delete(id);
		
		return "redirect:/employes";
	}
	
	
	// modification
	@RequestMapping(value = "/editemploye", method = RequestMethod.GET)
	public String editFormemploye(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("employes", EmployesRepository.findOne(id));
		return "/admin/createemploye";
	}
	
	@RequestMapping(value = "/editemploye", method = RequestMethod.POST)
	public String editPostemploye(@ModelAttribute Employes Employes, Model model) {
		EmployesRepository.save(Employes);
		return "redirect:/employes";
	}
	
	
	
//
	@RequestMapping(value = "/loginemploi", method = RequestMethod.GET)
	public String loginemploye(Model model) {
		//model.addAttribute("produits", new Produits());
		model.addAttribute("employes", new Employes());
		
		return "/login";
	}	
	
	@RequestMapping(value = "/accueilemploi", method = RequestMethod.POST)
	public String editPostemployepost(@ModelAttribute Employes employes, Model model) {
boolean exist=false;
		ArrayList<Employes> listemploi = (ArrayList<Employes>) EmployesRepository.findAll();
		int sizeListe=listemploi.size();
		int i=0;
		while(i<sizeListe && exist==false){
		//for(int i=0;i<sizeListe;i++){
			// System.out.println(employes.getNom());
			  // System.out.println(listemploi.get(i).getNom());
			   if(employes.getNom().equals(listemploi.get(i).getNom())  ){
				   System.out.println(employes.getNom());
				   System.out.println(listemploi.get(i).getNom());
	        		model.addAttribute("employes", employes.getNom());
	            	exist=true;
	            
	            }
			   
				i++;
		}
		System.out.println(exist);
	
	if(exist==true)
		return "/accueil";
	else 
		return "/login";
	}
		/*for (Employes emploi : listemploi) {
            System.out.println(emploi.getNom()); // list de tous les employes
            
            if(employes.getNom() == emploi.getNom() ){
        		model.addAttribute("employes", emploi.getNom());
            	return "/accueil";
            }
*/
            

	
	@RequestMapping(value = "/accueilemploi", method = RequestMethod.GET)
	public String accueilemploye(Model model) {
		//model.addAttribute("produits", new Produits());
		model.addAttribute("employes", new Employes());
		return "/accueil";
	}
	
	
	
}