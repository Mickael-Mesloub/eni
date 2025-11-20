package fr.eni.zoo.dal;

import fr.eni.zoo.bo.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalDaoMock {
    private static final List<Animal> animaux = new ArrayList<Animal>();
    private static int idIndex;

    public AnimalDaoMock() {
    }

    public void ajoutAnimal(Animal animal){
        idIndex++;
        animal.setId(idIndex);
        animaux.add(animal);
    }

    public void supprimerAnimal(int id){
        animaux.removeIf(animal -> animal.getId() == id);
    }

    public void majAnimal(Animal animal){
        animaux.set(getIdIndex() - 1, animal);
    }

    @Override
    public String toString() {
        return "AnimalDaoMock{" +
                "animaux=" + animaux +
                ", idIndex=" + idIndex +
                '}';
    }

    public List<Animal> getAnimaux() {
        return animaux;
    }

    public Animal getAnimal(int id){
        return animaux.stream().filter(animal -> animal.getId() == id).findAny().orElse(null);
    }

    public int getIdIndex() {
        return idIndex;
    }
}
