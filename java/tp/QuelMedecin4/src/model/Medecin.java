package model;

import java.util.ArrayList;

public class Medecin extends Personne {
    private final ArrayList<Creneau> creneaux;

    public Medecin(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);
        creneaux = new ArrayList<Creneau>();
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(super.toString())
                .append(getAdresse().getAdresseInfo()).append("\n")
                .append("Créneaux : ").append("\n")
                .append(getCreneauDisplay());

        return info.toString();
    }

    public String getCreneauDisplay() {
        StringBuilder creneauSb = new StringBuilder();
        for(Creneau creneau : creneaux) {
            creneauSb.append(creneau.toString()).append("\n");
        }

        return creneauSb.toString();
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

