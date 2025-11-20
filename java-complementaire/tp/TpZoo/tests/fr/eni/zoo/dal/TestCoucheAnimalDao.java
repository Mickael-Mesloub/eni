package fr.eni.zoo.dal;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.eni.zoo.bo.Animal;

//utilisé pour lancer les tests dans l'ordre donné par les annotation @order
//et s'assuré que la sortie sur la console est identique à l'énoncé
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCoucheAnimalDao {
	AnimalDaoMock animalDao = new AnimalDaoMock();
	

	@Test
	@Order(1)
	public void testAjoutAnimal() {
		System.out.println("---------------------------- tests Animal Dao -----------------------------\n");
		System.out.println("-------------- test ajout animal ---------------");
		// Arrange
		Animal elephantFemelle = new Animal("dumbo", true, 14, TypeAnimal.ELEPHANT);
		int size = this.animalDao.getAnimaux().size();
		// Act
		this.animalDao.ajoutAnimal(elephantFemelle);

		// Assert
		assertEquals(size + 1, this.animalDao.getAnimaux().size());

		System.out
				.println("il y a une différence de taille dans liste de " + (this.animalDao.getAnimaux().size() - size));

	}

	@Test
	@Order(2)
	public void testGetAnimal() {
		System.out.println("\n-------------- test getAnimal---------------");
		Animal singeFemelle = new Animal("Coco", true, 6, TypeAnimal.SINGE);
		// Arrange
		this.animalDao.ajoutAnimal(singeFemelle);

		// Act
		Animal animal = this.animalDao.getAnimal(singeFemelle.getId());

		// Assert
		assertEquals(animal.getNom(), "Coco");
		assertEquals(animal.getSexe(), true);
		assertEquals(animal.getType(), TypeAnimal.SINGE);
		assertEquals(animal.getAge(), 6);

		System.out.println("détail du singe Coco : " + animal);

	}

	@Test
	@Order(3)
	public void testUpdateAnimaux() {
		System.out.println("\n-------------- test mise à jour animal ---------------");
		// Arrange
        //pour tester la mise à jour dans la liste il est necessaire d'utiliser 2 objets
        // si un seul objet était utilisé alors ça mise à jour dans le test le mettrais aussi à jour dans la liste.
		Animal girafeMale = new Animal("Gerald", false, 12, TypeAnimal.GIRAFE);
		Animal girafeMaleUpdate = new Animal("Gaston", false, 12, TypeAnimal.GIRAFE);

		// ajout d'un animal dans le manager
		this.animalDao.ajoutAnimal(girafeMale);
		girafeMaleUpdate.setId(girafeMale.getId());
		System.out.println("détail de la girafe : " + girafeMale);

		// Act
		// mis à jjour de l'animal
		this.animalDao.majAnimal(girafeMaleUpdate);

		// Assert
		// récupération du nouvel animal
		Animal animal = this.animalDao.getAnimal(girafeMale.getId());
		assertEquals("Gaston", animal.getNom());
		System.out.println("changement du nom de la girafe " + animal);
	}

	@Test
	@Order(4)
	public void testDeleteAnimalMock() {
	    System.out.println("\n-------------- test suppression animal Mock ---------------");
		Animal lionFemelle = new Animal("nala", true, 5, TypeAnimal.LION);
	    // Arrange
	    this.animalDao.ajoutAnimal(lionFemelle);
	    int size = this.animalDao.getAnimaux().size();

	    // Act
	    this.animalDao.supprimerAnimal(lionFemelle.getId());

	    // Assert
	    assertEquals(size - 1, this.animalDao.getAnimaux().size());
	    assertNull(this.animalDao.getAnimal(lionFemelle.getId()));
	    System.out.println("L'animal avec l'ID " + lionFemelle.getId() + " a été supprimé.");
	}
}
