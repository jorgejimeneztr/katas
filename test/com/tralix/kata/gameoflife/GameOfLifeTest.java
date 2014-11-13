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
        assertEquals(DEAD, game.nextOffspring(ALIVE, 0));
        assertEquals(DEAD, game.nextOffspring(ALIVE, 1));
    }

    @Test
    public void givenAliveCellWithTwoOrThreeNeighbors_returnAlive() throws Exception {
        assertEquals(ALIVE, game.nextOffspring(ALIVE, 2));
        assertEquals(ALIVE, game.nextOffspring(1, 3));
    }

    @Test
    public void givenAliveCellWithFourOrMoreNeighbors_retrunDead() throws Exception {
        assertEquals(DEAD, game.nextOffspring(ALIVE, 4));
        assertEquals(DEAD, game.nextOffspring(ALIVE, 5));
    }

    @Test
    public void givenDeadCellWithNeighborsDifrentThanThree_retrunDead() throws Exception {
        assertEquals(DEAD, game.nextOffspring(DEAD, 0));
        assertEquals(DEAD, game.nextOffspring(DEAD, 2));
        assertEquals(DEAD, game.nextOffspring(DEAD, 4));
    }

    @Test
    public void givenDeadCellWithThreeNeighbors_retrunAlive() throws Exception {
        assertEquals(ALIVE, game.nextOffspring(DEAD, 3));
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
}
