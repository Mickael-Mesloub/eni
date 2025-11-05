package service;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Methode {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static Methode instance;

    private Methode() {}

    public static Methode getMethode() {
        if (instance == null) {
            instance = new Methode();
        }
        return instance;
    }

    /**
     * Met en majuscule la première lettre de chaque mot ou sous-partie séparée par un caractère défini dans le pattern (ex: "[-' ]").
     * Exemple : premiereEnMajuscule("mac'douglas", "[-' ]") -> "Mac'Douglas"
     * @param input Le prénom à tester
     * @param pattern Le pattern du prénom à respecter (si c'est avec une apostrophe, trait d'union, espace...)
     * @return Le prénom formatté avec les majuscules aux premières lettres
     */
    public String premiereEnMajuscule(String input, String pattern) {
        if (input == null || input.isEmpty()) return input;

        // On découpe selon les séparateurs (mais on garde les séparateurs dans le résultat)
        String[] parties = input.toLowerCase().split("(?=" + pattern + ")|(?<=" + pattern + ")");

        // On traite chaque morceau
        return Arrays.stream(parties)
                .map(part -> part.matches(pattern) ? part : capitalize(part))
                .collect(Collectors.joining());
    }

    private static String capitalize(String s) {
        return s.isEmpty() ? s
                : s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Formate un numéro de téléphone français sur 10 chiffres : "0123456789" -> "01.23.45.67.89"
     * @param numero Le numéro à formatter
     * @return Le numéro formatté sous la forme 0X.XX.XX.XX.XX ou "<< numéro incorrect >>" si le numéro n'est pas valide
     */
    public String formatNumeroTelephone(String numero) {

        // Vérifie si c’est bien 10 chiffres commençant par 0
        if (!isNumTelValid(numero)) return "<< numero incorrect >>";

        String num = numero.replaceAll("\\D", "");

        // Regroupe par paires
        return num.replaceAll("(\\d{2})(?=\\d)", "$1.").substring(0, 14);
    }

    /**
     * Vérifie si le numéro est au bon format ("01.23.45.67.89")
     * @param numero Le numéro à tester
     * @return true si le format est bon, sinon false
     */
    public boolean isNumTelValid(String numero) {
        if (numero == null) return false;

        // Supprime les espaces et autres séparateurs
        String num = numero.replaceAll("\\D", "");

        return (num.matches("0\\d{9}"));
    }

    /**
     * Retourne un texte colorisé en rouge pour l'affichage en console
     * @param str Le texte à coloriser
     * @return Le texte colorisé
     */
    public String colorizeString(String str) {
        return ANSI_RED + str + ANSI_RESET;
    }
}
