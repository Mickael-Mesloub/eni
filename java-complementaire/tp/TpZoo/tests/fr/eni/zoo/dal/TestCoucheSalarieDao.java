
package fr.eni.zoo.dal;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.eni.zoo.bo.*;


/**
 * Teste les fonctionnalités de la couche DAO pour les employés du zoo (SalarieDaoMock).
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCoucheSalarieDao {
    // Instance du DAO simulé pour les salariés
    private SalarieDaoMock dao = new SalarieDaoMock();
	

	@Test
	@Order(1)
	public void testajoutSalarie() {
		System.out.println("\n-------------- test ajout salarie ---------------");
		// Arrange
		// création adresse
		Adresse address = new Adresse(1, 9, "chemin des bois", "Nantes", "44000");
		
		// création de gardien
		Gardien gardien = new Gardien("Dubois", "Sophie", 1600, address, true);
		
		int size = this.dao.getSalaries().size();
		// Act
		this.dao.ajoutSalarie(gardien);

		// Assert
		System.out.println("il y a une différence de taille de la liste de " + (this.dao.getSalaries().size() - size));
	}

	@Test
	@Order(2)
	public void testGetSalarie() {
		System.out.println("\n-------------- test getSalarie ---------------");
		// Arrange
		Adresse address = new Adresse(2, 2, "rue de la forêt", "Rennes", "35000");

		Gardien gardien = new Gardien("Leclerc", "Jacques", 1700, address, false);

		this.dao.ajoutSalarie(gardien);

		// Act
		Salarie salarie = this.dao.getSalarie(gardien.getId());

		// Assert
		assertEquals(salarie.getNom(), "Leclerc");
		assertEquals(salarie.getPrenom(), "Jacques");
		assertEquals(salarie.getSalaire(), 1700);
		assertEquals(salarie.getAdresse(), address);
		System.out.println("détail du salarié Leclerc " + salarie);
	}

	@Test
	@Order(3)
	public void testUpdateSalaries() {
		System.out.println("\n-------------- test mise à jour salarie ---------------");
		// Arrange
        //pour tester la mise à jour dans la liste il est necessaire d'utiliser 2 objets
        // si un seul objet était utilisé alors ça mise à jour dans le test le mettrais aussi à jour dans la liste.
		// création de soignant
		Adresse address = new Adresse(3, 3, "rue Faraday", "Saint Herblain", "44800");
		Soignant soignant = new Soignant("Dupont", "Daniel", 1800, address, TypeAnimal.LION);
		Soignant soignantUpdate = new Soignant("Martin", "Daniel", 1800, address, TypeAnimal.LION);
		
		// ajout d'un salarie dans le manager
		this.dao.ajoutSalarie(soignant);
		System.out.println("détail du soignant " + soignant);

		soignantUpdate.setId(soignant.getId());

		// Act
		// mis à jour du salarie
		this.dao.majSalarie(soignantUpdate);

		// Assert
		// récupération du nouveau salarie
		Salarie salarie = this.dao.getSalarie(soignant.getId());
		assertEquals("Martin", salarie.getNom());
		System.out.println("changement du nom du soignant " + salarie);
	}
	
	@Test
	@Order(4)
	public void testDeleteSalarie() {
	    System.out.println("\n-------------- test suppression salarie ---------------");
	    // Arrange
	    Adresse address = new Adresse(4, 4, "avenue de la République", "Paris", "75000");
	    Gardien gardien = new Gardien("Blanc", "Luc", 1900, address, false);
	    this.dao.ajoutSalarie(gardien);
	    int size = this.dao.getSalaries().size();
	    int salarieId = gardien.getId();
	    System.out.println("Salarié ajouté pour suppression : " + gardien);

	    // Act
	    this.dao.supprimerSalarie(salarieId);

	    // Assert
	    assertEquals(size - 1, this.dao.getSalaries().size());
	    assertNull(this.dao.getSalarie(gardien.getId()));
	    System.out.println("L'animal avec l'ID " + gardien.getId() + " a été supprimé.");
	}
}

