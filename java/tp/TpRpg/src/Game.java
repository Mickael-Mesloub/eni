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

    // TODO: refactor play() method : code repetition can be avoided by extracting in other methods
    // TODO: add random stats (hp, power)
    // TODO: add dealing random attack damage

    /**
     * Système d'initiative
     * Chaque combattant a un montant d'initiative
     * Avant d'attaquer, je dois vérifier s'il reste des combattants vivants
     * Ensuite, je dois déterminer QUI attaque en premier
     * Dans la team 1 je récupère celui qui a le plus d'initiative
     * Idem dans la team 2
     * Puis je compare celui qui a le plus d'initiative entre ces 2 combattants
     * Je vérifie de quelle équipe il fait partie
     * L'équipe qui a le combattant le plus rapide joue son tour
     * Puis c'est au tour de l'autre équipe
     */

    // Méthode pour lancer une partie
    public void play() {

        System.out.println("------------------ \n\n Game ready to start! Let's FIGHT! \uD83E\uDD4A \n\n ------------------ \n\n");

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {

            // Pour chaque équipe, on récupère le battler avec le plus d'initiative
            Battler playerBattlerWithMostInitiative = getAttacker(playerTeam.battlers);
            Battler enemyBattlerWithMostInitiative = getAttacker(enemyTeam.battlers);

            // Je récupère les 2 combattants avec le plus d'ini : celui de la playerTeam et celui de l'enemyTeam
            ArrayList<Battler> bothTeamFastestBattlers = new ArrayList<Battler>(List.of(playerBattlerWithMostInitiative, enemyBattlerWithMostInitiative));

            // Entre ces 2 combattants ennemis, je détermine celui qui a le plus d'ini (= celui qui va attaquer en premier)
            Battler firstAttacker = getAttacker(bothTeamFastestBattlers);

            // Je vérifie dans quelle équipe se trouve le firstAttacker pour qu'il attaque l'autre équipe
            // Si firstAttacker est dans playerTeam
            if (playerTeam.battlers.contains(firstAttacker)) {
                // Je vérifie si le battler de playerTeam est toujours vivant
                if (!firstAttacker.isDead()) {
                    // Si oui, il attaque l'équipe enemyTeam
                    firstAttacker.attack(enemyTeam);
                } else {
                    // Sinon, je détermine le nouvel attacker de playerTeam
                    Battler nextAttacker = getAttacker(playerTeam.battlers);
                    // Et ce nouveau battler attaque enemyTeam
                    nextAttacker.attack(enemyTeam);
                }

                // Sinon : si firstAttacker n'est pas dans playerTeam, alors il est dans enemyTeam et c'est donc au tour d'enemyTeam de jouer
            } else {
                // Je vérifie si le battler de enemyTeam est toujours vivant
                if (!firstAttacker.isDead()) {
                    // Si oui, il attaque l'équipe playerTeam
                    firstAttacker.attack(playerTeam);
                } else {
                    // Sinon, je détermine le nouvel attacker de enemyTeam
                    Battler nextAttacker = getAttacker(enemyTeam.battlers);
                    // Et ce nouveau battler de enemyTeam attaque playerTeam
                    nextAttacker.attack(playerTeam);
                }
            }

            // S'il n'y a pas d'équipe gagnante, on continue
            if (getWinnerTeam() == null) {
                // Je dois déterminer dans quelle équipe se trouvait celui qui a attaqué en premier
                // Pour que ça soit au tour de l'autre équipe de jouer

                // Si le premier attaquant était dans l'équipe playerTeam, alors, c'est au tour de enemyTeam
                if (playerTeam.battlers.contains(firstAttacker)) {
                    // Je vérifie si le battler avec le plus d'ini chez enemyTeam est toujours vivant
                    if (!enemyBattlerWithMostInitiative.isDead()) {
                        // Si oui, il attaque l'équipe playerTeam
                        enemyBattlerWithMostInitiative.attack(playerTeam);
                    } else {
                        // Sinon, je détermine le nouveau battler avec le plus d'ini de l'équipe enemyTeam
                        Battler nextAttacker = getAttacker(enemyTeam.battlers);
                        // Et ce nouveau battler attaque playerTeam
                        nextAttacker.attack(playerTeam);
                    }

                    // Sinon (= si firstAttacker était dans enemyTeam)
                } else {
                    // Je vérifie si le battler avec le plus d'ini dans playerTeam est toujours vivant
                    if (!playerBattlerWithMostInitiative.isDead()) {
                        // Si oui, il attaque l'équipe enemyTeam
                        playerBattlerWithMostInitiative.attack(enemyTeam);
                    } else {
                        // Sinon, je détermine le nouveau battler de l'équipe playerTeam
                        Battler nextAttacker = getAttacker(playerTeam.battlers);
                        // Et ce nouveau battler attaque enemyTeam
                        nextAttacker.attack(enemyTeam);
                    }
                }
            }
            System.out.println("\n ------------------ \n");
        }
    }

    // TODO: add message announcing winner team
    // Méthode pour déterminer l'équipe gagnante
    public Team getWinnerTeam() {
        return teams.stream()
                .filter((Team::isLoser))
                .findFirst()
                .orElse(null);
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
}
