package fr.eni.orchestre.couplagefort;

public class Pianiste {
    private Piano piano;
    private String morceau;

    public Pianiste(String morceau) {
        this.morceau = morceau;
        this.piano = new Piano();
    }

    public void jouerMorceau(){
        System.out.println("Je joue " + morceau);
        piano.jouer();
        piano.afficher();
    }

}
