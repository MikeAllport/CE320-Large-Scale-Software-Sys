package group9.GridCollageGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Collage {
    private static Color backgroundColor = Color.BLACK;
    private final int width;
    private final int height;
    private final ArrayList<CollageImage> collageImages;
    private final ArrayList<CollageText> collageTexts;
    private BufferedImage collage;
    private boolean greyscale = false;

    public Collage(int width, int height) {
        this.width = width;
        this.height = height;
        this.collageImages = new ArrayList<>();
        this.collageTexts = new ArrayList<>();
    }

    public static void setBackgroundColor(Color col) {
        backgroundColor = col;
    }

    public void addImage(BufferedImage image, int x, int y, int width, int height) {
        CollageImage collageImage = new CollageImage(image, x, y, width, height);
        collageImages.add(collageImage);
    }

    public void addText(String text, int x, int y, int size, Color color) {

        CollageText collageText = new CollageText(text, x, y, size, color);
        collageTexts.add(collageText);
    }

    public void draw() {
        Graphics2D g2 = setUpDraw();
        g2.dispose();
        if(greyscale) collage = convertImageToBlackWhite(collage);
    }

    private Graphics2D setUpDraw() {
        collage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = collage.createGraphics();
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, width, height);
        for (CollageImage collageImage : collageImages)
            g2.drawImage(collageImage.getImage(), collageImage.getPosX(), collageImage.getPosY(), collageImage.getWidth(), collageImage.getHeight(), null);
        if (collageTexts.size() > 0) {
            for (CollageText collageText : collageTexts) {
                g2.setColor(collageText.getColor());
                g2.setFont(new Font("TimesRoman", Font.PLAIN, collageText.getSize()));
                g2.drawString(collageText.getText(), collageText.getPosX(), collageText.getPosY());
            }
        }
        return g2;
    }

    public void draw(Point p) {
        Graphics2D g2 = setUpDraw();
        if(greyscale) collage = convertImageToBlackWhite(collage);
        CollageImage selected = getImageFromPoint(p);
        if(selected != null){
            g2.setColor(Color.BLUE);
            g2.drawRect(selected.getPosX(), selected.getPosY(), selected.getWidth(), selected.getHeight());
        }
        g2.dispose();
    }

    public CollageImage getImageFromPoint(Point p) {
        int posX;
        int posY;
        int width;
        int height;
        for (CollageImage collageImage : collageImages) {
            posX = collageImage.getPosX();
            posY = collageImage.getPosY();
            width = collageImage.getWidth();
            height = collageImage.getHeight();
            if(p.x > posX && p.x < posX+width && p.y > posY && p.y < posY + height) return collageImage;
        }
        return null;
    }

    public BufferedImage getCollageAsImage() {
        draw();
        return collage;
    }

    public BufferedImage getCollageAsImage(Point p) {
        draw(p);
        return collage;
    }

    public void save(String filename, String fileType) throws IOException {
        draw();
        File outfile = new File(filename + "."+fileType);
        ImageIO.write(collage, fileType, outfile);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
