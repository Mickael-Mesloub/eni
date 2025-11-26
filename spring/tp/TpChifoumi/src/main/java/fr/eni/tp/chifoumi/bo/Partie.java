package fr.eni.tp.chifoumi.bo;

import java.util.Objects;

public class Partie {
    private int choixUtilisateur;
    private int choixOrdinateur;
    private int resultat;

    public Partie(int choixUtilisateur, int choixOrdinateur, int resultat) {
        this.choixUtilisateur = choixUtilisateur;
        this.choixOrdinateur = choixOrdinateur;
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "choixUtilisateur=" + choixUtilisateur +
                ", choixOrdinateur=" + choixOrdinateur +
                ", resultat=" + resultat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partie partie = (Partie) o;
        return choixUtilisateur == partie.choixUtilisateur && choixOrdinateur == partie.choixOrdinateur && resultat == partie.resultat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(choixUtilisateur, choixOrdinateur, resultat);
    }

    public int getChoixUtilisateur() {
        return choixUtilisateur;
    }

    public void setChoixUtilisateur(int choixUtilisateur) {
        this.choixUtilisateur = choixUtilisateur;
    }

    public int getChoixOrdinateur() {
        return choixOrdinateur;
    }

    public void setChoixOrdinateur(int choixOrdinateur) {
        this.choixOrdinateur = choixOrdinateur;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }
}
