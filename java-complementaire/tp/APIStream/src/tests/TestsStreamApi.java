package tests;

import models.Chanson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TestsStreamApi {

	public static void main(String[] args) {
		List<Chanson> chansons = new ArrayList<Chanson>(List.of(new Chanson("Space Oddity", "David Bowie", 5.17),
				new Chanson("Paint It, Black", "The Rolling Stones", 3.22),
				new Chanson("Smoke On The Water", "Deep Purple", 5.40),
                new Chanson("Cocaine", "Eric Clapton", 3.36),
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
        System.out.println("==========================================================================================");
		System.out.println("Afficher les chansons de M, Brigitte et Sanseverino");

        /*chansons.stream()
                .filter(c ->
                        c.getChanteur().equals("M")
                                || c.getChanteur().equals("Brigitte")
                                || c.getChanteur().equals("Sanseverino"))
                .toList()
                .forEach((c) -> System.out.println(c.getTitre() + " - " + c.getChanteur()));*/

        /*chansons.stream().filter(c ->
                c.getChanteur().matches("M|Brigitte|Sanseverino"))
                .toList()
                .forEach((c) -> System.out.println(c.getTitre() + " - " + c.getChanteur()));*/

        List<String> listeDeChanteursCherches = List.of("M", "Brigitte", "Sanseverino");
        chansons.stream()
                .filter(c -> listeDeChanteursCherches.contains(c.getChanteur()))
                .forEach((c) -> System.out.println(c.getTitre() + " - " + c.getChanteur()));

        System.out.println("==========================================================================================");
        System.out.println("Est-ce que toutes les chansons durent minimum 2 minutes?");

        boolean allSongsLastAtLeastTwoMins = chansons.stream().allMatch(c -> c.getDuree() >= 2);
        Chanson shortestSong = chansons.stream()
                .min(Comparator.comparing(Chanson::getDuree)).get();

        StringBuilder twoMinsSongsSb = new StringBuilder();

        twoMinsSongsSb.append((allSongsLastAtLeastTwoMins) ? "Oui. " : "Non. ").append("la chanson la plus courte dure ")
                .append(String.format("%.2f",shortestSong.getDuree()))
                .append(" (")
                .append(shortestSong.getTitre())
                .append(").");

        System.out.println(twoMinsSongsSb);

        System.out.println("==========================================================================================");
        System.out.println("Est-ce que toutes les chansons durent minimum 3 minutes?");

        boolean allSongsLastAtLeastThreeMins = chansons.stream().allMatch(c -> c.getDuree() >= 3);

        StringBuilder threeMinsSongsSb = new StringBuilder();

        threeMinsSongsSb.append((allSongsLastAtLeastThreeMins) ? "Oui. " : "Non. ").append("la chanson la plus courte dure ")
                .append(String.format("%.2f",shortestSong.getDuree()))
                .append(" (")
                .append(shortestSong.getTitre())
                .append(").");

        System.out.println(threeMinsSongsSb);

        System.out.println("==========================================================================================");
        System.out.println("Est-ce qu'au moins une chanson dure plus de 6 minutes?");
        List<Chanson> songsExceedingSixMinutes = chansons.stream().filter(c -> c.getDuree() > 6).toList();
        boolean atLeastOneSongExceedSixMins = !songsExceedingSixMinutes.isEmpty();

        StringBuilder sixMinsSongsSb = new StringBuilder();

        sixMinsSongsSb.append((atLeastOneSongExceedSixMins) ? "Oui.\n" : "Non.\n");

        songsExceedingSixMinutes.stream().forEach(
                c ->  sixMinsSongsSb.append(c.getTitre())
                        .append(" - ")
                        .append(c.getDuree()));

        System.out.println(sixMinsSongsSb);

        System.out.println("==========================================================================================");
        System.out.println("Quelle est la durée moyenne d'une chanson?");


        double averageDuration = chansons.stream()
                .mapToDouble(Chanson::getDuree)
                .average().orElse(0);

        System.out.println("La durée moyenne d'une chanson est de " + String.format("%.2f", averageDuration));

        System.out.println("==========================================================================================");
        System.out.println("Quelle est la chanson la plus courte?");
        System.out.println("La chanson la plus courte est " + shortestSong.getTitre() + " (" + String.format("%.2f", shortestSong.getDuree()) + ").");
	}
}
