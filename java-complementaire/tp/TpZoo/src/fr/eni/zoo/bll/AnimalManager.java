package fr.eni.zoo.bll;

import fr.eni.zoo.bo.Animal;
import fr.eni.zoo.dal.AnimalDaoMock;

public class AnimalManager extends AnimalDaoMock {
    public AnimalManager() {
    }

    public double getMoyenneAge() {
        return getAnimaux().stream().mapToDouble(Animal::getAge).average().orElse(0);
    }

    public double getProportionFemelle() {
        return getAnimaux().stream()
                .filter(Animal::getSexe)
                .count() * 100.0 / getAnimaux().size();
    }
}
