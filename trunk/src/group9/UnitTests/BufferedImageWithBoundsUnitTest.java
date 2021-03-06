package group9.UnitTests;

import group9.RandomCollageGenerator.Bounds;
import group9.RandomCollageGenerator.BufferedImageWithBounds;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertTrue;

public class BufferedImageWithBoundsUnitTest {
    BufferedImageWithBounds imageWithBound1;
    BufferedImageWithBounds imageWithBound2;
    BufferedImage imageToTestWith;

    @Before
    public void init() {
        BufferedImage imageToTestWith = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Dimension size1 = new Dimension(100, 100);
        imageWithBound1 = new BufferedImageWithBounds(imageToTestWith, new Bounds(0, 0, size1));
    }

    @Test
    public void testCompareToImageSize1() {
        Dimension size2 = new Dimension(200, 200);
        imageWithBound2 = new BufferedImageWithBounds(imageToTestWith, new Bounds(0, 0, size2));
        assertTrue(imageWithBound1.compareTo(imageWithBound2) > 0);
        assertTrue(imageWithBound2.compareTo(imageWithBound1) < 0);
    }
}