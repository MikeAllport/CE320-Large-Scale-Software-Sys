package group9.utils;

import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {
    public static BufferedImage resizeImage(BufferedImage inputImage, int targetWidth, int targetHeight) {
        Image output = inputImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputBuff = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputBuff.getGraphics().drawImage(output, 0, 0, null);
        return outputBuff;
    }

    public static BufferedImage rescaleIcon(BufferedImage toBeScaled, int width, int height) {
        return Scalr.resize(toBeScaled, Scalr.Method.QUALITY, width, height, Scalr.OP_ANTIALIAS);
    }
}