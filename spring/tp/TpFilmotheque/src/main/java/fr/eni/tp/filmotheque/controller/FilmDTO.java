package fr.eni.tp.filmotheque.controller;

import jakarta.validation.constraints.*;

public class FilmDTO {
    private long id;
    @Size(min = 1, max = 100, message = "Le titre doit faire entre {min} et {max} caractères")
    private String titre;
    @NotNull(message = "L'année ne peut pas être vide")
    @Min(value = 1900, message = "L'année ne peut pas être avant {value}")
    @Max(value = 2025, message = "L'année ne peut pas être après {value}")
    private Integer annee;
    @NotNull(message = "La durée ne peut pas être vide")
    @Min(value = 1)
    private Integer duree;
    @NotBlank(message = "Le synopsis ne peut pas être vide")
    private String synopsis;
    private long idGenre;

    public FilmDTO() {
    }

    public FilmDTO(String titre, int annee, int duree, String synopsis, long idGenre) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.idGenre = idGenre;
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

    public Integer getAnnee() {
        return annee;
    }
    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getDuree() {
        return duree;
    }
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(long idGenre) {
        this.idGenre = idGenre;
    }
}
