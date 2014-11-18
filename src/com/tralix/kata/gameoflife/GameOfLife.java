package com.tralix.kata.gameoflife;

public class GameOfLife {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    public int statusInOffspring(final int status, final int neighbors) {
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
                neighbors += existIn(grid, i, j);
            }
        }
        return neighbors;
    }

    private int existIn(final int[][] grid, final int x, final int y) {
        if (outOfRange(grid, x, y)) {
            return 0;
        } else {
            return grid[x][y];
        }
    }

    private boolean outOfRange(final int[][] grid, final int x, final int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length;
    }

    public int[][] nextOffspring(final int[][] grid) {
        int[][] offspring = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                offspring[i][j] = statusInOffspring(grid[i][j], calculateNeigbors(grid, i, j));
            }
        }
        return offspring;
    }
}
