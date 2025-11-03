import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    public Team heroTeam;
    public Team monsterTeam;
    ArrayList<Team> teams;

    public Game(Team _heroTeam, Team _monsterTeam) {
        this.heroTeam = _heroTeam;
        this.monsterTeam = _monsterTeam;
        teams = new ArrayList<Team>(List.of(_heroTeam, _monsterTeam));
    }


    /**
     * Lance une partie. La partie continue tant qu'il n'y a pas d'équipe gagnante.
     */
    public void play() {
        // Affichage du message de début de partie
        System.out.println("------------------ \n\n Game ready to start! Let's BATTLE! \uD83E\uDD4A \n\n ------------------ \n");

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {
            // Pour chaque équipe, on récupère le character avec le plus d'initiative
            Character playerCharacterWithMostInitiative = getAttacker(heroTeam.getCharacters());
            Character enemyCharacterWithMostInitiative = getAttacker(monsterTeam.getCharacters());

            // On récupère les 2 combattants avec le plus d'ini : celui de la heroTeam et celui de l'monsterTeam
            ArrayList<Character> bothTeamFastestCharacters = new ArrayList<Character>(List.of(playerCharacterWithMostInitiative, enemyCharacterWithMostInitiative));

            // Entre ces 2 combattants, je détermine celui qui a le plus d'ini (= celui qui va attaquer en premier) pour savoir quelle équipe commence.
            Character firstAttacker = getAttacker(bothTeamFastestCharacters);

            // On vérifie dans quelle équipe se trouve le firstAttacker pour qu'il attaque l'autre équipe
            // Si firstAttacker est dans heroTeam
            if (heroTeam.getCharacters().contains(firstAttacker)) {
                // Alors, l'équipe heroTeam commence la partie et attaque monsterTeam en premier
               playTeamTurn(firstAttacker, heroTeam, monsterTeam);
            } else {
                // Sinon : si firstAttacker n'est pas dans heroTeam, alors il est dans monsterTeam et c'est donc au tour d'monsterTeam de jouer.
                playTeamTurn(firstAttacker, monsterTeam, heroTeam);
            }

            // S'il n'y a pas d'équipe gagnante, on continue
            if (getWinnerTeam() == null) {
                // Tour suivant : si l'équipe heroTeam a joué en dernier
                if (heroTeam.getCharacters().contains(firstAttacker)) {
                    // Alors, c'est au tour de monsterTeam de riposter
                    playTeamTurn(enemyCharacterWithMostInitiative, monsterTeam, heroTeam);
                } else {
                    // Sinon, cela signifie que l'équipe monsterTeam a joué en dernier, donc c'est au tour de heroTeam de jouer son tour.
                    playTeamTurn(playerCharacterWithMostInitiative, heroTeam, monsterTeam);
                }
            }
            System.out.println("\n \n ------------------ \n");
        }
        showWinnerMessage(getWinnerTeam());
    }

    /**
     * Détermine l'équipe gagnante pour mettre fin à la partie.
     * @return L'équipe gagnante :
     */
    public Team getWinnerTeam() {
        Team loser = teams.stream()
                .filter((Team::isLoser))
                .findFirst()
                .orElse(null);

        if(loser != null) {
            return (loser == heroTeam) ? monsterTeam : heroTeam;
        }

        return null;
    }

    /**
     * Affiche un message annonçant l'équipe gagnante.
     * @param winner L'équipe gagnante
     */
    public void showWinnerMessage(Team winner) {
        System.out.printf("\n \uD83C\uDFC6 And the winner is... %s! \n ------------------", winner.getName());
    }

    // Récupère le character avec le plus d'initiative pour définir l'ordre d'attaque
    public Character getAttacker(ArrayList<Character> characters) {
        return characters.stream()
                .max(Comparator.comparing(b -> b.getInitiative()))
                .orElse(null);
    }

    // Récupère le character le plus faible (= celui avec le moins d'hp)
    public Character getWeakestEnemy(ArrayList<Character> enemyCharacters) {
        return enemyCharacters.stream().min(Comparator.comparing(b -> b.getHp())).orElse(null);
    }

    /**
     * Méthode permettant à une équipe de jouer son tour
     * @param attacker Le character qui se prépare à attaquer
     * @param attackingTeam L'équipe qui attaque
     * @param defendingTeam L'équipe qui subit l'attaque
     */
    public void playTeamTurn(Character attacker, Team attackingTeam, Team defendingTeam) {
        // On vérifie si le character d'attackingTeam est toujours vivant
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
