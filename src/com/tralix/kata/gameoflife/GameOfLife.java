package com.tralix.kata.gameoflife;

public class GameOfLife {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    public int nextOffspring(final int status, final int neighbors) {
        if (status == ALIVE) {
            if (neighbors > 1) {
                return ALIVE;
            }
        }
        return DEAD;
    }
}
