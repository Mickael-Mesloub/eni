public class Aeroport {
    private Ville ville;
    private String nom;

    public Aeroport() {
    }

    public Aeroport(Ville ville, String nom) {
        this.ville = ville;
        this.nom = nom;
    }

    public Ville getVille() {
        return ville;
    }
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "ville=" + ville +
                ", nom='" + nom + '\'' +
                '}';
    }
}
