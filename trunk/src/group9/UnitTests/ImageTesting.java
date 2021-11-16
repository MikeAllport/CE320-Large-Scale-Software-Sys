package group9.UnitTests;

import group9.ButtonHandling.ConversionToGreyscaleButtonHandler;
import group9.GridCollageGenerator.GridCollageGenerator;
import group9.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class ImageTesting {


    @Test
    public void testFileNotNull() {
        File kitten = new File("trunk\\resources\\testImages\\kitten.jpg");
        Assert.assertNotNull(kitten);
    }

    @Test
    public void testBufferedImageNotNull() {
        BufferedImage puppy = null;
        try {
            puppy = read(getClass().getResource("puppyTest.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(puppy);
    }


    @Test
    public void testRescaling() {
        BufferedImage puppy = null;
        try {
            puppy = read(getClass().getResource("puppyTest.jpg")); //original 256X256
        } catch (IOException e) {
            e.printStackTrace();
        }
        int targetWidth = 600;
        int targetHeight = 600;
        BufferedImage rescaledPuppy = Utils.rescaleIcon(puppy, targetWidth, targetHeight);
        Assert.assertEquals(targetHeight, rescaledPuppy.getHeight());
        Assert.assertEquals(targetWidth, rescaledPuppy.getWidth());
    }

    @Test
    public void testConvertingToBlackAndWhite() {
        BufferedImage puppy;
        BufferedImage puppyBlackWhite = null;
        try {
            puppy = read(getClass().getResource("puppyTest.jpg"));
            puppyBlackWhite = ConversionToGreyscaleButtonHandler.convertImageToBlackWhite(puppy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert puppyBlackWhite != null;
        Raster ras = puppyBlackWhite.getRaster();
        int elem = ras.getNumDataElements(); //if it returns 1 then greyscale,  if 3 then coloured image
        Assert.assertNotEquals(3, elem);
    }

    @Test
    public void testMaximumWidthAndHeight() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        BufferedImage dog;
        BufferedImage cat;
        BufferedImage unicorn;
        try {
            dog = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/dog.jpg"));
            cat = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/cat.jpg"));
            unicorn = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/unicorn.jpg"));
            images.add(dog);
            images.add(cat);
            images.add(unicorn);
            GridCollageGenerator collageGenerator = new GridCollageGenerator(images);
            Assert.assertEquals(976, collageGenerator.getLargestWidth(images));
            Assert.assertEquals(549, collageGenerator.getLargestHeight(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMinimumWidthAndHeight() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        BufferedImage dog;
        BufferedImage cat;
        BufferedImage unicorn;
        try {
            dog = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/dog.jpg"));
            cat = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/cat.jpg"));
            unicorn = ImageIO.read(new FileInputStream("trunk/src/group9/UnitTests/unicorn.jpg"));
            images.add(dog);
            images.add(cat);
            images.add(unicorn);
            GridCollageGenerator collageGenerator = new GridCollageGenerator(images);
            Assert.assertEquals(256, collageGenerator.getSmallestHeight(images));
            Assert.assertEquals(256, collageGenerator.getSmallestWidth(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}