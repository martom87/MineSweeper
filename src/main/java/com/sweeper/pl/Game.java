package com.sweeper.pl;

import java.util.Arrays;

public class Game implements MineSweeper {

    private String mineField;
    private int[][] hintField;
    private boolean[][] mines;

    public Game(String mineField) {
        super();
        this.mineField = mineField;
    }

    public void setMineField() {
        mineField.split("\\n");
        if (!mineField.matches("[*.]+[*.\n]*")) {
            throw new IllegalArgumentException();
        }
        System.out.println("MINE FIELD:");
        System.out.println("------------------------------");
        System.out.println(mineField);
    }

    public void setMineField(String mineField) {
        String[] fieldLines = mineField.split("\\n");
        for (int i = 1; i != fieldLines.length; i++) {
            if (fieldLines[i].length() != fieldLines[0].length())
                throw new IllegalArgumentException();
        }

        mines = new boolean[fieldLines.length][fieldLines[0].length()];
        for (int i = 0; i < fieldLines.length; i++) {
            for (int j = 0; j < fieldLines[0].length(); j++) {
                if (fieldLines[i].charAt(j) == '*')
                    mines[i][j] = true;
                else
                    mines[i][j] = false;

            }

        }
        System.out.println("------------------------------");
        System.out.println("WHERE ARE THE MINES ARRAY:");
        System.out.println("------------------------------");
        System.out.println(Arrays.deepToString(mines).replace("[", "").replace("]", ""));
        createHintField();
        System.out.println("------------------------------");
    }

    private void createHintField() {
        hintField = new int[mines.length][mines[0].length];
        int rows = mines.length;
        int columns = mines[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int iNeibourghs = i - 1; iNeibourghs <= i + 1; iNeibourghs++) {
                    for (int jNeibourghs = j - 1; jNeibourghs <= j + 1; jNeibourghs++) {

                        if (mines[i][j] && iNeibourghs >= 0 && iNeibourghs < rows && jNeibourghs >= 0
                                && jNeibourghs < columns)
                            hintField[iNeibourghs][jNeibourghs]++;
                    }
                }
            }

        }

    }


    public String getHintField() throws IllegalStateException {
        int rows = mines.length;
        int columns = mines[0].length;
        String hintsOut = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mines[i][j])
                    hintsOut += "*";
                else
                    hintsOut += hintField[i][j];
            }
            if (i < rows - 1)
                hintsOut += "\n";
        }

        System.out.println("HINT FIELD");
        System.out.println("------------------------------");
        return hintsOut;

    }
}
