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
}
