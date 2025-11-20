package fr.eni.zoo.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import enums.TypeAnimal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.eni.zoo.bo.Animal;

/**
 * Teste les fonctionnalités de la couche de gestion des animaux du zoo
 * (AnimalManager).
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCoucheAnimalManager {

	// Instance du gestionnaire des animaux
	private AnimalManager animalManager = new AnimalManager();

/**
	 * Teste l'ajout d'un animal dans le gestionnaire.
	 */

	@Test
	@Order(1)
	public void testAjoutAnimal() {
		System.out.println("---------------------------- Tests Animal Manager -----------------------------\n");
		System.out.println("-------------- Test ajout animal ---------------");

		// Arrange
		Animal lionMale = new Animal("Simba", false, 5, TypeAnimal.LION);

		int tailleInitiale = animalManager.getAnimaux().size();

		// Act
		animalManager.ajoutAnimal(lionMale);

		// Assert
		int nouvelleTaille = animalManager.getAnimaux().size();
		assertEquals(tailleInitiale + 1, nouvelleTaille);
		System.out.println("Différence de taille dans la liste : " + (nouvelleTaille - tailleInitiale));
	}

/**
	 * Teste la récupération d'un animal par son ID.
	 */

	@Test
	@Order(2)
	public void testGetAnimal() {
		System.out.println("\n-------------- Test getAnimal---------------");

		// Arrange
		Animal singeFemelle = new Animal("Coco", true, 6, TypeAnimal.SINGE);

		animalManager.ajoutAnimal(singeFemelle);

		// Act
		Animal animal = animalManager.getAnimal(singeFemelle.getId());

		// Assert
		assertEquals("Coco", animal.getNom());
		assertTrue(animal.getSexe());
		assertEquals(TypeAnimal.SINGE, animal.getType());
		assertEquals(6, animal.getAge());
		System.out.println("Détail du singe Coco : " + animal);
	}


/**
	 * Teste la mise à jour des informations d'un animal.
	 */

	@Test
	@Order(3)
	public void testUpdateAnimaux() {
		System.out.println("\n-------------- Test mise à jour animal ---------------");

		// Arrange
		// pour tester la mise à jour dans la liste il est necessaire d'utiliser 2 objets
		// si un seul objet était utilisé alors ça mise à jour dans le test le mettrais
		// aussi à jour dans la liste la différence ici est le nom on utilise l'id.
		Animal girafeMale = new Animal("Gerald", false, 12, TypeAnimal.GIRAFE);
		Animal girafeMaleUpdate = new Animal("Moufassa", false, 12, TypeAnimal.GIRAFE);

		// Ajoute un animal dans le gestionnaire
		animalManager.ajoutAnimal(girafeMale);

		System.out.println("Détail de la girafe : " + girafeMale);
		//c'est grâce à l'id que l'élément est modifier dans la liste
		girafeMaleUpdate.setId(girafeMale.getId());

		// Act
		// Met à jour les informations de l'animal
		animalManager.majAnimal(girafeMaleUpdate);

		// Assert
		// Récupère l'animal mis à jour et vérifie son nom
		Animal animal = animalManager.getAnimal(girafeMale.getId());
		assertEquals("Moufassa", animal.getNom());

		System.out.println("Changement du nom de la girafe : " + animal);
	}

	@Test
	@Order(4)

/**
	 * Teste la suppression d'un animal. Vérifie si un animal est correctement
	 * supprimé de la liste des animaux et si la taille de la liste diminue de 1.
	 *
	 */

	public void testDeleteAnimal() {
		System.out.println("\n-------------- test delete animal ---------------");
		// Arrange
		Animal elephantFemelle = new Animal("dumbo", true, 14, TypeAnimal.ELEPHANT);

		this.animalManager.ajoutAnimal(elephantFemelle);
		int size = this.animalManager.getAnimaux().size();

		// Act
		this.animalManager.supprimerAnimal(elephantFemelle.getId());

		// Assert
		assertEquals(size - 1, this.animalManager.getAnimaux().size());
		// Vérifier que l'animal n'existe plus
		Animal animal = this.animalManager.getAnimal(elephantFemelle.getId());
		assertNull(animal);
		System.out.println("L'animal a été supprimé avec succès");
	}


/**
	 * Teste le calcul de la moyenne d'âge des animaux.
	 */

	@Test
	@Order(5)
	public void testMoyenneAge() {
		System.out.println("\n-------------- Test moyenne d'âge ---------------");

		// Arrange
		Animal lionMale = new Animal("Simba", false, 5, TypeAnimal.LION);
		Animal singeFemelle = new Animal("Coco", true, 6, TypeAnimal.SINGE);
		Animal girafeMale = new Animal("Gerald", false, 12, TypeAnimal.GIRAFE);
		Animal elephantFemelle = new Animal("Dumbo", true, 14, TypeAnimal.ELEPHANT);

		// on vide la liste pour s'assurer du total
		List<Integer> list = this.animalManager.getAnimaux().stream().map(Animal::getId).collect(Collectors.toList());
		for (int id : list) {
			animalManager.supprimerAnimal(id);
		}

		animalManager.ajoutAnimal(lionMale);
		animalManager.ajoutAnimal(singeFemelle);
		animalManager.ajoutAnimal(girafeMale);
		animalManager.ajoutAnimal(elephantFemelle);

		// Act
		double moyenne = animalManager.getMoyenneAge();

		// Assert
		assertEquals(9.25, moyenne);
		System.out.println("La moyenne d'âge est de " + moyenne + " ans");
	}


/**
	 * Teste le calcul de la proportion de femelles parmi les animaux.
	 */

	@Test
	@Order(6)
	public void testProportionFemelle() {
		System.out.println("\n-------------- Test proportion femelle ---------------");

		// Arrange
		Animal lionMale = new Animal("Simba", false, 5, TypeAnimal.LION);
		Animal singeFemelle = new Animal("Coco", true, 6, TypeAnimal.SINGE);
		Animal girafeMale = new Animal("Gerald", false, 12, TypeAnimal.GIRAFE);
		Animal elephantFemelle = new Animal("Dumbo", true, 14, TypeAnimal.ELEPHANT);

		// on vide la liste pour s'assurer du total
		List<Integer> list = this.animalManager.getAnimaux().stream().map(Animal::getId).collect(Collectors.toList());
		for (int id : list) {
			animalManager.supprimerAnimal(id);
		}

		animalManager.ajoutAnimal(lionMale);
		animalManager.ajoutAnimal(singeFemelle);
		animalManager.ajoutAnimal(girafeMale);
		animalManager.ajoutAnimal(elephantFemelle);

		// Act
		double proportion = animalManager.getProportionFemelle();

		// Assert
		assertEquals(50.0, proportion);
		System.out.println("La proportion de femelles est de " + proportion + " %");
	}
}

