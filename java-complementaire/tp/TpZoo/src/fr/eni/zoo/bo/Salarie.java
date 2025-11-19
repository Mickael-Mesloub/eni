package fr.eni.zoo.bo;

import java.util.Objects;

public abstract class Salarie {
    private int id;
    private String nom;
    private String prenom;
    private double salaire;
    private Adresse adresse;

    public Salarie(int id, String nom, String prenom, double salaire, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.adresse = adresse;
    }

    public Salarie(String nom, String prenom, double salaire, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.adresse = adresse;
    }

    public Salarie() {
    }

    @Override
    public String toString() {
        return "Salarie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", salaire=" + salaire +
                ", adresse=" + adresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Salarie salarie)) return false;
        return id == salarie.id && Double.compare(salaire, salarie.salaire) == 0 && Objects.equals(nom, salarie.nom) && Objects.equals(prenom, salarie.prenom) && Objects.equals(adresse, salarie.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, salaire, adresse);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public double getSalaire() {
        return salaire;
    }
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Adresse getAdresse() {
        return adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
