package group9.UnitTests;

import group9.Piccollage;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PiccollageTests {
    PicCollageTest app;

    @Before
    public void init() throws Exception {
        app = new PicCollageTest();
    }

    @Test
    public void testGenerateCollagesFailNoImages() {
        assertEquals(0, app.getUnalteredBufferedImages().size());
        app.generateCollages();
        assertEquals(0, app.getGeneratedCollages().size());
    }

    @Test
    public void testGenerateCollagesSuccess1Image() {
        app.getUnalteredBufferedImages().add(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
        assertEquals(1, app.getUnalteredBufferedImages().size());
        app.generateCollages();
        assertTrue(app.getGeneratedCollages().size() > 0);
    }

    @Test
    public void testGenerateCollagesSuccess3Images() {
        for (int i = 0; i < 3; ++i)
            app.getUnalteredBufferedImages().add(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
        assertEquals(3, app.getUnalteredBufferedImages().size());
        app.generateCollages();
        assertTrue(app.getGeneratedCollages().size() > 0);
    }

    @Test
    public void testGenerateCollageClearsCollages() {
        testGenerateCollagesSuccess3Images();
        app.getBufferedImages().clear();
        app.generateCollages();
        assertEquals(0, app.getGeneratedCollages().size());
    }

    public static class PicCollageTest extends Piccollage {
        PicCollageTest() throws IOException {
            super();
        }

        public ArrayList<BufferedImage> getUnalteredBufferedImages() {
            return bufferedImages;
        }
    }
}