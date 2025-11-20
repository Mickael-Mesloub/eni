package fr.eni.zoo.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.eni.zoo.bo.Adresse;
import fr.eni.zoo.bo.Salarie;
import fr.eni.zoo.bo.Gardien;
import fr.eni.zoo.bo.Soignant;

/**
 * Teste les fonctionnalités de la couche de gestion pour les employés du zoo (SalarieManager).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCoucheSalarieManager {
    // Instance du gestionnaire des employés
    private SalarieManager salarieManager = new SalarieManager();

    /**
     * Teste l'ajout d'un salarié dans le gestionnaire.
     */
    @Test
    @Order(1)
    public void testAjoutSalarie() {
        System.out.println("---------------------------- tests Salarie Manager -----------------------------\n");
        System.out.println("-------------- test ajout salarie ---------------");
        // Arrange
    	Adresse address = new Adresse(9, "chemin des bois", "Nantes", "44000");
    	Gardien gardien = new Gardien("Dubois", "Sophie", 1600, address, true);
    	
        int tailleInitiale = salarieManager.getSalaries().size();

        // Act
        salarieManager.ajoutSalarie(gardien);

        // Assert
        assertEquals(tailleInitiale + 1, salarieManager.getSalaries().size());
        
        System.out.println("Différence de taille dans la liste : " 
                    + (salarieManager.getSalaries().size() - tailleInitiale));
    }

    /**
     * Teste la récupération d'un salarié par son ID.
     */
    @Test
    @Order(2)
    public void testGetSalarie() {
        System.out.println("\n-------------- test getSalarie ---------------");
        // Arrange
    	Adresse address = new Adresse(2, "rue de la forêt", "Rennes", "35000");

    	Gardien gardien = new Gardien("Leclerc", "Jacques", 1700, address, false);

        salarieManager.ajoutSalarie(gardien);
        
        // Act
        Salarie salarie = salarieManager.getSalarie(gardien.getId());

        // Assert
        assertEquals("Leclerc", salarie.getNom());
        assertEquals("Jacques", salarie.getPrenom());
        assertEquals(1700, salarie.getSalaire());
        assertEquals(address, salarie.getAdresse());
        System.out.println("Détail du salarié Leclerc : " + salarie);
    }

    /**
     * Teste la mise à jour des informations d'un salarié.
     */
    @Test
    @Order(3)
    public void testUpdateSalaries() {
        System.out.println("\n-------------- test mise à jour du salarie ---------------");
        // Arrange
        //pour tester la mise à jour dans la liste il est necessaire d'utiliser 2 objets
        // si un seul objet était utilisé alors ça mise à jour dans le test le mettrais aussi à jour dans la liste.
    	Adresse address = new Adresse(3, "rue Faraday", "Saint-Herblain", "44800"); 
    	Soignant soignant = new Soignant("Dupont", "Daniel", 1800, address, TypeAnimal.LION);
		Soignant soignantUpdate = new Soignant("Martin", "Daniel", 1800, address, TypeAnimal.LION);
        
        // Ajoute le soignant au gestionnaire
        salarieManager.ajoutSalarie(soignant);
        
        System.out.println("Détail du soignant : " + soignant);
		//c'est grâce à l'id que l'élément est modifier dans la liste
		soignantUpdate.setId(soignant.getId());

        // Act
        // Met à jour les informations du salarié
        salarieManager.majSalarie(soignantUpdate);

        // Assert
        // Récupère le salarié mis à jour et vérifie son nom
        Salarie salarie = salarieManager.getSalarie(soignant.getId());
        assertEquals("Martin", salarie.getNom());
        System.out.println("Changement du nom du soignant : " + salarie);
    }

	@Test
    @Order(4)
    public void testDeleteSalarie() {
        System.out.println("\n-------------- test delete salarie ---------------");
        // Arrange
		Adresse address = new Adresse(3, "2b rue Faraday", "Saint Herblain", "44800");

		Gardien gardien1 = new Gardien("Dubois", "Sophie", 1600, address, true);
		
        this.salarieManager.ajoutSalarie(gardien1);
        int size = this.salarieManager.getSalaries().size();

        // Act
        this.salarieManager.supprimerSalarie(gardien1.getId());

        // Assert: vérifier si la taille de la liste a diminué de 1
        assertEquals(size - 1, this.salarieManager.getSalaries().size());
        // Vérifier que le salarié n'existe plus
        Salarie salarie = this.salarieManager.getSalarie(gardien1.getId());
        assertNull(salarie);
        System.out.println("Le salarié a été supprimé avec succès");
    }
	
	@Test
	@Order(5)
	/**
	 * Teste le calcul de la masse salariale totale. Vérifie que la masse salariale
	 * calculée correspond à celle attendue.
	 *
	 */
	public void testMasseSalariale() {
		System.out.println("\n-------------- test masse salariale ---------------");
		// Arrange
		Gardien gardien1 = new Gardien("Dubois", "Sophie", 1600, null, true);
		Gardien gardien2 = new Gardien("Leclerc", "Jacques", 1700, null, false);

		Soignant soignant1 = new Soignant("Dupont", "Daniel", 1800, null, TypeAnimal.LION);
		Soignant soignant2 = new Soignant("Martin", "Julie", 1750, null, TypeAnimal.SINGE);
		
		//on vide la liste pour s'assurer du total
		List<Integer> list = this.salarieManager.getSalaries().stream().map(Salarie::getId).collect(Collectors.toList());
		for (int id : list) {
			salarieManager.supprimerSalarie(id);
	    }
		
		this.salarieManager.ajoutSalarie(gardien1);
		this.salarieManager.ajoutSalarie(gardien2);
		this.salarieManager.ajoutSalarie(soignant1);
		this.salarieManager.ajoutSalarie(soignant2);

		// Act: calculer la masse salariale totale
		double masse = this.salarieManager.getMasseSalariale();

		// Assert: vérifier que la masse salariale calculée est correcte
		assertEquals(6850.0, masse);
		System.out.println("La masse salariale totale est de " + masse + " euros");
	}

	@Test
	@Order(6)
	/**
	 * Teste le calcul de la moyenne salariale. Vérifie que la moyenne salariale
	 * calculée correspond à celle attendue.
	 *
	 */
	public void testMoyenneSalariale() {
		System.out.println("\n-------------- test moyenne salariale ---------------");
		// Arrange
		Gardien gardien1 = new Gardien("Dubois", "Sophie", 1600, null, true);
		Gardien gardien2 = new Gardien("Leclerc", "Jacques", 1700, null, false);

		Soignant soignant1 = new Soignant("Dupont", "Daniel", 1800, null, TypeAnimal.LION);
		Soignant soignant2 = new Soignant("Martin", "Julie", 1750, null, TypeAnimal.SINGE);
		
		//on vide la liste pour s'assurer de la moyenne
		List<Integer> list = this.salarieManager.getSalaries().stream().map(Salarie::getId).collect(Collectors.toList());
		for (int id : list) {
			salarieManager.supprimerSalarie(id);
	    }
		
		this.salarieManager.ajoutSalarie(gardien1);
		this.salarieManager.ajoutSalarie(gardien2);
		this.salarieManager.ajoutSalarie(soignant1);
		this.salarieManager.ajoutSalarie(soignant2);

		// Act
		double moyenne = this.salarieManager.getMoyenneSalariale();

		// Assert
		assertEquals(1712.5, moyenne);
		System.out.println("La moyenne des salaires est de " + moyenne + " euros");
	}
}
