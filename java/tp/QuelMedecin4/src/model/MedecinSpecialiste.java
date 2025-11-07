package model;

public class MedecinSpecialiste extends Medecin implements Comparable<Personne>{
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
                .append("Spécialité : ").append(specialite.toUpperCase()).append("\n");

        return info.toString();
    }

    @Override
    public int compareTo(MedecinSpecialiste otherMedSpe) {
        return this.specialite.compareTo(otherMedSpe.getSpecialite());
    };

    @Override
    public void examiner(){
        super.examiner();
    };

    @Override
    public void diagnostiquer(){
        super.traiter();
    };

    @Override
    public void traiter(){
        super.traiter();
    };

    @Override
    public void conseiller() {
        super.conseiller();
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    @Override
    public int compareTo(Personne o) {
        return 0;
    }
}
