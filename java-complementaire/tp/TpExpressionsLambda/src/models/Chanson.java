package models;


public class Chanson {
	private String titre;
	private String chanteur;
	private double duree;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getChanteur() {
		return chanteur;
	}
	public void setChanteur(String chanteur) {
		this.chanteur = chanteur;
	}
	public double getDuree() {
		return duree;
	}
	public void setDuree(double duree) {
		this.duree = duree;
	}
	
	public Chanson() {
	}
	
	public Chanson(String titre, String chanteur, double duree) {
		super();
		this.titre = titre;
		this.chanteur = chanteur;
		this.duree = duree;
	}
	@Override
	public String toString() {
		return String.format("Chanson [titre=%s, chanteur=%s, duree=%s]", titre, chanteur, duree);
	}
	
}
