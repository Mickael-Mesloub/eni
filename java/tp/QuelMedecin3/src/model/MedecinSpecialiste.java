package model;

public class MedecinSpecialiste extends Medecin{
    private final String specialite;
    private final int tarif;

    public MedecinSpecialiste(String _nom, String _prenom, String _numeroDeTelephone, Adresse _adresse, String _specialite, int _tarif) {
        super(_nom, _prenom, _numeroDeTelephone, _adresse);

        tarif = _tarif;
        specialite = _specialite;

        showCreneaux();
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

    public int getTarif() {
        return tarif;
    }
}
