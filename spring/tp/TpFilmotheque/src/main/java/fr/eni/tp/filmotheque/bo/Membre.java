package fr.eni.tp.filmotheque.bo;

import java.util.Objects;

public class Membre extends Personne {
    private String pseudo;
    private String motDePasse;
    private Boolean admin;

    public Membre(long id, String nom, String prenom, String pseudo, Boolean admin) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "pseudo='" + pseudo + '\'' +
                "admin=" + admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Membre membre = (Membre) o;
        return admin == membre.admin && Objects.equals(pseudo, membre.pseudo) && Objects.equals(motDePasse, membre.motDePasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, motDePasse, admin);
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
