package models;

import models.enums.Etat;

public class JeuSociete extends Jeu {
    private double difficulte;
    private double interet;

    public JeuSociete(double difficulte, double interet) {
        this.difficulte = difficulte;
        this.interet = interet;
    }

    public JeuSociete(int id, String titre, Etat etat, int age, String description, double difficulte, double interet) {
        super(id, titre, etat, age, description);
        this.difficulte = difficulte;
        this.interet = interet;
    }

    public double getDifficulte() {
        return difficulte;
    }
    public void setDifficulte(double difficulte) {
        this.difficulte = difficulte;
    }

    public double getInteret() {
        return interet;
    }
    public void setInteret(double interet) {
        this.interet = interet;
    }
}
