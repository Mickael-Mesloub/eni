package model;

public class MedecinSpecialiste extends Medecin{
    private  String specialite;
    private int tarif;

    public MedecinSpecialiste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse, String _specialite, int _tarif) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);

        specialite = _specialite;
        tarif = _tarif;
    }

    public MedecinSpecialiste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }
}
