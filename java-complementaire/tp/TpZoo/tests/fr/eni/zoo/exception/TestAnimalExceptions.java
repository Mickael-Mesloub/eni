package fr.eni.zoo.exception;

import enums.TypeAnimal;
import fr.eni.zoo.bo.Animal;
import fr.eni.zoo.dal.AnimalDaoMock;
import fr.eni.zoo.dal.exception.AnimalDaoException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAnimalExceptions {
    AnimalDaoMock animalDao = new AnimalDaoMock();

    @Test
    @Order(1)
    public void testAjoutAnimal() throws AnimalDaoException {
        System.out.println("---------------------------- tests Animal Dao -----------------------------\n");
        System.out.println("-------------- test ajout animal ---------------");
        // Arrange
        Animal elephantFemelle = new Animal("Maman de Dumbo", true, 14, TypeAnimal.ELEPHANT);
        Animal elephantMale = new Animal("Dumbo", false, 1, TypeAnimal.ELEPHANT);
        int size = this.animalDao.getAnimaux().size();
        // Act
        this.animalDao.ajoutAnimal(elephantFemelle);
        this.animalDao.ajoutAnimal(elephantMale);

        // Assert
        assertEquals(size + 1, this.animalDao.getAnimaux().size());

        System.out
                .println("il y a une différence de taille dans liste de " + (this.animalDao.getAnimaux().size() - size));

    }

}
