package test;

import java.time.LocalDate;

public class TestTroisiemePartie {
    public static void main(String[] args) {
        // Création de la ludothèque
    	Ludotheque ludotheque = new Ludotheque();

        // Création de membres
        Membre membre1 = new Membre(1, "Dupont", "Jean", "0123456789", LocalDate.of(2023, 1, 10));
        Membre membre2 = new Membre(2, "Martin", "Claire", "0987654321", LocalDate.of(2022, 6, 15));

        // Inscription des membres
        ludotheque.inscrireMembre(membre1);
        ludotheque.inscrireMembre(membre2);

        // Création de types de jouets
        TypeJouet peluche = new TypeJouet(1, "Peluche");
        TypeJouet puzzle = new TypeJouet(2, "Puzzle");

        // Création de jeux
        JeuSociete catan = new JeuSociete(3, "Les Colons de Catane" , Etat.MAUVAIS, 10, "Les Colons de Catane est un jeu de stratégie où les joueurs colonisent une île, construisent des routes et des colonies, et échangent des ressources pour devenir la puissance dominante.", 2.5, 4.0);
        JeuSociete terraformingMars = new JeuSociete(4, "Terraforming Mars", Etat.CORRECT, 12, "Terraforming Mars est un jeu de stratégie où les joueurs dirigent des corporations pour rendre Mars habitable en gérant les ressources et les technologies.", 4.5, 4.5);

		// Création de jouets
		Jouet teddyPeluche = new Jouet(3, "Teddy bear", Etat.MAUVAIS, 3, "Ourson en peluche", peluche);
		Jouet parisPuzzle = new Jouet(4, "puzzle paris", Etat.BON, 5, "puzzle 100 piece de Paris", puzzle);

        // Ajout des jeux à la ludothèque
        ludotheque.ajouterJeu(catan);
        ludotheque.ajouterJeu(terraformingMars);
        ludotheque.ajouterJeu(teddyPeluche);
        ludotheque.ajouterJeu(parisPuzzle);

        // Emprunt de jeux par les membres
        ludotheque.emprunterJeu(membre1, catan, LocalDate.of(2023, 5, 1));
        ludotheque.emprunterJeu(membre2, teddyPeluche, LocalDate.of(2023, 5, 1));

        // Affichage des emprunts d'un membre
        ludotheque.afficherEmpruntsMembre(membre1);
        ludotheque.afficherEmpruntsMembre(membre2);

        // Tentative d'emprunt d'un jeu déjà emprunté
        ludotheque.emprunterJeu(membre1, catan, LocalDate.of(2023, 5, 16));

        // Retour d'un jeu emprunté
        ludotheque.retournerJeu(membre1, catan);

        // Emprunt d'un jeu retourné
        ludotheque.emprunterJeu(membre1, catan, LocalDate.of(2023, 5, 16));

        // Affichage des jeux en retard (si la date actuelle est après la date de fin de l'emprunt)
        ludotheque.afficherJeuxEnRetard();
    }
}

