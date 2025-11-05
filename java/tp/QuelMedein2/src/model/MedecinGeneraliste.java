package model;

import service.Methode;

import java.util.ArrayList;

public class MedecinGeneraliste extends  InformationsPersonnelles{
    private static int tarif;
    private final ArrayList<Creneau> creneaux;

    public MedecinGeneraliste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);

        Methode ctrl = Methode.getMethode();

        // Affichage du message d'erreur si n° téléphone invalide
        if (!ctrl.isNumTelValid(_numeroDeTelephone)) {
            System.out.println(ctrl.colorizeString("Le numero de téléphone " + _numeroDeTelephone + " n'est pas conforme !"));
            setNumeroDeTelephone("<< numero incorrect >>");
        } else {
            // Si n° téléphone valide, on le formate (0X.XX.XX.XX.XX)
            setNumeroDeTelephone(ctrl.formatNumeroTelephone(_numeroDeTelephone));
        }

        tarif = 25;
        creneaux = new ArrayList<Creneau>();
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
        info.append(getNom().toUpperCase()).append(" ")
                .append(getPrenom()).append("\n")
                .append("Téléphone : ").append(getNumeroDeTelephone()).append("\n")
                .append("Tarif : ").append(getTarif()).append("€").append("\n")
                .append(getAdresse().getAdresseInfo());
        System.out.println(info);
        showCreneaux();
    }

    public void ajouterCreneau(Creneau creneau) {
        creneaux.add(creneau);
    }

    public void showCreneaux() {
        System.out.println("Créneaux :");
        for(Creneau creneau : creneaux) {
            creneau.afficher();
        }
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public int getTarif() {
        return tarif;
    }

    public static void setTarif(int _tarif) {
        tarif = _tarif;
    }

    public ArrayList<Creneau> getCreneaux() {
        return creneaux;
    }
}
