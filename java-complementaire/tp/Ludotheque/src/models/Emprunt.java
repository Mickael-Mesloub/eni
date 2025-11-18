package models;

import java.time.LocalDate;
import java.util.Objects;

public class Emprunt {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Jeu jeu;
    private Membre membre;

    public Emprunt(LocalDate dateDebut, LocalDate dateFin, Jeu jeu, Membre emprunteur) {
        this.dateDebut =  (dateDebut != null) ? dateDebut : LocalDate.now();
        this.dateFin = dateFin;
        this.jeu = jeu;
        this.membre = emprunteur;
    }

    public Emprunt() {
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", jeu=" + jeu +
                ", membre=" + membre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(dateDebut, emprunt.dateDebut) && Objects.equals(dateFin, emprunt.dateFin) && Objects.equals(jeu, emprunt.jeu) && Objects.equals(membre, emprunt.membre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateDebut, dateFin, jeu, membre);
    }
}
