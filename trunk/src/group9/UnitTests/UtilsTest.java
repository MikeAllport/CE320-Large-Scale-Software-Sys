package group9.UnitTests;

import group9.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void resizeImage() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("trunk/src/group9/UnitTests/unicorn.jpg"));
            BufferedImage newImage = Utils.resizeImage(originalImage,500,400);
            Assert.assertEquals(500,newImage.getWidth());
            Assert.assertEquals(400,newImage.getHeight());
            Assert.assertNotEquals(newImage,originalImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rescaleIcon() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("trunk/src/group9/UnitTests/unicorn.jpg"));
            BufferedImage newImage = Utils.rescaleIcon(originalImage,500,400);
            Assert.assertNotEquals(originalImage.getHeight(),newImage.getHeight());
            Assert.assertNotEquals(originalImage.getWidth(),newImage.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}