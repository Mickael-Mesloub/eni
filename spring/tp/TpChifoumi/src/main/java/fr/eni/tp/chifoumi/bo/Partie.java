package fr.eni.tp.chifoumi.bo;

import fr.eni.tp.chifoumi.controller.GameResult;

import java.util.Objects;

public class Partie {
    private String playerChoice;
    private String serverChoice;
    private GameResult result;

    public Partie(String playerChoice, String serverChoice, GameResult result) {
        this.playerChoice = playerChoice;
        this.serverChoice = serverChoice;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partie partie = (Partie) o;
        return Objects.equals(getPlayerChoice(), partie.getPlayerChoice()) && Objects.equals(getServerChoice(), partie.getServerChoice()) && result == partie.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerChoice(), getServerChoice(), getResult());
    }

    public String getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(String playerChoice) {
        this.playerChoice = playerChoice;
    }

    public String getServerChoice() {
        return serverChoice;
    }

    public void setServerChoice(String serverChoice) {
        this.serverChoice = serverChoice;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }
}
