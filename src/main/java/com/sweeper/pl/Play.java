package com.sweeper.pl;

public class Play {
    public static void main(String[] args) {

        String mineField = "*...\n..*.\n....";

        Game game = new Game(mineField);

        game.setMineField();
        game.setMineField(mineField);
        System.out.println(game.getHintField());
    }
}
