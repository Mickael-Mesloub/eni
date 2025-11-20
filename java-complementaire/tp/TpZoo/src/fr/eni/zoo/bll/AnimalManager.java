package fr.eni.zoo.bll;

import fr.eni.zoo.bo.Animal;
import fr.eni.zoo.dal.AnimalDaoMock;

import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalManager extends AnimalDaoMock {
    public AnimalManager() {
    }

    public double getMoyenneAge() {
        return getAnimaux().stream().mapToDouble(a -> (double)a.getAge()).average().getAsDouble();
    }

    public double getProportionFemelle() {
        int nbFemelles = 0;

        for(Animal a : getAnimaux()) {
            if(!a.getSexe()) nbFemelles++;
        }

        return (double) nbFemelles / getAnimaux().size() * 100;
    }
}
