import models.Chanson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestsExpressionLambda {

	public static void main(String[] args) {
		List<Chanson> chansons = new ArrayList<Chanson>(List.of( new Chanson("Space Oddity", "David Bowie", 5.17),
				 new Chanson("Paint It, Black", "The Rolling Stones", 3.22),
				 new Chanson("Smoke On The Water", "Deep Purple", 5.40),
				 new Chanson("Cocaine", "Eric Clapton", 3.22),
				 new Chanson("Englishman In New York", "Sting", 4.26),
				 new Chanson("Mojo", "M", 3.16),
				 new Chanson("Battez-vous", "Brigitte", 4.04),
				 new Chanson("Frida", "Sanseverino", 4.08),
				 new Chanson("Demons", "Imagine Dragons", 2.57),
				 new Chanson("Supersonic", "Oasis", 4.44),
				 new Chanson("Boulevard of Broken Dreams", "Green Day", 4.21),
				 new Chanson("Come Out and Play", "The Offspring", 3.17),
				 new Chanson("Under the Bridge", "Red Hot Chili Peppers", 4.25),
				 new Chanson("Come As You Are", "Nirvana", 3.38),
				 new Chanson("Nothing Else Matters", "Metallica", 6.28),
				 new Chanson("Sympathy For The Devil", "Motorhead", 5.26),
				 new Chanson("You Really Got Me", "Van Halen", 2.36),
				 new Chanson("Knockin' On Heaven's Door", "Guns N' Roses", 5.36)));
		
		//Enoncés
        System.out.println("====================================================================");
        System.out.println("Afficher la liste des chansons (titre + duree)");
        System.out.println("====================================================================");

        chansons.forEach(( c) -> System.out.println("Titre : " + c.getTitre() + " - Durée : " + c.getDuree() ));

        System.out.println("====================================================================");
        System.out.println("Trier la liste des chansons par ordre alphabétique");
        System.out.println("====================================================================");

        chansons.sort((c1, c2) -> c1.getTitre().compareTo(c2.getTitre()));
        // chansons.sort(Comparator.comparing(Chanson::getTitre)); -- Version avancée
        chansons.forEach((c) -> System.out.println(c.getTitre()));

        System.out.println("====================================================================");
        System.out.println("Afficher la liste des chansons (toString)");
        System.out.println("====================================================================");

        // chansons.forEach(c -> System.out.println(c.toString())); -- Déjà bien
        chansons.forEach(c -> System.out.println(c)); // Pas mal
        // chansons.forEach(System.out::println); -- Un peu avancé

        System.out.println("====================================================================");
		System.out.println("Supprimer les chansons trop longues (>5 minutes)");
        System.out.println("====================================================================");

        chansons.removeIf(c -> c.getDuree() > 5);
        chansons.forEach(( c) -> System.out.println("Titre : " + c.getTitre() + " - Durée : " + c.getDuree() ));
        /*
        List<Chanson> chansonsCourtes = chansons.stream().filter((chanson) -> chanson.getDuree() < 5).toList();
        chansonsCourtes.forEach(chanson -> System.out.println(chanson.getTitre() + " - Durée : " + chanson.getDuree()));*/

        System.out.println("====================================================================");
		System.out.println("Trier la liste des chansons par durée croissante");
        System.out.println("====================================================================");

        chansons.sort((c1, c2) -> {return Double.compare(c1.getDuree(), c2.getDuree());});
        /* chansons.sort(Comparator.comparingDouble(Chanson::getDuree));
        chansons.sort(Comparator.comparing(Chanson::getDuree));
        chansons.forEach(chanson -> System.out.println(chanson.getTitre() + " - Durée : " + chanson.getDuree()));*/
        chansons.forEach(( c) -> System.out.println("Titre : " + c.getTitre() + " - Durée : " + c.getDuree() ));

        System.out.println("========================================================================================");
        System.out.println("Trier la liste des chansons par durée croissante et par titre décroissant si durée égale");
        System.out.println("========================================================================================");

        chansons.sort((c1, c2) -> {
            int comparisonDuree = Double.compare(c1.getDuree(), c2.getDuree());
            if(comparisonDuree != 0) return comparisonDuree;
            else return c2.getTitre().compareTo(c1.getTitre());
        });
        chansons.forEach(( c) -> System.out.println("Titre : " + c.getTitre() + " - Durée : " + c.getDuree() ));
        System.out.println("====================================================================");
    }
}
