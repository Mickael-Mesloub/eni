package fr.eni.zoo.dal;

import fr.eni.zoo.bo.Animal;
import fr.eni.zoo.dal.exception.AnimalDaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalDaoMock {
    private static final List<Animal> animaux = new ArrayList<Animal>();
    private static int idIndex;

    public AnimalDaoMock() {
    }

    public void ajoutAnimal(Animal animal) throws AnimalDaoException {
        try {

            animal.setId(idIndex);
            animaux.add(animal);
            idIndex++;
        } catch (Exception ex) {
            throw new AnimalDaoException("L'ajout de l'animal a échoué.", ex);
        }
    }

    public void supprimerAnimal(int id) throws AnimalDaoException {
        try {
            animaux.removeIf(animal -> animal.getId() == id);
        } catch (Exception ex) {
            throw new AnimalDaoException("La suppression de l'animal a échoué.", ex);
        }
    }

    public void majAnimal(Animal animal) throws AnimalDaoException {
        try {
            Optional<Animal> animalCherche = animaux.stream()
                    .filter(a -> a.getId() == animal.getId())
                            .findFirst();

            if(animalCherche.isPresent()) {
                int index = animaux.indexOf(animalCherche.get());
                animaux.set(index, animal);
            } else {
                // TODO exception ?
            }
        } catch (Exception ex) {
            throw new AnimalDaoException("La mise à jour de l'animal a échoué.", ex);
        }
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
        return animaux.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);
    }

    public int getIdIndex() {
        return idIndex;
    }
}
