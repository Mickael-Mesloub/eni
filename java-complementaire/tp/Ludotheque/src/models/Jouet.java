package models;

import models.enums.Etat;

public class Jouet extends Jeu{
    public Jouet(int id, String titre, Etat etat, int age, String description) {
        super(id, titre, etat, age, description);
    }

    public Jouet() {
    }

}
