package fr.eni.zoo.bo;

import enums.TypeAnimal;

import java.util.Objects;

public class Soignant extends Salarie {
    private TypeAnimal specialite;

    public Soignant(int id, String nom, String prenom, double salaire, Adresse adresse, TypeAnimal specialite) {
        super(id, nom, prenom, salaire, adresse);
        this.specialite = specialite;
    }

    public Soignant(String nom, String prenom, double salaire, Adresse adresse, TypeAnimal specialite) {
        super(nom, prenom, salaire, adresse);
        this.specialite = specialite;
    }

    public Soignant() {
    }

    @Override
    public String toString() {
        return "Soignant{" +
                "specialite=" + specialite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Soignant soignant)) return false;
        return specialite == soignant.specialite;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(specialite);
    }

    public TypeAnimal getSpecialite() {
        return specialite;
    }
    public void setSpecialite(TypeAnimal specialite) {
        this.specialite = specialite;
    }
}
