import java.util.ArrayList;
import java.util.List;

public class QuelMedecinOneApp {
    public static void main(String[] args) {
        // Instances de médecins
        GeneralPractitioner gp1 = new GeneralPractitioner("Malaniche", "Mélanie", "02.28.03.17.28");
        GeneralPractitioner gp2 = new GeneralPractitioner("Bosapin", "Edmond", "+33 2 17 18 19 20");
        GeneralPractitioner gp3 = new GeneralPractitioner("Table", "Djémila", "03-04-05-06-07");

        // Liste de médecins
        ArrayList<GeneralPractitioner> gps = new ArrayList<GeneralPractitioner>(List.of(gp1,gp2,gp3));

        // Boucle sur liste de médecins pour afficher les infos des médecins
        for(GeneralPractitioner gp : gps) {
            gp.showInfo();
        }

    }
}