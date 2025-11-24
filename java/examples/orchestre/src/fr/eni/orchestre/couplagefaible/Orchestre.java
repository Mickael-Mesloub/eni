package fr.eni.orchestre.couplagefaible;

import java.util.ArrayList;
import java.util.List;

public class Orchestre {
    List<Musicien> musiciens = new ArrayList<Musicien>();

    public Orchestre() {
    }

    public void ajouterMusicien(Musicien musicien){
        musiciens.add(musicien);
    }

    public void jouer(){
        musiciens.forEach(Musicien ::jouerMorceau);
    }
}
