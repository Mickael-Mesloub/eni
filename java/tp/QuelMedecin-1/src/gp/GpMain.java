package gp;

import java.util.ArrayList;
import java.util.List;

public class GpMain {
    public static void main(String[] args) {
        // Instances de médecins
        GeneralPractitioner gp1 = new GeneralPractitioner("Malaniche", "Mélanie", "02.28.03.17.28");
        GeneralPractitioner gp2 = new GeneralPractitioner("Bosapin", "Edmond", "+33 2 17 18 19 20");
        GeneralPractitioner gp3 = new GeneralPractitioner("Table", "Djémila", "03-04-05-06-07");

        // Liste de médecins
        ArrayList<GeneralPractitioner> gps = new ArrayList<GeneralPractitioner>(List.of(gp1,gp2,gp3));

        System.out.println("__________________________ Médecins ______________________________");

        // Boucle sur liste de médecins pour afficher les infos des médecins
        for(GeneralPractitioner gp : gps) {
            System.out.println(gp.showInfo());
            System.out.println("------------------------------------------------------------------");
        }

        System.out.println("--------------- Changement du numéro de ce médecin ---------------\n");


        gp3.setPhoneNumber(" 07-06-05-04-03");

        System.out.printf("Nouveau numéro du Dr %s : ", gp3.getLastName());
        System.out.printf("%s", gp3.getPhoneNumber());

        System.out.println("\n-------------- Changement du prix de la consultation -------------\n");

        for(GeneralPractitioner gp : gps) {
            gp.setTariff(23);
            System.out.println(gp.showInfo());
            System.out.println("------------------------------------------------------------------");
        }



    }
}