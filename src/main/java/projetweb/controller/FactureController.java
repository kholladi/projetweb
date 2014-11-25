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
	public String caisseemploye(Model model, @ModelAttribute Facture facture,
			HttpSession session) {
		model.addAttribute("prod", new Produits());
		model.addAttribute("products", ProduitsRepository.findAll());

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
					// facture.setPrix(listfacture.get(j).getPrix());
					// System.out.println(listproduit.get(i).getNom());
					// System.out.println(listfacture.get(j).getProduit());
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

		return "caisse";
	}

	@RequestMapping(value = "/caisse", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Facture facture, Model model) {

		List<Facture> listfacture = (List<Facture>) FactureRepository.findAll();
		for (int j = 0; j < listfacture.size(); j++) {

			if (facture.getProduit().equals(listfacture.get(j).getProduit())) {
				listfacture.get(j).setQuantite(listfacture.get(j).getQuantite() + facture.getQuantite());
				listfacture.get(j).setExist(true);
			}

		}

		FactureRepository.save(facture);

		// System.out.println(facture.getProduit());
		// System.out.println(facture.getPrix());

		return "redirect:/caisse";
	}

	@RequestMapping(value = "/deletefacture", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Long id, Model model) {

		FactureRepository.delete(id);

		return "redirect:/caisse";
	}

	@RequestMapping(value = "/editfacture", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {

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
					// facture.setPrix(listfacture.get(j).getPrix());
					// System.out.println(listproduit.get(i).getNom());
					// System.out.println(listfacture.get(j).getProduit());
				}
			}
		}

		model.addAttribute("facturess", FactureRepository.findOne(id));

		return "caisse";
	}

	@RequestMapping(value = "/editfacture", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Facture facture, Model model) {
		FactureRepository.save(facture);
		return "redirect:/caisse";
	}

	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public String facturation(Model model, @ModelAttribute Facture facture) {

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
					// facture.setPrix(listfacture.get(j).getPrix());
					// System.out.println(listproduit.get(i).getNom());
					// System.out.println(listfacture.get(j).getProduit());
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

		return "facture";
	}

}