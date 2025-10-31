import java.util.ArrayList;
import java.util.List;

public class TpRpgAttributesApp {
    public static void main(String[] args) {

        Hero hero = new Hero("Aria", "Légion du Nord");
        hero.addAttribute("Métier", "Archère");
        hero.addAttribute("Niveau", "1");
        hero.addAttribute("Energie", "5");

        Monster monster = new Monster("Goblin","Gobelin des cavernes" );
        monster.addAttribute("Taille", "petite");
        monster.addAttribute("Dangerosité", "2");
        monster.addAttribute("Habitat", "Grotte");

        ArrayList<Character> characters = new ArrayList<Character>(List.of(hero, monster));

        for(Character character : characters) {
            character.showInfo();
        }
    }
}
