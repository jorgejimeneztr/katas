package com.tralix.kata.gameoflife;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GameOfLifeTest {

    @Test
    public void setUp() throws Exception {
        GameOfLife game = new GameOfLife();
        assertNotNull(game);
    }

}
