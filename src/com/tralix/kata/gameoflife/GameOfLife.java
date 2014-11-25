package com.tralix.kata.gameoflife;

import java.util.HashMap;
import java.util.Map;

public class GameOfLife {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    public int statusInOffspring(final int status, final int neighbors) {
        return getStatus(generateMap(status), neighbors);
    }

    private int getStatus(final Map<Integer, Integer> map, final int neighbors) {
        return map.get(neighbors) == null ? DEAD : map.get(neighbors);
    }

    private Map<Integer, Integer> generateMap(final int status) {
        Map<Integer, Integer> offspringRules = new HashMap<Integer, Integer>();
        offspringRules.put(3, ALIVE);
        offspringRules.put(2, status);
        return offspringRules;
    }

    public int calculateNeigbors(final int[][] grid, final int x, final int y) {
        return existIn(grid, x - 1, y - 1) + existIn(grid, x - 1, y) + existIn(grid, x - 1, y + 1)
                + existIn(grid, x, y - 1) + existIn(grid, x, y + 1) + existIn(grid, x + 1, y - 1)
                + existIn(grid, x + 1, y) + existIn(grid, x + 1, y + 1);
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
        fillOffspring(grid, offspring);
        return offspring;
    }

    private void fillOffspring(final int[][] grid, final int[][] offspring) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                offspring[i][j] = statusInOffspring(grid[i][j], calculateNeigbors(grid, i, j));
            }
        }
    }
}
