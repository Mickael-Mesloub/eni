import java.time.LocalDateTime;

public class Escale {
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private Aeroport aeroport;

    public Escale(LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee, Aeroport aeroport) {
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.aeroport = aeroport;
    }

    public Escale() {
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

    public Aeroport getAeroport() {
        return aeroport;
    }

    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }


}
