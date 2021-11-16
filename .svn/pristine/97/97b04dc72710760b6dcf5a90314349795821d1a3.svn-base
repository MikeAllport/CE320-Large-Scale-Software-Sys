package group9.GridCollageGenerator;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

public class CollageImage {

    private final int posX;
    private final int posY;
    private final int width;
    private final int height;
    private final BufferedImage image;
    private boolean greyscale = false;

    public CollageImage(BufferedImage image, int x, int y, int width, int height) {
        this.image = image;
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        if (greyscale) return convertImageToBlackWhite(image);
        else return image;
    }

    public void setGreyscale(boolean greyscale) {
        this.greyscale = greyscale;
    }

    private BufferedImage convertImageToBlackWhite(BufferedImage image) {
        BufferedImageOp bufferedImageOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        BufferedImage convertedImage = bufferedImageOp.filter(image, null);
        convertedImage.createGraphics();
        return convertedImage;
    }
}
