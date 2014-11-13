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
    public void givenLiveCellWithNoNeighbors_ret() throws Exception {
        int result = game.nextOffspring(1, 0);
        assertEquals(GameOfLife.DEAD, result);
    }
}
