package group9.UnitTests;

import group9.CollageWindow.CollagePage;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.Assert.*;

public class PrevNextCollageTest {
    PiccollageTests.PicCollageTest testApp;
    CollagePage collagePage;

    @Before
    public void init() throws IOException {
        testApp = new PiccollageTests.PicCollageTest();
        collagePage = new CollagePage(testApp);
        collagePage.getPreviewCollageJPanel().setSize(new Dimension(400, 400));
    }

    @Test
    public void testNoImagesToInsert() {
        collagePage.setCollagePreviewImage(1);
        assertNull(collagePage.getPreviewCollageJPanel().getImage());
    }

    public int populateCollagesGetLength() {
        for (int i = 0; i < 3; ++i)
            testApp.getUnalteredBufferedImages().add(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
        testApp.generateCollages();
        return testApp.getGeneratedCollages().size();
    }

    @Test
    public void testPreviousAt0boundry() {
        int collageListLength = populateCollagesGetLength();
        assertTrue(collageListLength > 0);
        assertEquals(0, collagePage.getCollagePreviewIndex());
        collagePage.setCollagePreviewImage(-1);
        assertEquals(collageListLength - 1, collagePage.getCollagePreviewIndex());
    }

    @Test
    public void testNextIncrements() {
        populateCollagesGetLength();
        assertEquals(0, collagePage.getCollagePreviewIndex());
        collagePage.setCollagePreviewImage(1);
        assertEquals(1, collagePage.getCollagePreviewIndex());
    }

    @Test
    public void testNextAtBoundry() {
        int collageListLength = populateCollagesGetLength();
        int i = -1;
        while (++i < collageListLength - 1)
            collagePage.setCollagePreviewImage(1);
        assertEquals(collageListLength - 1, collagePage.getCollagePreviewIndex());
        collagePage.setCollagePreviewImage(1);
        assertEquals(0, collagePage.getCollagePreviewIndex());
    }
}