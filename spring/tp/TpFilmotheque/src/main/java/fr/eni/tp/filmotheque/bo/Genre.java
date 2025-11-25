package fr.eni.tp.filmotheque.bo;

import java.io.Serializable;
import java.util.Objects;

public class Genre implements Serializable {
    private long id;
    private String titre;

    public Genre(long id, String titre) {
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
        return id == genre.id && Objects.equals(titre, genre.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
