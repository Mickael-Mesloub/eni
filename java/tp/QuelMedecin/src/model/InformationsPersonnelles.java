package model;

public class InformationsPersonnelles {
    private final String nom;
    private String prenom;
    private String numeroDeTelephone;

    public InformationsPersonnelles(String nom, String prenom, String numeroDeTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public String getNom() {
        return nom;
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
}
