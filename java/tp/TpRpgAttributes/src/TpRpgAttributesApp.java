import java.util.ArrayList;
import java.util.List;

public class TpRpgAttributesApp {
    public static void main(String[] args) {

        // ----------------------------------------------------\\
        // -------------- Instances des héros ----------------- \\
        // ------------------------------------------------------\\

        Hero ranger = new Hero("Ranger intrépide", "Héros", 80, 80, 80);
        ranger.addAttribute("Métier", "Ranger");
        ranger.addAttribute("Niveau", "1");
        ranger.addAttribute("Energie", "5");

        Hero magicienDesTenebres = new Hero("Magicien des Ténèbres", "Héros", 60, 100, 120);
        magicienDesTenebres.addAttribute("Métier", "Mage");
        magicienDesTenebres.addAttribute("Niveau", "4");
        magicienDesTenebres.addAttribute("Energie", "10");

        Hero nainBarbare = new Hero("Nain barbare", "Héros", 75, 125, 20);
        nainBarbare.addAttribute("Métier", "Nain");
        nainBarbare.addAttribute("Niveau", "2");
        nainBarbare.addAttribute("Energie", "20");

        Hero voleur = new Hero("Voleur maladroit", "Héros", 90, 85, 125);
        voleur.addAttribute("Métier", "Voleur");
        voleur.addAttribute("Niveau", "3");
        voleur.addAttribute("Energie", "15");

        Hero ogre = new Hero("Ogre affamé", "Héros", 250, 250, 5);
        ogre.addAttribute("Métier", "Ogre");
        ogre.addAttribute("Niveau", "10");
        ogre.addAttribute("Energie", "100");

        // ----------------------------------------------------\\
        // -------------- Instances des monstres -------------- \\
        // ------------------------------------------------------\\

        Monster goblin = new Monster("Goblin des cavernes","Gobelin", 50, 50, 100 );
        goblin.addAttribute("Taille", "petite");
        goblin.addAttribute("Dangerosité", "2");
        goblin.addAttribute("Habitat", "Grotte");

        Monster wolf = new Monster("Loup des bois","Lycan", 60, 60, 90 );
        wolf.addAttribute("Taille", "moyenne");
        wolf.addAttribute("Dangerosité", "2");
        wolf.addAttribute("Habitat", "Forêt");

        Monster vampire = new Monster("Vampire albinos","Vampire", 120, 80, 115 );
        vampire.addAttribute("Taille", "grande");
        vampire.addAttribute("Dangerosité", "3");
        vampire.addAttribute("Habitat", "Grotte");

        Monster zombie = new Monster("Zombie archer","Zombie", 50, 65, 10 );
        zombie.addAttribute("Taille", "moyenne");
        zombie.addAttribute("Dangerosité", "2");
        zombie.addAttribute("Habitat", "Plaines");

        Monster dragon = new Monster("Dragon colossal","Dragon", 500, 250, 200 );
        dragon.addAttribute("Taille", "colossal");
        dragon.addAttribute("Dangerosité", "12");
        dragon.addAttribute("Habitat", "Montagnes");

        // Instance de l'équipe Héros
        Team heroTeam = new Team("Héros", new ArrayList<Character>(List.of(ranger, magicienDesTenebres, nainBarbare, voleur, ogre)));

        for(Character hero : heroTeam.getCharacters()) {
            hero.showInfo();
        }

        // Instance de l'équipe Monstres
        Team monsterTeam = new Team("Monstres", new ArrayList<Character>(List.of(goblin, wolf, vampire, zombie, dragon)));


        for(Character monster : monsterTeam.getCharacters()) {
            monster.showInfo();
        }

        // Instance de la partie
        Game game = new Game(heroTeam, monsterTeam);

        // Lancer une partie
        game.play();


    }
}
