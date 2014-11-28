package projetweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.metamodel.relational.Size;
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
import projetweb.repository.EmployesRepository;
import projetweb.repository.FactureRepository;

@Controller
public class EmployesController {

	@Autowired
	private EmployesRepository EmployesRepository;

	@Autowired
	FactureRepository FactureRepository;
	
	@RequestMapping(value = "/createemploye", method = RequestMethod.POST)
	public String submitFormemploye(@ModelAttribute Employes Employes,HttpSession session, Model model) {
		



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
	public String editFormemploye(@RequestParam("id") Long id, Model model,HttpSession session) {
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			 // emp.setAth(false);
			  System.out.println("ssdf"+emp.getNom());
			  System.out.println(""+emp.getAth());
			//  session.setAttribute("emp", emp);
		}
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
	public String loginemploye(Model model,HttpSession session) {
		//model.addAttribute("produits", new Produits());
		model.addAttribute("employes", new Employes());
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			  emp.setAth(false);
			  session.setAttribute("emp", emp);
		}
		return "/login";
	}	
	
	@RequestMapping(value = "/accueilemploi", method = RequestMethod.POST)
	public String editPostemployepost(@ModelAttribute  @Valid Employes employes,HttpSession session,BindingResult bindingResult, Model model) {
boolean exist=false;
		ArrayList<Employes> listemploi = (ArrayList<Employes>) EmployesRepository.findAll();
		Employes emp = (Employes)session.getAttribute("emp");

		int sizeListe=listemploi.size();
		int i=0;
		while(i<sizeListe && exist==false){
		//for(int i=0;i<sizeListe;i++){
			// System.out.println(employes.getNom());
			  // System.out.println(listemploi.get(i).getNom());
			   if(employes.getNom().equals(listemploi.get(i).getNom()) & employes.getMdp().equals(listemploi.get(i).getMdp()) ){
				   System.out.println(employes.getNom());
				   System.out.println(listemploi.get(i).getNom());
	        		model.addAttribute("employes", employes.getNom());
	        		//EmployesRepository.delete(listemploi.get(i));
	        	//	listemploi.get(i).setAth(true);
	        		
	        	//	EmployesRepository.save(listemploi.get(i));
	        		employes.setAth(true);
	        		emp=employes;
	        		session.setAttribute("emp", emp);
	            	exist=true;
	            
	            }
			   
				i++;
		}
		System.out.println(exist);
	
	if(exist==true)
		return "/accueil";
	else{
		ObjectError error = new ObjectError("nom","Nom ou Mot de passe Incorrect");
		bindingResult.addError(error);
		bindingResult.rejectValue("nom", "error.user", "Nom ou Mot de passe Incorrect");
	
		return "/login";}
	}
		
            

	
	@RequestMapping(value = "/accueilemploi", method = RequestMethod.GET)
	public String accueilemploye(Model model, HttpSession session) {
		//model.addAttribute("produits", new Produits());
		boolean aut=false;
		Employes emp = (Employes)session.getAttribute("emp");
		if(emp==null){
      
		}else{
			if(emp.getAth()) {
				
				aut=true;
				
			}
			
			
		}
		
		if(aut){
			
			return "/accueil";
			
		}else{
			return "redirect:/loginemploi";
		}

		
	}
	
	  @RequestMapping(	value ="/singout", method=RequestMethod.POST)
	  public String logout(Model model,HttpSession session) {

			Employes emp = (Employes)session.getAttribute("emp");
			if(emp==null){
	      
			}else{
				emp.setAth(false);
			
					session.setAttribute("emp", emp);
			}
		
			
			List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
			for(int i=0;i<listfacture.size();i++){
				FactureRepository.delete(listfacture.get(i));
			}
	   
	   
	    return "redirect:/loginemploi";
	  }
	}
	
	

	
	
