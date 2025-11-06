package model;

import service.Methode;

public class MedecinGeneraliste extends Medecin {
    private final int tarif;

    public MedecinGeneraliste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);

        tarif = 25;

        Methode ctrl = Methode.getMethode();

        // Affichage du message d'erreur si n° téléphone invalide
        if (!ctrl.isNumTelValid(_numeroDeTelephone)) {
            System.out.println(ctrl.colorizeString("Le numero de téléphone " + _numeroDeTelephone + " n'est pas conforme !"));
            setNumeroDeTelephone("<< numero incorrect >>");
        } else {
            // Si n° téléphone valide, on le formate (0X.XX.XX.XX.XX)
            setNumeroDeTelephone(ctrl.formatNumeroTelephone(_numeroDeTelephone));
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(super.toString())
                .append("Tarif : ").append(getTarif()).append("€").append("\n");

        return info.toString();
    }

    @Override
    public int compareTo(MedecinSpecialiste otherMedSpe) {
        return 0;
    }

    public void orienter(){
        System.out.println("Le médecin oriente vers un spécialiste en cas de besoin.");
    };

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

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public int getTarif() {
        return tarif;
    }
}
