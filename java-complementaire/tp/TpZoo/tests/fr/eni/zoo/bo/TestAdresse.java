package fr.eni.zoo.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Classe de test pour la classe Adresse dans le package fr.eni.zoo.bo.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAdresse {

    /**
     * Teste le constructeur d'une Adresse sans ID.
     */
    @Test
    @Order(1)
    void testConstructeurSansId() {
        System.out.println("\n-------------- Test Adresse constructeur sans ID ---------------");
        
        // Arrange et Act
        // Création d'une adresse avec des valeurs réalistes et différentes.
        Adresse adresse = new Adresse(15, "Avenue des Champs-Élysées", "Paris", "75008");

        // Affiche l'adresse pour vérification
        System.out.println("Adresse : " + adresse);
        
        // Assert
        // Vérification que les valeurs sont correctement attribuées.
        assertEquals(15, adresse.getNumero());
        assertEquals("Avenue des Champs-Élysées", adresse.getRue());
        assertEquals("Paris", adresse.getVille());
        assertEquals("75008", adresse.getCodePostal());
    }

    /**
     * Teste le constructeur d'une Adresse avec ID.
     */
    @Test
    @Order(2)
    void testConstructeurAvecId() {
        System.out.println("\n-------------- Test Adresse constructeur avec ID ---------------");
        
        // Arrange et Act
        // Création d'une adresse avec un ID et des valeurs réalistes et différentes.
        Adresse adresse = new Adresse(1, 25, "Rue de Rivoli", "Paris", "75004");
        
        // Affiche l'adresse pour vérification
        System.out.println("Adresse : " + adresse);
        
        // Assert
        // Vérification que les valeurs sont correctement attribuées.
        assertEquals(1, adresse.getId());
        assertEquals(25, adresse.getNumero());
        assertEquals("Rue de Rivoli", adresse.getRue());
        assertEquals("Paris", adresse.getVille());
        assertEquals("75004", adresse.getCodePostal());
    }

    /**
     * Teste les méthodes setters de l'Adresse.
     */
    @Test
    @Order(3)
    void testSetters() {
        System.out.println("\n-------------- Test Adresse setters ---------------");
        
        // Arrange et Act
        // Création d'une adresse vide et modification de ses attributs avec des valeurs réalistes et différentes.
        Adresse adresse = new Adresse();
        adresse.setId(2);
        adresse.setNumero(30);
        adresse.setRue("Rue de Bretagne");
        adresse.setVille("Paris");
        adresse.setCodePostal("75003");
        
        // Affiche l'adresse pour vérification
        System.out.println("Adresse : " + adresse);

        // Assert
        // Vérification que les valeurs sont correctement modifiées.
        assertEquals(2, adresse.getId());
        assertEquals(30, adresse.getNumero());
        assertEquals("Rue de Bretagne", adresse.getRue());
        assertEquals("Paris", adresse.getVille());
        assertEquals("75003", adresse.getCodePostal());
    }
}
