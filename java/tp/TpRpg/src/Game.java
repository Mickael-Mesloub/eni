import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    public Team playerTeam;
    public Team enemyTeam;
    ArrayList<Team> teams;

    public Game(Team _playerTeam, Team _enemyTeam) {
        this.playerTeam = _playerTeam;
        this.enemyTeam = _enemyTeam;
        teams = new ArrayList<Team>(List.of(_playerTeam, _enemyTeam));
    }

    // TODO: add random stats (hp, power)
    // TODO: add dealing random attack damage
    // TODO: javadoc

    // Méthode pour lancer une partie
    public void play() {
        // Affichage du message de début de partie
        System.out.println("------------------ \n\n Game ready to start! Let's BATTLE! \uD83E\uDD4A \n\n ------------------ \n");

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {
            // Pour chaque équipe, on récupère le battler avec le plus d'initiative
            Battler playerBattlerWithMostInitiative = getAttacker(playerTeam.battlers);
            Battler enemyBattlerWithMostInitiative = getAttacker(enemyTeam.battlers);

            // On récupère les 2 combattants avec le plus d'ini : celui de la playerTeam et celui de l'enemyTeam
            ArrayList<Battler> bothTeamFastestBattlers = new ArrayList<Battler>(List.of(playerBattlerWithMostInitiative, enemyBattlerWithMostInitiative));

            // Entre ces 2 combattants, je détermine celui qui a le plus d'ini (= celui qui va attaquer en premier) pour savoir quelle équipe commence.
            Battler firstAttacker = getAttacker(bothTeamFastestBattlers);

            // On vérifie dans quelle équipe se trouve le firstAttacker pour qu'il attaque l'autre équipe
            // Si firstAttacker est dans playerTeam
            if (playerTeam.battlers.contains(firstAttacker)) {
                // Alors, l'équipe playerTeam commence la partie et attaque enemyTeam en premier
               playTeamTurn(firstAttacker, playerTeam, enemyTeam);
            } else {
                // Sinon : si firstAttacker n'est pas dans playerTeam, alors il est dans enemyTeam et c'est donc au tour d'enemyTeam de jouer.
                playTeamTurn(firstAttacker, enemyTeam, playerTeam);
            }

            // S'il n'y a pas d'équipe gagnante, on continue
            if (getWinnerTeam() == null) {
                // Tour suivant : si l'équipe playerTeam a joué en dernier
                if (playerTeam.battlers.contains(firstAttacker)) {
                    // Alors, c'est au tour d'enemyTeam de riposter
                    playTeamTurn(enemyBattlerWithMostInitiative, enemyTeam, playerTeam);
                } else {
                    // Sinon, cela signifie que l'équipe enemyTeam a joué en dernier, donc c'est au tour de playerTeam de jouer son tour.
                    playTeamTurn(playerBattlerWithMostInitiative, playerTeam, enemyTeam);
                }
            } else {
                showWinnerMessage(getWinnerTeam());
            }
            System.out.println("\n \n ------------------ \n");
        }
    }

    // Méthode pour déterminer l'équipe gagnante
    public Team getWinnerTeam() {
        return teams.stream()
                .filter((Team::isLoser))
                .findFirst()
                .orElse(null);
    }

    public void showWinnerMessage(Team winner) {
        System.out.printf("\n\n ------------------ \n\n \uD83C\uDFC6 And the winner is... %s!", winner.name);
    }

    // Récupère le battler avec le plus d'initiative pour définir l'ordre d'attaque
    public Battler getAttacker(ArrayList<Battler> battlers) {
        return battlers.stream()
                .max(Comparator.comparing(b -> b.initiative))
                .orElse(null);
    }

    // Récupère le battler le plus faible (= celui avec le moins d'hp)
    public Battler getWeakestEnemy(ArrayList<Battler> enemyBattlers) {
        return enemyBattlers.stream().min(Comparator.comparing(b -> b.hp)).orElse(null);
    }

    /**
     * Méthode permettant à une équipe de jouer son tour
     * @param attacker Le battler qui se prépare à attaquer
     * @param attackingTeam L'équipe qui attaque
     * @param defendingTeam L'équipe qui subit l'attaque
     */
    public void playTeamTurn(Battler attacker, Team attackingTeam, Team defendingTeam) {
        // On vérifie si le battler d'attackingTeam est toujours vivant
        if (!attacker.isDead()) {
            // Si oui, il attaque l'équipe defendingTeam
            attacker.attack(defendingTeam);
        } else {
            // Sinon, je détermine le nouvel attacker de attackingTeam
            Battler nextAttacker = getAttacker(attackingTeam.battlers);
            // Et ce nouvel attacker attaque defendingTeam
            nextAttacker.attack(defendingTeam);
        }
    }
}
