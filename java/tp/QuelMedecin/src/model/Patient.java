package  model;

import service.Methode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient extends InformationsPersonnelles {
    private final char sexe;
    private final long numeroSecuriteSociale;
    private final LocalDate dateDeNaissance;
    private final String commentaire;


    public Patient(String _nom, String _prenom, String _numeroDeTelephone, char _sexe, long _numeroSecuriteSociale, LocalDate _dateDeNaissance, String _commentaire ) {
        super(_nom, _prenom, _numeroDeTelephone);

        Methode ctrl = Methode.getMethode();

        // Affichage du message d'erreur si n° téléphone invalide
        if (!ctrl.isNumTelValid(_numeroDeTelephone)) {
            System.out.println(ctrl.colorizeString("Le numero de téléphone " + _numeroDeTelephone + " n'est pas conforme !"));
            setNumeroDeTelephone("<< numero incorrect >>");
        } else {
            // Si n° téléphone valide, on le formate (0X.XX.XX.XX.XX)
            setNumeroDeTelephone(ctrl.formatNumeroTelephone(_numeroDeTelephone));
        }

        sexe = _sexe;
        numeroSecuriteSociale = _numeroSecuriteSociale;
        dateDeNaissance = _dateDeNaissance;
        commentaire = _commentaire;
    }



    /**
     * Retourne le texte à afficher pour le sexe
     * @return Une String : "Féminin" si "F, et "Masculin" si "M"
     */
    public String displayGender() {
        return (sexe == 'F') ? "Féminin" : "Masculin";
    }

    /**
     * Retourne le texte à afficher pour les commentaires
     * @return Le commentaire si fournit, sinon, "[aucun commentaire]" comme valeur par défaut
     */
    public String displayComments() {
        return (commentaire == null) ? "[aucun commentaire]" : commentaire;
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
                info.append(getNom().toUpperCase()).append(" ")
                .append(getPrenom()).append("\n")
                .append("Téléphone : ").append(getNumeroDeTelephone()).append("\n")
                .append("Sexe : ").append(displayGender()).append("\n")
                .append("Numéro de sécurité sociale : ").append(getNumeroSecuriteSociale()).append("\n")
                .append("Date de naissance : ").append(getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).append("\n")
                .append("Commentaires : ").append(displayComments());

        System.out.println( info);
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public long getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

}

