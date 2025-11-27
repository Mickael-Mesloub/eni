package fr.eni.tp.chifoumi.controller;

public enum GameResult {
    WON("Gagné"), LOST("Perdu"), DRAW("Égalité");

    private String label;

    GameResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
