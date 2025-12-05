package fr.eni.demoSpringJDBC.bo;

import java.util.Objects;

public class Pizza {
	private Integer id;
	private String nom;
	private float prix;

	public Pizza() {
		//super();
	}
	
	public Pizza( String nom, float prix) {
		this.nom = nom;
		this.prix = prix;
	}

	
	public Pizza(Integer id, String nom, float prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nom=" + nom + ", prix=" + prix + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom)
				&& Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix);
	}
	
	
}
