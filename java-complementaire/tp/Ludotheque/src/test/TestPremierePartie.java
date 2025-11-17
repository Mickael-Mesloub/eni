/*
package test;

import models.*;
import models.enums.Etat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPremierePartie {

	public static void main(String[] args) {
		// Création d'un membre pour 12 mois à la date du jour
        Membre john = new Membre(1, "Doe", "John", "0666666666", LocalDate.now());
        // Création d'un membre pour 36 mois au 25/12/2023
        Membre jane = new Membre(2, "Doe", "Jane", "0777777777", LocalDate.of(2023, 12, 25));

        System.out.println("--------------- Membre -------------------");
        System.out.println("ID: " + john.getId() + ", Nom: " + john.getNom() + ", Prénom: " + john.getPrenom() + ", Téléphone: " + john.getTelephone() + ", Date d'inscription: " + john.getDateAdhesion());
        System.out.println("ID: " + jane.getId() + ", Nom: " + jane.getNom() + ", Prénom: " + jane.getPrenom() + ", Téléphone: " + jane.getTelephone() + ", Date d'inscription: " + jane.getDateAdhesion());

        // Création des types de jouets
        TypeJouet educatif = new TypeJouet(1, "Jouets éducatifs");
        TypeJouet puzzle = new TypeJouet(2, "Puzzle");

        System.out.println("--------------- TypeJouet -------------------");
        System.out.println("ID: " + educatif.getId() + ", Nom: " + educatif.getLibelle());
        System.out.println("ID: " + puzzle.getId() + ", Nom: " + puzzle.getLibelle());

        // Création des jouets
        Jouet scrabble = new Jouet(1, "scrabble", Etat.BON, 7, "Un Scrabble plus simple pour les enfants");
        Jouet jouetPuzzle = new Jouet(2, "Carte du monde", Etat.CORRECT, 3, "Apprendre à reconnaitre les pays du monde en s'amusant");

        // Création des jeux de société
        JeuSociete catan = new JeuSociete(3, "Les Colons de Catane" , Etat.MAUVAIS, 10, "Les Colons de Catane est un jeu de stratégie où les joueurs colonisent une île, construisent des routes et des colonies, et échangent des ressources pour devenir la puissance dominante.", 2.5, 4.0);
        JeuSociete terraformingMars = new JeuSociete(4, "Terraforming Mars", Etat.CORRECT, 12, "Terraforming Mars est un jeu de stratégie où les joueurs dirigent des corporations pour rendre Mars habitable en gérant les ressources et les technologies.", 4.5, 4.5);

        // Création d'une liste de jeux
        List<Jeu> listJeux = new ArrayList<>();

        // Affichage des informations des jeux
        listJeux.add(scrabble);
        listJeux.add(jouetPuzzle);
        listJeux.add(catan);
        listJeux.add(terraformingMars);

        System.out.println("--------------- Jeu -------------------");

        // Affichage des jeux
        listJeux.forEach(j -> System.out.println("ID : " + j.getTitre() + ", Titre : " + j.getTitre() + ", Etat: " + j.getEtat() + ", à partir de " + j.getAge() + " ans"));

        // Création d'un emprunt
        Emprunt emprunt = new Emprunt(LocalDate.now(), LocalDate.of(2023, 8, 21));

        // Affichage des informations de l'emprunt
        System.out.println("--------------- Emprunt -------------------");
        System.out.println("Date d'emprunt: " + emprunt.getDateDebut() + ", Date de retour: " + emprunt.getDateFin());
    }

}
*/
