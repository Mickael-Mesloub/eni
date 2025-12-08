package fr.eni.tp.filmotheque.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Avis implements Serializable {
    /**
     * Numéro de sérialisation
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int note;
    private String commentaire;
    private Membre membre;

    public Avis(int id, int note, String commentaire, Membre membre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }

    public Avis(int note, String commentaire, Membre membre) {
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", membre=" + membre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Avis avis = (Avis) o;
        return getId() == avis.getId();
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
}
