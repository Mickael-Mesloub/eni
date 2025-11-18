package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ludotheque {
    private ArrayList<Jeu> jeux = new ArrayList<Jeu>();
    private ArrayList<Membre> membres = new ArrayList<Membre>();

    public Ludotheque(ArrayList<Jeu> jeux, ArrayList<Membre> membres) {
        this.jeux = jeux;
        this.membres = membres;
    }

    public Ludotheque() {
    }

    public ArrayList<Jeu> getJeux() {
        return jeux;
    }
    public void setJeux(ArrayList<Jeu> jeux) {
        this.jeux = jeux;
    }

    public ArrayList<Membre> getMembres() {
        return membres;
    }
    public void setMembres(ArrayList<Membre> membres) {
        this.membres = membres;
    }

    public void inscrireMembre(Membre membre) {
        membres.add(membre);
    }

    public void ajouterJeu(Jeu jeu) {
        jeux.add(jeu);
    }

    public void emprunterJeu(Membre membre, Jeu jeu, LocalDate dateDebut) {
        // Vérifier si le jeu est disponible (pas encore emprunté)
        Emprunt nouvelEmprunt = new Emprunt(dateDebut, null, jeu, membre);

        for(Emprunt e : jeu.getEmprunts()) {
            System.out.println(e.toString());
        }

        if(jeu.getEmprunts().stream().anyMatch(emprunt -> emprunt.equals(nouvelEmprunt))) {
            System.out.println("Ce jeu est déjà emprunté : " + jeu.getTitre() );
            /* jeu.ajouterEmprunt(e);*/
        }
        // Si le jeu est dispo, un nouvel emprunt est créé.
        // Lorsqu'un emprunt est créé, si la date de début n'est pas donnée, alors dateDebut = date du jour

    }

    public void retournerJeu(Membre membre, Jeu jeu)  {
        // Trouver l'emprunt correspondant au membre et au jeu renseignés
        // Mettre à jour la date de fin de l'emprunt
    }

    public void afficherEmpruntsMembre(Membre membre) {
        // Affiche tous les emprunts effectués par un membre
    }

    public void afficherJeuxEnRetard() {
        // Affiche tous les jeux en retard
        // C'est-à-dire, les jeux qui n'ont pas été retournés après un délai d'1mois après la date d'emprunt
    }
}
