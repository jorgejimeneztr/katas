package com.tralix.kata.gameoflife;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTest {

    private GameOfLife game;

    @Before
    public void setUp() throws Exception {
        game = new GameOfLife();
    }

    @Test
    public void givenLiveCellWithNoOrOneNeighbors_returnDead() throws Exception {
        assertEquals(GameOfLife.DEAD, game.nextOffspring(1, 0));
        assertEquals(GameOfLife.DEAD, game.nextOffspring(1, 1));
    }

    @Test
    public void givenAliveCellWithTwoOrThreeNeighbors_returnAlive() throws Exception {
        assertEquals(GameOfLife.ALIVE, game.nextOffspring(1, 2));
        assertEquals(GameOfLife.ALIVE, game.nextOffspring(1, 3));

    }
}
