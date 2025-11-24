package fr.eni.orchestre.couplagefort;

public class Violoniste {
    private Violon violon;
    private String morceau;

    public Violoniste(String morceau) {
        this.morceau = morceau;
        this.violon = new Violon();
    }

    public void jouerMorceau(){
        System.out.println("Je joue " + morceau);
        violon.jouer();
        violon.afficher();
    }
}
