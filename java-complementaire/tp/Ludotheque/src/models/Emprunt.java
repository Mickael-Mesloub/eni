package models;

import java.time.LocalDate;

public class Emprunt {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Jeu jeu;
    private Membre membre;

    public Emprunt(LocalDate dateDebut, LocalDate dateFin, Jeu jeu, Membre emprunteur) {
        this.dateDebut =  (dateDebut != null) ? dateDebut : LocalDate.now();
        this.dateFin = (dateFin != null) ? dateFin : LocalDate.now();
        this.jeu = jeu;
        this.membre = emprunteur;

        emprunteur.ajouterEmprunt(this);
        jeu.ajouterEmprunt(this);
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
}
