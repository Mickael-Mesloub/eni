import fr.eni.orchestre.couplagefaible.*;

public class MainCouplageFaible {
    public static void main(String[] args) {
        Piano piano = new Piano();
        Violon violon = new Violon();
        Pianiste pianiste = new Pianiste("Clair Obscur : Expedition 33", piano);
        Violoniste violoniste = new Violoniste("Clair Obscur : Expedition 33", violon);

        Orchestre orchestre = new Orchestre();
        orchestre.ajouterMusicien(pianiste);
        orchestre.ajouterMusicien(violoniste);

        orchestre.jouer();
    }
}
