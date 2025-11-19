package fr.eni.zoo.bo;

import static org.junit.jupiter.api.Assertions.*;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Classe de test pour les objets Soignant dans le package fr.eni.zoo.bo.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestSoignant {

    /**
     * Teste le constructeur d'un Soignant sans ID.
     */
    @Test
    @Order(1)
    void testConstructeurSansId() {
        System.out.println("\n-------------- Test Soignant constructeur sans ID ---------------");
        
        // Arrange et Act
        // Création d'un soignant sans ID
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        TypeAnimal specialite = TypeAnimal.LION;
        Soignant soignant = new Soignant("Dupont", "Marie", 2000.0, adresse, specialite);

        // Affiche les détails du soignant pour vérification
        System.out.println("Soignant : " + soignant);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals("Dupont", soignant.getNom());
        assertEquals("Marie", soignant.getPrenom());
        assertEquals(2000.0, soignant.getSalaire());
        assertEquals(specialite, soignant.getSpecialite());
        assertEquals(adresse, soignant.getAdresse());
    }

    /**
     * Teste le constructeur d'un Soignant avec ID.
     */
    @Test
    @Order(2)
    void testConstructeurAvecId() {
        System.out.println("\n-------------- Test Soignant constructeur avec ID ---------------");
        
        // Arrange et Act
        // Création d'un soignant avec ID
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        TypeAnimal specialite = TypeAnimal.OURS;
        Soignant soignant = new Soignant(1, "Dupont", "Marie", 2000.0, adresse, specialite);

        // Affiche les détails du soignant pour vérification
        System.out.println("Soignant : " + soignant);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals(1, soignant.getId());
        assertEquals("Dupont", soignant.getNom());
        assertEquals("Marie", soignant.getPrenom());
        assertEquals(2000.0, soignant.getSalaire());
        assertEquals(specialite, soignant.getSpecialite());
        assertEquals(adresse, soignant.getAdresse());
    }

    /**
     * Teste les méthodes setters du Soignant.
     */
    @Test
    @Order(3)
    void testSetters() {
        System.out.println("\n-------------- Test Soignant setters ---------------");
        
        // Arrange et Act
        // Création d'un soignant vide
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        TypeAnimal specialite = TypeAnimal.ELEPHANT;
        Soignant soignant = new Soignant();

        // Modifier les attributs de Soignant
        soignant.setId(1);
        soignant.setNom("Dupont");
        soignant.setPrenom("Marie");
        soignant.setSalaire(2500.0);
        soignant.setSpecialite(specialite);
        soignant.setAdresse(adresse);

        // Affiche les détails du soignant pour vérification
        System.out.println("Soignant : " + soignant);

        // Assert
        // Vérification que les setters fonctionnent correctement
        assertEquals(1, soignant.getId());
        assertEquals("Dupont", soignant.getNom());
        assertEquals("Marie", soignant.getPrenom());
        assertEquals(2500.0, soignant.getSalaire());
        assertEquals(specialite, soignant.getSpecialite());
        assertEquals(adresse, soignant.getAdresse());
    }
}
