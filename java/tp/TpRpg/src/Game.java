import java.util.ArrayList;
import java.util.List;

public class Game {
    public Team playerTeam;
    public Team enemyTeam;
    ArrayList<Team> teams;

    public Game(Team playerTeam, Team enemyTeam) {
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        teams = new ArrayList<Team>(List.of(playerTeam, enemyTeam));
    }

    // TODO: add initiative system : player with the highest amount of initiative strikes first
    // TODO: add random stats (hp, power)
    // TODO: add dealing random attack damage


    // Méthode pour lancer une partie
    public void play() {

        System.out.println(String.format("------------------ \n\n Game ready to start! Let's FIGHT! \uD83E\uDD4A \n\n ------------------ \n\n"));

        // Tant qu'aucune team n'est déclarée vainqueur, on continue
        while (getWinnerTeam() == null) {

            // TODO: replace with battler with highest initiative amount
            // Instances des combattants : on récupère le premier de la liste de chaque équipe
            Battler playerTeamBattler = playerTeam.battlers.stream().findFirst().orElse(null);
            Battler enemyTeamBattler = enemyTeam.battlers.stream().findFirst().orElse(null);

            // Si on ne trouve aucun combattant, fin de partie
            if (playerTeamBattler == null || enemyTeamBattler == null) return;

            // On vérifie que le joueur peut attaquer (= qu'il n'est pas mort et que son ennemi non plus). Si OK, attaque
            if(playerTeamBattler.canAttack(enemyTeamBattler)) {
                playerTeamBattler.attack(enemyTeam);
            }

            // Cf au-dessus, si l'ennemi peut attaquer le joueur, il attaque
            if(enemyTeamBattler.canAttack(playerTeamBattler)) {
                enemyTeamBattler.attack(playerTeam);
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
