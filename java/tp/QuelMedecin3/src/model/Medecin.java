package model;

import java.util.ArrayList;

public class Medecin extends Personne {
    private final ArrayList<Creneau> creneaux;

    public Medecin(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);
        creneaux = new ArrayList<Creneau>();
    }

    public void ajouterCreneau(Creneau creneau) {
        creneaux.add(creneau);
    }

    public void showCreneaux() {
        System.out.println("Créneaux :");
        for (Creneau creneau : creneaux) {
            creneau.afficher();
        }
    }
}

