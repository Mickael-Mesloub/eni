package fr.eni.orchestre.couplagefaible;

public class Violoniste implements Musicien {
    private Instrument violon;
    private String morceau;

    public Violoniste(String morceau, Instrument violon) {
        this.morceau = morceau;
        this.violon = violon;
    }

    public void jouerMorceau(){
        System.out.println("Je joue " + morceau);
        violon.jouer();
        violon.afficher();
    }
}
