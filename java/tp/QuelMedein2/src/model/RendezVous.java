package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RendezVous {
    private final Creneau creneau;
    private final Patient patient;
    private final LocalDate date;

    public RendezVous(Creneau _creneau, Patient _patient, LocalDate _date) {
        creneau = _creneau;
        patient = _patient;
        date = _date;
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
        StringBuilder newInfo = new StringBuilder();

        info.append("Rendez-vous du ").append(getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).append(" ");
        System.out.printf(info.toString());
        creneau.afficher();

        newInfo.append("avec le Dr ").append(getCreneau().getMedecin().getNom()).append("\n")
        .append("pour ");
        System.out.printf(newInfo.toString());
        patient.afficher();
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\


    public Creneau getCreneau() {
        return creneau;
    }

    public LocalDate getDate() {
        return date;
    }
}
