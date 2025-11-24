//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Pianiste p1 = new Pianiste("Crazy Frog");
        Violoniste v1 = new Violoniste("Crazy Frog");

        Orchestre  orchestre = new Orchestre();
        orchestre.ajouterMusicien(p1);
        orchestre.ajouterMusicien(v1);

        orchestre.jouer();
    }
}