package fr.eni.demo.demospring.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Formateur implements Serializable {
	
	
	/**
	* Identifiant de l'interface Serializable
	*/
	private static final long serialVersionUID = 1L;

	private String nom;
	private String prenom;
	private String email;

	//1 formateur peut dispenser plusieurs cours
	private List<Cours> listeCours;

		
	public Formateur() {
		//initialisation de la liste des cours.
		listeCours = new ArrayList<Cours>();
	}

	public Formateur(String nom, String prenom, String email) {
		this();//appel du constructeur par défaut
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	// GETTER + SETTER
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	// toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Formateur [prenom=");
		builder.append(prenom);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
