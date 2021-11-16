package group9.UnitTests;

import group9.CollageGenerators.GridCollageGenerator_PH;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GridCollageGenerator_PHTest {
    @Test
    public void testImage_Smaller_than_AspectRatio() //test doesn't work because of rounding errors
    {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("cat.jpg"));

            double aspectRatio = (float) 1920 / 1080;
            BigDecimal bd1 = new BigDecimal(Double.toString(aspectRatio));
            bd1 = bd1.setScale(4, RoundingMode.HALF_UP);
            BufferedImage img2 = GridCollageGenerator_PH.fixImage(img, aspectRatio, 0, 0);
            double targetAR = (float) img2.getWidth() / img2.getHeight();
            BigDecimal bd2 = new BigDecimal(Double.toString(targetAR));
            bd2 = bd2.setScale(4, RoundingMode.HALF_UP);
            System.out.println(img2.getData());
            assert (bd1.doubleValue() == bd2.doubleValue());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testNumberOfImages() throws IOException {
        List<BufferedImage> aux = createTestList();
        GridCollageGenerator_PH grid = new GridCollageGenerator_PH(aux,
                1920, 1080, 0, 0);

        assert(grid.getListSize()%2 == 0);
    }

    @Test
    public void testNumberOfCols() throws IOException {

        List<BufferedImage> aux = createTestList();
        GridCollageGenerator_PH grid = new GridCollageGenerator_PH(aux,
                1920, 1080, 0, 0, 1);
        assert(grid.getNUM_COLS() == 8);

        grid = new GridCollageGenerator_PH(aux,
                1920, 1080, 0, 0, 3);
        assert(grid.getNUM_COLS() == 2);

        grid = new GridCollageGenerator_PH(aux,
                1920, 1080, 0, 0, 4);
        assert(grid.getNUM_COLS() == 2);
    }

    public List<BufferedImage> createTestList() throws IOException {
        List<BufferedImage> aux = new ArrayList<>();
        for(int i=0; i < 9; i++)
        {
            BufferedImage img = ImageIO.read(getClass().getResource("cat.jpg"));
            aux.add(img);
        }


        return aux;
    }
}