package fr.eni.orchestre.couplagefort;

import java.util.ArrayList;
import java.util.List;

public class Orchestre {
    List<Object> musiciens = new ArrayList<Object>();

    public Orchestre() {
    }

    public void ajouterMusicien(Object musicien){
        musiciens.add(musicien);
    }

    public void jouer(){
        musiciens.forEach(m -> {
            if(m  instanceof Pianiste) {
                ((Pianiste) m).jouerMorceau();
            } else if(m instanceof Violoniste)  {
                ((Violoniste) m).jouerMorceau();
            }
        });
    }
}
