import java.util.ArrayList;
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

    // TODO: add initiative system : player with the highest amount of initiative strikes first
    // TODO: add random stats (hp, power)
    // TODO: add dealing random attack damage

    /**
     * Système d'initiative
     * Chaque combattant a un montant d'initiative
     * Avant d'attaquer, je dois vérifier QUI attaque en premier
     * Dans la team 1 je récupère celui qui a le plus d'initiative
     * Idem dans la team 2
     * Puis je compare celui qui a le plus d'initiative entre ces 2 combattants
     * Celui qui a le plus est celui qui attaque en premier
     * Je dois aussi vérifier quelle équipe a attaqué en dernier
     * Si c'est l'équipe A, alors c'est au tour de l'équipe B d'attaquer
     */

    // Méthode pour lancer une partie
    public void play() {

        System.out.println(String.format("------------------ \n\n Game ready to start! Let's FIGHT! \uD83E\uDD4A \n\n ------------------ \n\n"));

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {

            // Pour chaque équipe, on récupère le battler avec le plus d'initiative
            Battler playerBattlerWithMostInitiative = playerTeam.getBattlerWithMostInitiative();
            Battler enemyBattlerWithMostInitiative = enemyTeam.getBattlerWithMostInitiative();

            // On vérifie que le joueur peut attaquer (= qu'il n'est pas mort et que son ennemi non plus). Si OK, attaque
            if(playerBattlerWithMostInitiative.canAttack(enemyBattlerWithMostInitiative)) {
                playerBattlerWithMostInitiative.attack(enemyTeam);
            }

            // Cf au-dessus, si l'ennemi peut attaquer le joueur, il attaque
            if(enemyBattlerWithMostInitiative.canAttack(playerBattlerWithMostInitiative)) {
                enemyBattlerWithMostInitiative.attack(playerTeam);
            }

            System.out.println("\n ------------------ \n");
        }

    }


    // TODO: refactor ?
    // Méthode pour déterminer l'équipe gagnante
    public Team getWinnerTeam() {
        Team winner = null;

        // Si l'équipe du joueur n'a plus de combattants (isLoser vaut true), l'équipe ennemie gagne
        if (playerTeam.isLoser()) {
            winner = enemyTeam;
            System.out.println(String.format("🏆 %s won! \n\n ------------------ \n\n Game is over! \n\n ------------------", enemyTeam.name));
            return winner;
            // Si l'équipe ennemie n'a plus de combattants, l'équipe du joueur gagne
        } else if (enemyTeam.isLoser()) {
            winner = playerTeam;
            System.out.println(String.format("🏆 %s won! \n\n ------------------ \n\n Game is over! \n\n ------------------", playerTeam.name));
            return winner;
        }

        // Si aucune des 2 équipes n'est déclarée gagnante, winner vaut null pour que la partie continue
        return winner;
    }

}
