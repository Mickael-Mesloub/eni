package model;

import service.Methode;

public class MedecinGeneraliste extends Medecin {
    private int tarif;

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
                .append("Tarif : ").append(getTarif()).append("€").append("\n")
                .append(getAdresse().getAdresseInfo());

        System.out.println(info);
        showCreneaux();

        return info.toString();
    }




    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public int getTarif() {
        return tarif;
    }
}
