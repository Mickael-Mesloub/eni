import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private char sexe;
    private long numeroSecuriteSociale;
    private LocalDate dateDeNaissance;
    private String commentaire;

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

        System.out.println( info.toString());
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public long getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(long numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}

