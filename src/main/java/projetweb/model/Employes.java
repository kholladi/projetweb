package projetweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employes implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id = -1;
    
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String numero;
    private String mail;
    private String mdp;
    
    
	public Employes() {}


	public Employes(long id, String nom, String prenom, String adresse,
			String numero, String mail, String mdp, String civilite) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numero = numero;
		this.mail = mail;
		this.mdp = mdp;
		this.civilite = civilite;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}




	


}