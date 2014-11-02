package projetweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facture implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id = -1;
    
    private String nomclient;
    private String prenomclient;
    private String produit;
    private long prix;
    private long quantite;

	public Facture() {}

	public Facture(String nomclient, String prenomclient, String produit, long prix, long quantite) {
		this.nomclient = nomclient;
		this.prenomclient = prenomclient;
		this.produit = produit;
		this.prix = prix;
		this.quantite = quantite;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomclient() {
		return nomclient;
	}

	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}

	public String getPrenomclient() {
		return prenomclient;
	}

	public void setPrenomclient(String prenomclient) {
		this.prenomclient = prenomclient;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public long getPrix() {
		return prix;
	}

	public void setPrix(long prix) {
		this.prix = prix;
	}

	public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}



	


}