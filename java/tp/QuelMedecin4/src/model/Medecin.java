package model;

import java.util.ArrayList;

public abstract class Medecin extends Personne implements Soigner {
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
        for (Creneau creneau : creneaux) {
            creneauSb.append(creneau.toString()).append("\n");
        }

        return creneauSb.toString();
    }

    public void ajouterCreneau(Creneau creneau) {
        creneaux.add(creneau);
    }

    public void orienter(){
        System.out.println("Le médecin oriente vers un spécialiste en cas de besoin.");
    };

    public abstract int compareTo(MedecinSpecialiste otherMedSpe);

    @Override
    public void examiner(){
        System.out.println("Le médecin procède à des examens de routine sur les patients (auscultation, prise de tensions...");
    };

    @Override
    public void diagnostiquer(){
        System.out.println("Le médecin évalue l'état de santé du patient pour découvrir d'éventuels problèmes.");
    };

    @Override
    public void traiter(){
        System.out.println("Le médecin met en place un traitement.");
    };

    @Override
    public void conseiller(){
        System.out.println("Le médecin dispense des conseils appropriés pour des habitudes saines (régime, hygiène, etc.)");
    };
}

