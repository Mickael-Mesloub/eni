package models;

import models.enums.Etat;

public abstract class Jeu {
    private int id;
    private String titre;
    private Etat etat;
    private int age;
    private String description;

    public Jeu() {
    }

    public Jeu(int id, String titre, Etat etat, int age, String description) {
        this.id = id;
        this.titre = titre;
        this.etat = etat;
        this.age = age;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
