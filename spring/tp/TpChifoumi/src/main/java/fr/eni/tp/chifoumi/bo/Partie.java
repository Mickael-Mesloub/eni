package fr.eni.tp.chifoumi.bo;

import java.util.Objects;

public class Partie {
    private String playerChoice;
    private String serverChoice;
    private String result;

    public Partie(String playerChoice, String serverChoice, String result) {
        this.playerChoice = playerChoice;
        this.serverChoice = serverChoice;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "playerChoice=" + playerChoice +
                ", serverChoice=" + serverChoice +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partie partie = (Partie) o;
        return Objects.equals(getPlayerChoice(), partie.getPlayerChoice()) && Objects.equals(getServerChoice(), partie.getServerChoice()) && Objects.equals(geResult(), partie.geResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerChoice(), getServerChoice(), geResult());
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

    public String geResult() {
        return result;
    }

    public void seResult(String result) {
        this.result = result;
    }
}
