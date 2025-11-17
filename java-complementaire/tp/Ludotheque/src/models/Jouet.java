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

    @Override
    void affichageInformation() {
        StringBuilder info = new StringBuilder();
        info.append("Titre : ").append(getTitre()).append(", ")
                .append("Etat : ").append(getEtat().toString().toUpperCase()).append(", ")
                .append("à partir de ").append(getAge()).append(" ans");

        System.out.println(info.toString());
    }
}
