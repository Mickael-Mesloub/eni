package fr.eni.zoo.bo;

import java.util.Objects;

public class Adresse {
    private int id;
    private int numero;
    private String rue;
    private String ville;
    private String codePostal;

    public Adresse(int id, int numero, String rue, String ville, String codePostal) {
        this.id = id;
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public Adresse(int numero, String rue, String ville, String codePostal) {
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public Adresse() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", numero=" + numero +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Adresse adresse)) return false;
        return id == adresse.id && numero == adresse.numero && Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville) && Objects.equals(codePostal, adresse.codePostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, rue, ville, codePostal);
    }
}
