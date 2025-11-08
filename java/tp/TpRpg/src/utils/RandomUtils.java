package utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils(){}

    /**
     * Génère un nombre aléatoire entre min et max
     * @param min Valeur minimale
     * @param max Valeur maximale
     * @return Un nombre aléatoire
     */
    public static int generateRandomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}
