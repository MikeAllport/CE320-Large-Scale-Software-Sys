package group9.UnitTests;

import group9.RandomCollageGenerator.Bounds;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoundsUnitTest {
    group9.RandomCollageGenerator.Bounds bound1;
    group9.RandomCollageGenerator.Bounds bound2;

    @Before
    public void initTwoBounds() {
        bound1 = new Bounds(0, 100, 0, 100);
        bound2 = new Bounds(100, 200, 0, 100);
    }

    @Test
    public void dimensionValuesConstructor() {
        bound1 = new Bounds(0, 0, new Dimension(100, 100));
        assertEquals(100, bound1.getyPosition2());
        assertEquals(100, bound1.getxPosition2());
    }

    @Test
    public void normalizedPositionsAssignedCorrectly() {
        bound1 = new Bounds(110, 110, new Dimension(100, 100));
        assertEquals(100, bound1.getxNormalizedPosition1());
        assertEquals(200, bound1.getxNormalizedPosition2());
        assertEquals(100, bound1.getyNormalizedPosition1());
        assertEquals(200, bound1.getyNormalizedPosition2());
    }

    @Test
    public void noOverlapTest1() {
        assertFalse(bound1.isOverlap(bound2));
        assertFalse(bound1.isOverlapNormalized(bound2));
    }

    @Test
    public void noOverlapTest2() {
        bound2 = new group9.RandomCollageGenerator.Bounds(0, 100, 100, 200);
        assertFalse(bound1.isOverlap(bound2));
        assertFalse(bound1.isOverlapNormalized(bound2));
    }

    @Test
    public void isOverlapTest() {
        bound2 = new group9.RandomCollageGenerator.Bounds(99, 199, 0, 100);
        assertTrue(bound1.isOverlap(bound2));
        assertTrue(bound1.isOverlapNormalized(bound2));
    }

    @Test
    public void isOverlapTest2() {
        bound2 = new group9.RandomCollageGenerator.Bounds(0, 100, 99, 199);
        assertTrue(bound1.isOverlap(bound2));
        assertTrue(bound1.isOverlapNormalized(bound2));
    }
}