package com.tralix.kata.gameoflife;

public class GameOfLife {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    public int nextOffspring(final int status, final int neighbors) {
        switch (neighbors) {
        case 2:
            return status;
        case 3:
            return ALIVE;
        default:
            return DEAD;
        }
    }

    public int calculateNeigbors(final int[][] grid, final int x, final int y) {
        int neighbors = 0 - grid[x][y];
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                neighbors += exitIn(grid, i, j);
            }
        }
        return neighbors;
    }

    private int exitIn(final int[][] grid, final int x, final int y) {
        if (outOfRange(grid, x, y)) {
            return 0;
        } else {
            return grid[x][y];
        }
    }

    private boolean outOfRange(final int[][] grid, final int x, final int y) {
        return x < 0 || x >= grid.length || y < 0 || x >= grid[0].length;
    }
}
