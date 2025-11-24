package fr.eni.demospring.bo;

import java.util.Objects;

public class Formateur {
    private String nom;
    private String prenom;
    private String email;

    public Formateur() {
    }

    public Formateur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Formateur formateur = (Formateur) o;
        return Objects.equals(nom, formateur.nom) && Objects.equals(prenom, formateur.prenom) && Objects.equals(email, formateur.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, email);
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
