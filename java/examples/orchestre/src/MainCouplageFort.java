import fr.eni.orchestre.couplagefort.*;

public class MainCouplageFort {
    public static void main(String[] args) {
        Pianiste p1 = new Pianiste("Crazy Frog");
        Violoniste v1 = new Violoniste("Crazy Frog");

        Orchestre orchestre = new Orchestre();
        orchestre.ajouterMusicien(p1);
        orchestre.ajouterMusicien(v1);

        orchestre.jouer();
    }
}