package fr.eni.zoo.bo;

import static org.junit.jupiter.api.Assertions.*;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Classe de test pour la classe Animal dans le package fr.eni.zoo.bo.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAnimal {

    /**
     * Teste le constructeur d'un Animal sans ID.
     */
    @Test
    @Order(1)
    void testConstructeurSansId() {
        System.out.println("\n-------------- Test Animal constructeur sans ID ---------------");
        
        // Arrange et Act
        // Test du constructeur sans ID
        TypeAnimal type = TypeAnimal.LION;
        Animal animal = new Animal("Leo", false, 5, type);
        
        // Affiche les détails de l'animal pour vérification
        System.out.println("Animal : " + animal);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals("Leo", animal.getNom());
        assertEquals(false, animal.getSexe());
        assertEquals(type, animal.getType());
        assertEquals(5, animal.getAge());
    }

    /**
     * Teste le constructeur d'un Animal avec ID.
     */
    @Test
    @Order(2)
    void testConstructeurAvecId() {
        System.out.println("\n-------------- Test Animal constructeur avec ID ---------------");
        
        // Arrange et Act
        // Test du constructeur avec ID
        TypeAnimal type = TypeAnimal.OURS;
        Animal animal = new Animal(1, "Leo", false, 5, type);

        // Affiche les détails de l'animal pour vérification
        System.out.println("Animal : " + animal);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals(1, animal.getId());
        assertEquals("Leo", animal.getNom());
        assertEquals(false, animal.getSexe());
        assertEquals(type, animal.getType());
        assertEquals(5, animal.getAge());
    }

    /**
     * Teste les méthodes setters de l'Animal.
     */
    @Test
    @Order(3)
    void testSetters() {
        System.out.println("\n-------------- Test Animal setters ---------------");
        
        // Arrange et Act
        // Test des setters
        TypeAnimal type = TypeAnimal.OURS;
        Animal animal = new Animal();
        
        // Modifier les attributs de l'animal
        animal.setId(1);
        animal.setNom("Polly");
        animal.setSexe(true);
        animal.setType(type);
        animal.setAge(3);

        // Affiche les détails de l'animal pour vérification
        System.out.println("Animal : " + animal);

        // Assert
        // Vérification que les setters fonctionnent correctement
        assertEquals(1, animal.getId());
        assertEquals("Polly", animal.getNom());
        assertEquals(true, animal.getSexe());
        assertEquals(type, animal.getType());
        assertEquals(3, animal.getAge());
    }
}
