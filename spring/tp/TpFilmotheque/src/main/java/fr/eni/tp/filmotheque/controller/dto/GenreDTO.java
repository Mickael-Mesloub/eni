package fr.eni.tp.filmotheque.controller.dto;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class GenreDTO {
    private int  id;
    @Size(min = 1, max = 50, message = "Le titre doit faire entre {min} et {max} caractères")
    private String titre;

    public GenreDTO() {
    }

    public GenreDTO(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GenreDTO genreDTO)) return false;
        return id == genreDTO.id && Objects.equals(titre, genreDTO.titre);
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

    @Override
    public int hashCode() {
        return Objects.hash(id, titre);
    }
}
