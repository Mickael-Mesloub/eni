import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Vol {
    private String numero;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;

    // Association(s) many to one
    private Avion avion;
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrivee;

    // Associations one to many
    private ArrayList<Escale> escales = new ArrayList<Escale>();

    // Constructeur(s)
    public Vol() {
    }

    public Vol(String numero, LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee) {
        this.numero = numero;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
    }

    // Getters setters
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrivee() {
        return dateHeureArrivee;
    }
    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public Avion getAvion() {
        return avion;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }
    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }
    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }

    public ArrayList<Escale> getEscales() {
        return escales;
    }
    public void setEscales(ArrayList<Escale> escales) {
        this.escales = escales;
    }

    // Méthodes
    @Override
    public String toString() {
        return "Vol{" +
                "numero='" + numero + '\'' +
                ", dateHeureDepart=" + dateHeureDepart +
                ", dateHeureArrivee=" + dateHeureArrivee +
                ", avion=" + avion +
                ", aeroportDepart=" + aeroportDepart +
                ", aeroportArrivee=" + aeroportArrivee +
                ", escales=" + escales +
                '}';
    }



}
