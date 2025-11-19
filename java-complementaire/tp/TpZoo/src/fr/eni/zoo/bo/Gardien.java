package fr.eni.zoo.bo;

import java.util.Objects;

public class Gardien extends Salarie {
    private boolean gardienDeNuit;


    public Gardien(int id, String nom, String prenom, double salaire, Adresse adresse, boolean gardienDeNuit) {
        super(id, nom, prenom, salaire, adresse);
        this.gardienDeNuit = gardienDeNuit;
    }

    public Gardien(String nom, String prenom, double salaire, Adresse adresse, boolean gardienDeNuit) {
        super(nom, prenom, salaire, adresse);
        this.gardienDeNuit = gardienDeNuit;
    }

    public Gardien() {
    }

    @Override
    public String toString() {
        return "Gardien{" +
                "gardienDeNuit=" + gardienDeNuit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Gardien gardien)) return false;
        return gardienDeNuit == gardien.gardienDeNuit;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(gardienDeNuit);
    }

    public boolean isGardienDeNuit() {
        return gardienDeNuit;
    }
    public void setGardienDeNuit(boolean gardienDeNuit) {
        this.gardienDeNuit = gardienDeNuit;
    }
}
