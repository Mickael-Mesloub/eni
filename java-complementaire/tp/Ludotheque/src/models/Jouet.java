package models;

import models.enums.Etat;

public class Jouet extends Jeu{
    private TypeJouet type;

    public Jouet(int id, String titre, Etat etat, int age, String description, TypeJouet type) {
        super(id, titre, etat, age, description);
        this.type = type;
    }

    public Jouet() {
    }

    public TypeJouet getType() {
        return type;
    }
    public void setType(TypeJouet type) {
        this.type = type;
    }
}
