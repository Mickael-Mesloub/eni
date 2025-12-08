package fr.eni.tp.filmotheque.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Genre implements Serializable {
    /**
     * Numéro de sérialisation
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String titre;

    public Genre(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Genre(String titre) {
        this.titre = titre;
    }

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return getId() == genre.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
