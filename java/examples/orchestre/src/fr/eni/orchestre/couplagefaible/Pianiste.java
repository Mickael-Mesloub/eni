package fr.eni.orchestre.couplagefaible;

public class Pianiste implements Musicien {
    private Instrument piano;
    private String morceau;

    public Pianiste(String morceau, Instrument piano) {
        this.morceau = morceau;
        this.piano = piano;
    }

    public void jouerMorceau(){
        System.out.println("Je joue " + morceau);
        piano.jouer();
        piano.afficher();
    }

}
