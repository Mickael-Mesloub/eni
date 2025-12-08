package fr.eni.tp.filmotheque.controller.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class FilmDTO {
    private int id;
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
    private int idGenre;
    private int idRealisateur;
    private List<Integer> idsActeurs;

    public FilmDTO() {
    }

    public FilmDTO(String titre, int annee, int duree, String synopsis, int idGenre, int idRealisateur, List<Integer> idsActeurs) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.idGenre = idGenre;
        this.idRealisateur = idRealisateur;
        this.idsActeurs = idsActeurs;
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

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public int getIdRealisateur() {
        return idRealisateur;
    }

    public void setIdRealisateur(int idRealisateur) {
        this.idRealisateur = idRealisateur;
    }

    public List<Integer> getIdsActeurs() {
        return idsActeurs;
    }

    public void setIdsActeurs(List<Integer> idsActeurs) {
        this.idsActeurs = idsActeurs;
    }
}
