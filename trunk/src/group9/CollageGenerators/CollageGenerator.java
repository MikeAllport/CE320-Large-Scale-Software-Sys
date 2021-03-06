package group9.CollageGenerators;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CollageGenerator {
    public final File collageImagesFolder = new File("trunk/resources/testImages/");
    public final ArrayList<File> collageImages = new ArrayList<>();
    public final ArrayList<BufferedImage> collageImagesScaled = new ArrayList<>();

    public CollageGenerator() {
        addAllImagesOfCollageIntoArray();
        rescaleImage();
        drawAllImagesOntoCollageFrame();
        File generated_Collage = new File("trunk/resources/testCollages/generated_collage.jpg");
        try {
            ImageIO.write(drawAllImagesOntoCollageFrame(), "jpg", generated_Collage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CollageGenerator();
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    public void addAllImagesOfCollageIntoArray() {
        File[] listOfFilesInCollageImagesFolder = collageImagesFolder.listFiles();
        if (listOfFilesInCollageImagesFolder != null) {
            for (File fileIn : listOfFilesInCollageImagesFolder) {
                String fileName = fileIn.toString();
                int index = fileName.lastIndexOf(".");
                String extension = fileName.substring(index + 1);
                System.out.println(fileIn);
                if (fileIn.isFile() && (extension.equals("jpg") || extension.equals("png"))) {
                    collageImages.add(fileIn);
                }
            }
        }
    }

    public void rescaleImage() {
        for (File imageIn : collageImages) {
            try {
                BufferedImage original = ImageIO.read(imageIn);
                System.out.println(original);
                BufferedImage scaledImg = Scalr.resize(original, Scalr.Method.QUALITY, 450, 450, Scalr.OP_ANTIALIAS);
                collageImagesScaled.add(scaledImg);

            } catch (IOException e) {
                System.out.println("Exception occurred : " + e);
            }
        }
    }

    public BufferedImage drawAllImagesOntoCollageFrame() {
        int xCoordinate = 0;
        int yCoordinate = 0;
        int counter = 0;
        int maxWidthOfTwoPictures = 0;
        int maxHeightOfTwoPictures = 0;
        int frameHeightDefiner = 0;
        int frameWidthDefiner = 0;
        int widthOfFirstPicInPair = 0;
        int widthOfSecondPicInPair;
        int heightOfFirstPicInPair = 0;
        int heightOfSecondPicInPair;
        int arraySize = collageImagesScaled.size();
        for (BufferedImage imageIn : collageImagesScaled) {

            if (counter % 2 == 0) {
                widthOfFirstPicInPair = imageIn.getWidth();
                heightOfFirstPicInPair = imageIn.getHeight();
                if (counter == (arraySize - 1)) {
                    frameWidthDefiner += widthOfFirstPicInPair;
                }
            }

            if (counter % 2 == 1) {
                widthOfSecondPicInPair = imageIn.getWidth();
                heightOfSecondPicInPair = imageIn.getHeight();
                frameWidthDefiner += Math.max(widthOfSecondPicInPair, widthOfFirstPicInPair);
                maxHeightOfTwoPictures = heightOfFirstPicInPair + heightOfSecondPicInPair;
                widthOfFirstPicInPair = 0;
            }

            if (maxHeightOfTwoPictures > frameHeightDefiner) {
                frameHeightDefiner = maxHeightOfTwoPictures;
            }

            counter++;
        }

        counter = 0;

        BufferedImage collageFrame = new BufferedImage(frameWidthDefiner, frameHeightDefiner, BufferedImage.TYPE_INT_RGB);
        Graphics2D collage_creation = collageFrame.createGraphics();

        for (BufferedImage imageIn : collageImagesScaled) {
            collage_creation.drawImage(imageIn, xCoordinate, yCoordinate, null);
            yCoordinate += imageIn.getHeight();
            System.out.println(imageIn.getWidth());
            counter += 1;
            if (imageIn.getWidth() > maxWidthOfTwoPictures) {
                maxWidthOfTwoPictures = imageIn.getWidth();
            }
            if (counter == 2) {
                xCoordinate += maxWidthOfTwoPictures;
                yCoordinate = 0;
                maxWidthOfTwoPictures = 0;
                counter = 0;
            }
        }
        return collageFrame;
    }
}