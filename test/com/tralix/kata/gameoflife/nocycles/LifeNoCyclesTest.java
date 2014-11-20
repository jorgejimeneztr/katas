package com.tralix.kata.gameoflife.nocycles;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LifeNoCyclesTest {

    private static final int ALIVE = LifeNoCycles.ALIVE;
    private static final int DEAD = LifeNoCycles.DEAD;
    private LifeNoCycles life;

    @Before
    public void setUp() throws Exception {
        life = new LifeNoCycles();
    }

    @Test
    public void givenRulesOfLife_returnCorrectStatus() throws Exception {
        assertEquals(DEAD, life.getStatusByNeighbors(ALIVE, 0));
        assertEquals(ALIVE, life.getStatusByNeighbors(ALIVE, 2));
        assertEquals(DEAD, life.getStatusByNeighbors(DEAD, 2));
        assertEquals(ALIVE, life.getStatusByNeighbors(ALIVE, 3));
        assertEquals(ALIVE, life.getStatusByNeighbors(DEAD, 3));
        assertEquals(DEAD, life.getStatusByNeighbors(ALIVE, 4));
    }

    @Test
    public void givenSpace_returnNeighbors() throws Exception {
        assertEquals(0, life.getNeighbors(new int[2][2], 0, 0));
        assertEquals(3, life.getNeighbors(new int[][] { { 1, 1 }, { 1, 1 } }, 0, 0));
        assertEquals(3, life.getNeighbors(new int[][] { { 0, 1 }, { 1, 1 } }, 0, 0));
        assertEquals(2, life.getNeighbors(new int[][] { { 0, 1 }, { 1, 0 } }, 0, 0));
        assertEquals(8, life.getNeighbors(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, 1, 1));
    }

    @Test
    public void givenOnlyFirstDead_offspring_returnAllAlive() throws Exception {
        assertOffspring(new int[][] { { 0, 1 }, { 1, 1 } }, new int[][] { { 1, 1 }, { 1, 1 } });
    }

    private void assertOffspring(final int[][] space, final int[][] expected) {
        int[][] offspring = life.nextOffspring(space);
        assertEquals(expected.length, offspring.length);
        assertEquals(expected[0].length, offspring[0].length);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], offspring[i][j]);
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
