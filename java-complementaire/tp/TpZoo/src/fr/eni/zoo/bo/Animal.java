package fr.eni.zoo.bo;

import enums.TypeAnimal;

import java.util.Objects;

public class Animal {
    private int id;
    private String nom;
    private boolean sexe;
    private int age;
    private TypeAnimal type;

    public Animal(int id, String nom, boolean sexe, int age, TypeAnimal type) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.type = type;
    }

    public Animal(String nom, boolean sexe, int age, TypeAnimal type) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.type = type;
    }

    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", sexe=" + sexe +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Animal animal)) return false;
        return id == animal.id && sexe == animal.sexe && age == animal.age && Objects.equals(nom, animal.nom) && type == animal.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, sexe, age, type);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getSexe() {
        return this.sexe;
    }
    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public TypeAnimal getType() {
        return type;
    }
    public void setType(TypeAnimal type) {
        this.type = type;
    }
}
