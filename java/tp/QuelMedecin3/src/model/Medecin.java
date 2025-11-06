package model;

import service.Methode;

import java.util.ArrayList;

public class Medecin extends Personne {
    private final ArrayList<Creneau> creneaux;
    private final int MAX_CRENEAU_NB = 15;

    Methode service = Methode.getMethode();

    public Medecin(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);
        creneaux = new ArrayList<Creneau>();
    }

    public void ajouterCreneau(Creneau _creneau) {

        if(canAjouterCreneau()) {
            creneaux.add(_creneau);
        } else {
            System.out.println(service.colorizeString("Ce médecin ne peut plus accepter de nouveau créneau."));
        }
    }

    public boolean canAjouterCreneau() {
        return (creneaux.size() < MAX_CRENEAU_NB);
    }

    public void showCreneaux() {
        System.out.println("Créneaux :");
        for (Creneau creneau : creneaux) {
            creneau.afficher();
        }
    }
}

