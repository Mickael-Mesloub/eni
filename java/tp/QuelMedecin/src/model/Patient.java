package  model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private final String nom;
    private final String prenom;
    private final String numeroDeTelephone;
    private final char sexe;
    private final long numeroSecuriteSociale;
    private final LocalDate dateDeNaissance;
    private final String commentaire;

    public Patient(String _nom, String _prenom, String _numeroDeTelephone, char _sexe, long _numeroSecuriteSociale, LocalDate _dateDeNaissance, String _commentaire ) {
        nom = _nom;
        prenom = _prenom;
        numeroDeTelephone = _numeroDeTelephone;
        sexe = _sexe;
        numeroSecuriteSociale = _numeroSecuriteSociale;
        dateDeNaissance = _dateDeNaissance;
        commentaire = _commentaire;
    }

    /**
     * Retourne le texte à afficher pour le sexe
     * @return Une String : "Féminin" si "F, et "Masculin" si "M"
     */
    public String getDisplayGender() {
        return (sexe == 'F') ? "Féminin" : "Masculin";
    }

    /**
     * Retourne le texte à afficher pour les commentaires
     * @return Le commentaire si fournit, sinon, "[aucun commentaire]" comme valeur par défaut
     */
    public String getDisplayComments() {
        return (commentaire == null) ? "[aucun commentaire]" : commentaire;
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
        info.append(getNom().toUpperCase()).append(" ")
                .append(getPrenom()).append("\n")
                .append("Téléphone : ").append(getNumeroDeTelephone()).append("\n")
                .append("Sexe : ").append(getDisplayGender()).append("\n")
                .append("Numéro de sécurité sociale : ").append(getNumeroSecuriteSociale()).append("\n")
                .append("Date de naissance : ").append(getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))).append("\n")
                .append("Commentaires : ").append(getDisplayComments());

        System.out.println( info);
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public long getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }
}

