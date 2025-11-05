package model;

import java.time.LocalTime;

public class Creneau {
    private final LocalTime time;
    private final int duree;
    private final MedecinGeneraliste medecin;

    public Creneau(LocalTime _time, int _duree, MedecinGeneraliste _medecin) {
        time = _time;
        duree = _duree;
        medecin = _medecin;

        this.medecin.ajouterCreneau(this);
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
       info.append(getTime()).append(" - ").append(getTime().plusMinutes(getDuree())).append(" (").append(duree).append(" minutes)");
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(getTime()).append(" - ").append(getTime().plusMinutes(getDuree())).append(" (").append(duree).append(" minutes)");

        return info.toString();
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public LocalTime getTime() {
        return time;
    }

    public int getDuree() {
        return duree;
    }

    public MedecinGeneraliste getMedecin() {
        return medecin;
    }
}
