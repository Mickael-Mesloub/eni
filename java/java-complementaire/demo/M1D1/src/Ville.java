import java.util.Objects;

public class Ville {
    // Attribut(s)

    private String nom;

    // Constructeur(s)

    public Ville() {
    }

    public Ville(String nom) {
        this.nom = nom;
    }

    // Accesseurs et Mutateurs

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        nom = this.nom;
    }

    // Méthodes et redéfinitions de super méthodes

    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                '}';
    }

    // Permet de vérifier si un objet est dans une liste
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Objects.equals(nom, ville.nom);
    }

    // La méthode hashCode permet de vérifier si un objet est présent dans une Map
    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}
