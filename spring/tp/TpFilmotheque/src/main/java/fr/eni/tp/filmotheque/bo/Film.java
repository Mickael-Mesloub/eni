package fr.eni.tp.filmotheque.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film implements Serializable {
    /**
     * Numéro de sérialisation
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String titre;
    private int annee;
    private int duree;
    private String synopsis;
    private Genre genre;
    private Participant realisateur;
    private List<Participant> acteurs = new  ArrayList<Participant>();
    private List<Avis> avis = new ArrayList<Avis>();

    public Film(int id, String titre, int annee, int duree, String synopsis) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }

    public Film(String titre, int annee, int duree, String synopsis) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }


    public Film() {
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", annee=" + annee +
                ", duree=" + duree +
                ", synopsis='" + synopsis + '\'' +
                ", genre=" + genre +
                ", realisateur=" + realisateur +
                ", acteurs=" + acteurs +
                ", avis=" + avis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return getId() == film.getId();
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Participant> acteurs) {
        this.acteurs = acteurs;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
}
