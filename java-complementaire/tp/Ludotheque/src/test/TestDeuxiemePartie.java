package test;

import models.*;
import models.enums.Etat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDeuxiemePartie {

	public static void main(String[] args) {
		// Création de membres
		Membre jean = new Membre(1, "Dupont", "Jean", "0123456789", LocalDate.of(2023, 1, 10));
		Membre martin = new Membre(2, "Martin", "Claire", "0987654321", LocalDate.of(2022, 6, 15));

		// Création de types de jouets
		TypeJouet peluche = new TypeJouet(1, "Peluche");
		TypeJouet puzzle = new TypeJouet(2, "Puzzle");

		// Création de jeux
		// Création des jeux de société
        JeuSociete catan = new JeuSociete(3, "Les Colons de Catane" , Etat.MAUVAIS, 10, "Les Colons de Catane est un jeu de stratégie où les joueurs colonisent une île, construisent des routes et des colonies, et échangent des ressources pour devenir la puissance dominante.", 2.5, 4.0);
        JeuSociete terraformingMars = new JeuSociete(4, "Terraforming Mars", Etat.CORRECT, 12, "Terraforming Mars est un jeu de stratégie où les joueurs dirigent des corporations pour rendre Mars habitable en gérant les ressources et les technologies.", 4.5, 4.5);

		// Création des jouets
		Jouet teddyPeluche = new Jouet(3, "Teddy bear", Etat.MAUVAIS, 3, "Ourson en peluche", peluche);
		Jouet parisPuzzle = new Jouet(4, "puzzle paris", Etat.BON, 5, "puzzle 100 piece de Paris", puzzle);

		// Création d'un emprunt
		new Emprunt(LocalDate.of(2024, 1, 21), LocalDate.of(2023, 2, 21), terraformingMars, jean);
		new Emprunt(LocalDate.of(2024, 2, 21), LocalDate.of(2023, 3, 21), catan, jean);
		new Emprunt(LocalDate.of(2024, 3, 10), LocalDate.of(2023, 4, 10), teddyPeluche, martin);
		new Emprunt(LocalDate.of(2024, 4, 10), LocalDate.of(2023, 5, 10), parisPuzzle, martin);

		// Création d'une liste de jeu
		List<Jeu> listJeux = new ArrayList<>();

		// Affichage des informations des jeux
		listJeux.add(terraformingMars);
		listJeux.add(catan);
		listJeux.add(teddyPeluche);
		listJeux.add(parisPuzzle);

		System.out.println("--------------- Membre -------------------");
		System.out.println("Nom: " + jean.getNom() + ", Prénom: " + jean.getPrenom()
				+ ", Téléphone: " + jean.getTelephone() + ", Date d'inscription: " + jean.getDateAdhesion());

		System.out.println("Emprunt de Jean");
		jean.getEmprunts().forEach(m -> System.out.println("de " + m.getDateDebut() + " à " +  m.getDateFin() + " : "  + m.getJeu().getTitre() ));
		
		System.out.println( "\nNom: " + martin.getNom() + ", Prénom: " + martin.getPrenom()
				+ ", Téléphone: " + martin.getTelephone() + ", Date d'inscription: " + martin.getDateAdhesion());

		System.out.println("Emprunt de Martin");
		martin.getEmprunts().forEach(m -> System.out.println("de " + m.getDateDebut() + " à " +  m.getDateFin() + " : "  + m.getJeu().getTitre() ));
		
		System.out.println("\n--------------- Jeu -------------------");

		// Affichage des jeux
		listJeux.forEach(j -> {
				System.out.println("Titre: " + j.getTitre() + ", Etat: " + j.getEtat() + ", à partir de " + j.getAge() + " ans");
				System.out.println("Emprunt du jeu " + j.getTitre());
				j.getEmprunts().forEach(e -> System.out.println("de " + e.getDateDebut() + " à " +  e.getDateFin() + " : "  + e.getMembre().getNom() + " " + e.getMembre().getPrenom() + "\n"));

		});
	}

}
