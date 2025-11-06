package model;

public abstract class Personne implements Comparable {
    private final String nom;
    private final String prenom;
    private String numeroDeTelephone;
    private final Adresse adresse;

    public Personne(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        nom = _nom;
        prenom = _prenom;
        numeroDeTelephone = _numeroDeTelephone;
        adresse = _adresse;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        info.append(getNom().toUpperCase()).append(" ").append(getPrenom()).append("\n")
        .append("Téléphone : ").append(getNumeroDeTelephone()).append("\n");

        return info.toString();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public Adresse getAdresse() {
        return adresse;
    }
}
