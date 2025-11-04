package patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientMain {
    public static void main(String[] args) {
        // Instances de patients
        Patient patient1 = new Patient("Vambuce", "Hillary", "0123456789", Gender.F, 287060244010154L, LocalDate.of(1987, 6, 2), "Allergie aux arachides");
        Patient patient2 = new Patient("Perret", "Inès", "0698745123", Gender.F, 245021067034521L, LocalDate.of(1945, 2, 10), "");
        Patient patient3 = new Patient("Pamamobe", "Adhémar", "0753428619", Gender.M, 19211219202014L, LocalDate.of(1992, 11, 21), "");

        // Liste de patients
        ArrayList<Patient> patients = new ArrayList<Patient>(List.of(patient1,patient2,patient3));

        System.out.println("__________________________ Patients ______________________________");

        // Boucle sur liste de patients pour afficher les infos des patients
        for(Patient patient : patients) {
            System.out.println(patient.showInfo());
            System.out.println("------------------------------------------------------------------");
        }
    }
}
