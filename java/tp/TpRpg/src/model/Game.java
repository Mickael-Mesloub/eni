package model;

import model.characters.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    private final Team playerTeam;
    private final Team enemyTeam;
    ArrayList<Team> teams;

    public Game(Team _playerTeam, Team _enemyTeam) {
        this.playerTeam = _playerTeam;
        this.enemyTeam = _enemyTeam;
        teams = new ArrayList<Team>(List.of(_playerTeam, _enemyTeam));
    }

    /**
     * Lance une partie. La partie continue tant qu'il n'y a pas d'équipe gagnante.
     */
    public void play() {
        // Affichage du message de début de partie
        System.out.println("------------------------------------ \n Here are our challengers! 💪 \n");

        for(Team team : teams) {
            for(Character character : team.getCharacters()) {
                character.showInfo();
            }
        }

        System.out.println("------------------------------------ \n\n model.Game ready to start! Let's BATTLE! \uD83E\uDD4A \n\n ------------------------------------ \n");

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {
            // Pour chaque équipe, on récupère le Character avec le plus d'initiative
            Character playerCharacterWithMostInitiative = getAttacker(playerTeam.getCharacters());
            Character enemyCharacterWithMostInitiative = getAttacker(enemyTeam.getCharacters());

            // On récupère les 2 personnages avec le plus d'ini : celui de la playerTeam et celui de l'enemyTeam
            ArrayList<Character> bothTeamFastestCharacters = new ArrayList<Character>(List.of(playerCharacterWithMostInitiative, enemyCharacterWithMostInitiative));

            // Entre ces 2 personnages, je détermine celui qui a le plus d'ini (= celui qui va attaquer en premier) pour savoir quelle équipe commence.
            Character firstAttacker = getAttacker(bothTeamFastestCharacters);

            // On vérifie dans quelle équipe se trouve le firstAttacker pour qu'il attaque l'autre équipe
            // Si firstAttacker est dans playerTeam
            if (playerTeam.getCharacters().contains(firstAttacker)) {
                // Alors, l'équipe playerTeam commence la partie et attaque enemyTeam en premier
               playTeamTurn(firstAttacker, playerTeam, enemyTeam);
            } else {
                // Sinon : si firstAttacker n'est pas dans playerTeam, alors il est dans enemyTeam et c'est donc au tour d'enemyTeam de jouer.
                playTeamTurn(firstAttacker, enemyTeam, playerTeam);
            }

            // S'il n'y a pas d'équipe gagnante, on continue
            if (getWinnerTeam() == null) {
                // Tour suivant : si l'équipe playerTeam a joué en dernier
                if (playerTeam.getCharacters().contains(firstAttacker)) {
                    // Alors, c'est au tour d'enemyTeam de riposter
                    playTeamTurn(enemyCharacterWithMostInitiative, enemyTeam, playerTeam);
                } else {
                    // Sinon, cela signifie que l'équipe enemyTeam a joué en dernier, donc c'est au tour de playerTeam de jouer son tour.
                    playTeamTurn(playerCharacterWithMostInitiative, playerTeam, enemyTeam);
                }
            }
            System.out.println("\n \n ------------------------------------ \n");
        }
        showWinnerMessage(getWinnerTeam());
    }

    /**
     * Détermine l'équipe gagnante pour mettre fin à la partie
     * @return L'équipe gagnante
     */
    public Team getWinnerTeam() {
        Team loser = teams.stream()
                .filter((Team::isLoser))
                .findFirst()
                .orElse(null);

        if(loser != null) {
            return (loser == playerTeam) ? enemyTeam : playerTeam;
        }

        return null;
    }

    /**
     * Affiche un message annonçant l'équipe gagnante
     * @param winner L'équipe gagnante
     */
    public void showWinnerMessage(Team winner) {
        System.out.printf("\uD83C\uDFC6 And the winner is... team %s!", winner.getName());
    }

    /**
     * Récupère le personnage avec le plus d'initiative (pour définir l'ordre d'attaque)
     * @param characters La liste de personnages qu'on veut comparer
     * @return Le personnage avec le plus d'initiative
     */
    public Character getAttacker(ArrayList<Character> characters) {
        return characters.stream()
                .max(Comparator.comparing(Character::getInitiative))
                .orElse(null);
    }

    /**
     * Récupère le personnage ennemi le plus faible ((= celui avec le moins d'hp)
     * @param enemyCharacters La liste des personnages ennemis
     * @return Le personnage avec le moins d'hp
     */
    public Character getWeakestEnemy(ArrayList<Character> enemyCharacters) {
        return enemyCharacters.stream().min(Comparator.comparing(Character::getHp)).orElse(null);
    }

    /**
     * Permet à une équipe de jouer son tour
     * @param attacker Le personnage qui se prépare à attaquer
     * @param attackingTeam L'équipe qui attaque
     * @param defendingTeam L'équipe qui subit l'attaque
     */
    public void playTeamTurn(Character attacker, Team attackingTeam, Team defendingTeam) {
        // On vérifie si le Character d'attackingTeam est toujours vivant
        if (!attacker.isDead()) {
            // Si oui, il attaque l'équipe defendingTeam
            attacker.attack(defendingTeam);
        } else {
            // Sinon, je détermine le nouvel attacker de attackingTeam
            Character nextAttacker = getAttacker(attackingTeam.getCharacters());
            // Et ce nouvel attacker attaque defendingTeam
            nextAttacker.attack(defendingTeam);
        }
    }
}
