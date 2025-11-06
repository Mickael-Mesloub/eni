package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FichierMedical {
    private final int poids;
    private final int taille;
    private final String tension;
    private final int oxygenation;
    private final int pulsation;
    private final String probleme;
    private final String prescription;
    private final LocalDate dateSoin;
    private final Patient patient;
    private final Medecin medecin;

    public FichierMedical(int _poids, int _taille, String _tension, int _oxygenation, int _pulsation, String _probleme, String _prescription, LocalDate _dateSoin, Patient _patient ,Medecin _medecin) {
        poids = _poids;
        taille = _taille;
        tension = _tension;
        oxygenation = _oxygenation;
        pulsation = _pulsation;
        probleme = _probleme;
        prescription = _prescription;
        dateSoin = _dateSoin;
        patient = _patient;
        medecin = _medecin;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Fichier médical de :").append("\n")
            .append(patient.toString())
                .append("Poids : ").append(poids).append(" kg, ").append("taille : ").append(taille).append(" cm").append("\n")
                .append("Tension : ").append(tension).append("cmHg").append(", saturation : ").append(oxygenation).append("%, pulsation : ").append(pulsation).append("\n")
                .append("Probleme : ").append(probleme).append("\n")
                .append("Prescription : ").append(prescription).append("\n")
                .append("Date de soin : ").append(dateSoin.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).append("\n")
                .append("Le medecin généraliste responsable : ").append(medecin.toString())
            ;

        return info.toString();
    }
}
