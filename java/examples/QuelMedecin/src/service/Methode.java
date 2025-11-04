package service;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Methode {

    public Methode() {}

    /**
     * Méthode pour mettre en majuscule chaque premier mot d'une string
     * @param input La string à transformer
     * @return La string transformée
     */
    public static String premiereEnMajuscule(String input) {
        if (input == null || input.isEmpty()) return input;

        String[] parts = input.split("(?<=[ \\-’'])|(?=[ \\-’'])");

        return Arrays.stream(parts)
                .map(part -> part.matches("[a-zA-ZÀ-ÖØ-öø-ÿ]") ? part.toUpperCase() :
                        (!part.isEmpty() && Character.isLetter(part.charAt(0)))
                                ? Character.toUpperCase(part.charAt(0)) + part.substring(1).toLowerCase()
                                : part)
                .collect(Collectors.joining());
    }
}
