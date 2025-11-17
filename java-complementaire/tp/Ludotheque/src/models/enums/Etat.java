package models.enums;

public enum Etat {
    BON("Bon"),
    CORRECT("Correct"),
    MAUVAIS("Mauvais");

    public final String label;

    private  Etat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
