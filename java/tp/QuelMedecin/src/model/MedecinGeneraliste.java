package model;

public class MedecinGeneraliste {
    private final String nom;
    private final String prenom;
    private String numeroDeTelephone;
    private static int tarif;

    public MedecinGeneraliste(String _nom, String _prenom, String _numeroDeTelephone) {
        nom = _nom;
        prenom = _prenom;
        numeroDeTelephone = _numeroDeTelephone;
        tarif = 25;
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
        info.append(getNom().toUpperCase()).append(" ")
                .append(getPrenom()).append("\n")
                .append("Téléphone : ").append(getNumeroDeTelephone()).append("\n")
                .append("Tarif : ").append(getTarif()).append("€");

        System.out.println(info);
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

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public int getTarif() {
        return tarif;
    }

    public static void setTarif(int _tarif) {
        tarif = _tarif;
    }
}
