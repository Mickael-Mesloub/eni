package model;

public class MedecinSpecialiste extends MedecinGeneraliste{
    private final String specialite;

    public MedecinSpecialiste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse, String _specialite, int _tarif) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);
        specialite = _specialite;
        this.setTarif(_tarif);
    }

    public String getSpecialite() {
        return specialite;
    }
}
