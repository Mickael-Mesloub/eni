package fr.eni.tp.filmotheque.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Personne implements Serializable {
    /**
     * Numéro de sérialisation
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nom;
    private String prenom;
    
    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne() {
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return getId() == personne.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
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
}
