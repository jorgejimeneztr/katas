package com.tralix.kata.gameoflife.nocycles;

public class LifeNoCycles {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    public int getStatusByNeighbors(final int status, final int neighbors) {
        switch (neighbors) {
        case 2:
            return status;
        case 3:
            return ALIVE;
        default:
            return DEAD;
        }
    }

    public int getNeighbors(final int[][] space, final int x, final int y) {
        return addNeighbors(space, x, y);
    }

    private int addNeighbors(final int[][] space, final int x, final int y) {
        return addNeighbor(space, x - 1, y - 1) + addNeighbor(space, x - 1, y)
                + addNeighbor(space, x - 1, y + 1) + addNeighbor(space, x, y - 1)
                + addNeighbor(space, x, y + 1) + addNeighbor(space, x + 1, y - 1)
                + addNeighbor(space, x + 1, y) + addNeighbor(space, x + 1, y + 1);
    }

    private int addNeighbor(final int[][] space, final int x, final int y) {
        if (inAllowedSpace(space, x, y)) {
            return space[x][y];
        }
        return 0;
    }

    private boolean inAllowedSpace(final int[][] space, final int x, final int y) {
        return x < space.length && x > -1 && y < space[0].length && y > -1;
    }

    public int[][] nextOffspring(final int[][] space) {
        int[][] offspring = new int[space.length][space[0].length];
        fillOffspringFromStart(space, offspring);
        return offspring;
    }

    private void fillOffspringFromStart(final int[][] space, final int[][] offspring) {
        fillOffspring(space, offspring, 0, 0);
    }

    private void fillOffspring(final int[][] space, final int[][] offspring, final int x, final int y) {
        if (hasMoreRows(space, y)) {
            fillNextCell(space, offspring, x, y);
        }
    }

    private void fillNextCell(final int[][] space, final int[][] offspring, final int x, final int y) {
        if (junpRequired(space, x)) {
            fillOffspring(space, offspring, 0, y + 1);
        } else {
            offspring[x][y] = getStatusByNeighbors(space[x][y], getNeighbors(space, x, y));
            fillOffspring(space, offspring, x + 1, y);
        }
    }

    private boolean hasMoreRows(final int[][] space, final int y) {
        return y < space[0].length;
    }

    private boolean junpRequired(final int[][] space, final int x) {
        return x >= space.length;
    }
}
