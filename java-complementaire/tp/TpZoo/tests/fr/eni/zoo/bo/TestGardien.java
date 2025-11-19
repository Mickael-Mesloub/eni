package fr.eni.zoo.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Classe de test pour la classe Gardien dans le package fr.eni.zoo.bo.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestGardien {

    /**
     * Teste le constructeur d'un Gardien sans ID.
     */
    @Test
    @Order(1)
    void testConstructeurSansId() {
        System.out.println("\n-------------- Test Gardien constructeur sans ID ---------------");
        
        // Arrange et Act
        // Création d'un gardien sans ID
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        Gardien gardien = new Gardien("Martin", "Pierre", 1800.0, adresse, true);

        // Affiche les détails du gardien pour vérification
        System.out.println("Gardien : " + gardien);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals("Martin", gardien.getNom());
        assertEquals("Pierre", gardien.getPrenom());
        assertEquals(1800.0, gardien.getSalaire());
        assertTrue(gardien.isGardienDeNuit());
        assertEquals(adresse, gardien.getAdresse());
    }

    /**
     * Teste le constructeur d'un Gardien avec ID.
     */
    @Test
    @Order(2)
    void testConstructeurAvecId() {
        System.out.println("\n-------------- Test Gardien constructeur avec ID ---------------");
        
        // Arrange et Act
        // Création d'un gardien avec ID
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        Gardien gardien = new Gardien(1, "Martin", "Pierre", 1800.0, adresse, true);

        // Affiche les détails du gardien pour vérification
        System.out.println("Gardien : " + gardien);

        // Assert
        // Vérification que les valeurs passées au constructeur sont attribuées correctement
        assertEquals(1, gardien.getId());
        assertEquals("Martin", gardien.getNom());
        assertEquals("Pierre", gardien.getPrenom());
        assertEquals(1800.0, gardien.getSalaire());
        assertTrue(gardien.isGardienDeNuit());
        assertEquals(adresse, gardien.getAdresse());
    }

    /**
     * Teste les méthodes setters du Gardien.
     */
    @Test
    @Order(3)
    void testSetters() {
        System.out.println("\n-------------- Test Gardien setters ---------------");
        
        // Arrange et Act
        // Création d'un gardien vide
        Adresse adresse = new Adresse(10, "Rue Principale", "Paris", "75001");
        Gardien gardien = new Gardien();

        // Modifier les attributs de Gardien
        gardien.setId(1);
        gardien.setNom("Martin");
        gardien.setPrenom("Pierre");
        gardien.setSalaire(2000.0);
        gardien.setGardienDeNuit(false);
        gardien.setAdresse(adresse);

        // Affiche les détails du gardien pour vérification
        System.out.println("Gardien : " + gardien);

        // Assert
        // Vérification que les setters fonctionnent correctement
        assertEquals(1, gardien.getId());
        assertEquals("Martin", gardien.getNom());
        assertEquals("Pierre", gardien.getPrenom());
        assertEquals(2000.0, gardien.getSalaire());
        assertFalse(gardien.isGardienDeNuit());
        assertEquals(adresse, gardien.getAdresse());
    }
}
