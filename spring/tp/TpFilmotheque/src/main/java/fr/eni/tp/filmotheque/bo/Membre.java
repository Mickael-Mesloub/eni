package fr.eni.tp.filmotheque.bo;

import java.util.Objects;

public class Membre extends Personne {
    private String pseudo;
    private String motDePasse;
    private Boolean admin;

    public Membre(int id, String nom, String prenom, String pseudo, Boolean admin) {
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
