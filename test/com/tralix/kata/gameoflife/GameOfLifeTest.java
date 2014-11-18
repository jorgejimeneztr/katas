package com.tralix.kata.gameoflife;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTest {

    private static final int ALIVE = GameOfLife.ALIVE;
    private static final int DEAD = GameOfLife.DEAD;
    private GameOfLife game;

    @Before
    public void setUp() throws Exception {
        game = new GameOfLife();
    }

    @Test
    public void givenLiveCellWithNoOrOneNeighbors_returnDead() throws Exception {
        assertEquals(DEAD, game.statusInOffspring(ALIVE, 0));
        assertEquals(DEAD, game.statusInOffspring(ALIVE, 1));
    }

    @Test
    public void givenAliveCellWithTwoOrThreeNeighbors_returnAlive() throws Exception {
        assertEquals(ALIVE, game.statusInOffspring(ALIVE, 2));
        assertEquals(ALIVE, game.statusInOffspring(1, 3));
    }

    @Test
    public void givenAliveCellWithFourOrMoreNeighbors_retrunDead() throws Exception {
        assertEquals(DEAD, game.statusInOffspring(ALIVE, 4));
        assertEquals(DEAD, game.statusInOffspring(ALIVE, 5));
    }

    @Test
    public void givenDeadCellWithNeighborsDifrentThanThree_retrunDead() throws Exception {
        assertEquals(DEAD, game.statusInOffspring(DEAD, 0));
        assertEquals(DEAD, game.statusInOffspring(DEAD, 2));
        assertEquals(DEAD, game.statusInOffspring(DEAD, 4));
    }

    @Test
    public void givenDeadCellWithThreeNeighbors_retrunAlive() throws Exception {
        assertEquals(ALIVE, game.statusInOffspring(DEAD, 3));
    }

    @Test
    public void givenGread_CalculateNeigbors() throws Exception {
        int[][] grid = generateAllAliveGrid();
        assertEquals(8, game.calculateNeigbors(grid, 1, 1));
        assertEquals(3, game.calculateNeigbors(grid, 0, 0));
        assertEquals(5, game.calculateNeigbors(grid, 0, 1));
        int[][] gridAllDead = new int[3][3];
        assertEquals(0, game.calculateNeigbors(gridAllDead, 1, 1));
        assertEquals(0, game.calculateNeigbors(gridAllDead, 0, 0));
        assertEquals(0, game.calculateNeigbors(gridAllDead, 0, 1));
    }

    private int[][] generateAllAliveGrid() {
        return new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    }

    @Test
    public void givenGrid_CalculateNextOffspring() throws Exception {
        int[][] grid = generateAllAliveGrid();
        int[][] expectedOffspring = new int[][] { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };
        assertOffspring(grid, expectedOffspring);
    }

    private void assertOffspring(final int[][] grid, final int[][] expectedOffspring) {
        int[][] offspring = game.nextOffspring(grid);
        for (int i = 0; i < expectedOffspring.length; i++) {
            for (int j = 0; j < expectedOffspring[0].length; j++) {
                assertEquals(expectedOffspring[i][j], offspring[i][j]);
            }
        }
    }

    @Test
    public void givenExampleGames_returnResults() throws Exception {
        int[][] empty = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        int[][] block = new int[][] { { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        int[][] boat = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 1, 0, 1, 0 },
                { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 } };
        // same
        assertOffspring(empty, empty);
        assertOffspring(block, block);
        assertOffspring(boat, boat);
        int[][] beacon = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0 } };
        int[][] beacon2 = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0 } };
        int[][] toad = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
        int[][] toad2 = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
        // cyclical
        assertOffspring(beacon, beacon2);
        assertOffspring(beacon2, beacon);
        assertOffspring(toad, toad2);
        assertOffspring(toad2, toad);
    }
}
