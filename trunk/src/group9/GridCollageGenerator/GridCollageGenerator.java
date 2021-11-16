package group9.GridCollageGenerator;

import group9.RandomCollageGenerator.ICollageGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GridCollageGenerator implements ICollageGenerator {
    private final ArrayList<BufferedImage> images;
    private final Collage collage;
    private final GridCalculations gridCalculations = new GridCalculations();

    public GridCollageGenerator(ArrayList<BufferedImage> images) {
        this.images = images;
        collage = generateCollage();
        collage.draw();
    }

    //code to test this Collage Generator
    public static void main(String[] args) throws IOException {
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));
        images.add(ImageIO.read(new File("trunk/resources/testImages/dog.jpg")));

        new GridCollageGenerator(images);

    }

    public Collage getCollage() {
        return collage;
    }

    public int getLargestWidth(ArrayList<BufferedImage> imageCollection) {
        int largestWidth = 0;
        for (BufferedImage image : imageCollection) {
            int width = image.getWidth();
            if (width > largestWidth) largestWidth = width;
        }
        return largestWidth;
    }

    public int getLargestHeight(ArrayList<BufferedImage> imageCollection) {
        int largestHeight = 0;
        for (BufferedImage image : imageCollection) {
            int height = image.getHeight();
            if (height > largestHeight) largestHeight = height;
        }
        return largestHeight;
    }

    public int getSmallestWidth(ArrayList<BufferedImage> imageCollection) {
        int smallestWidth = Integer.MAX_VALUE;
        for (BufferedImage image : imageCollection) {
            int width = image.getWidth();
            if (width < smallestWidth) smallestWidth = width;
        }
        return smallestWidth;
    }

    public int getSmallestHeight(ArrayList<BufferedImage> imageCollection) {
        int smallestHeight = Integer.MAX_VALUE;
        for (BufferedImage image : imageCollection) {
            int height = image.getHeight();
            if (height < smallestHeight) smallestHeight = height;
        }
        return smallestHeight;
    }

    private Collage generateCollage() {
        int smallestWidth = getSmallestWidth(this.images);
        int smallestHeight = getSmallestHeight(this.images);

        ArrayList<Frame> frames = new ArrayList<>();

        for (BufferedImage image : images) {
            double imageWidth = image.getWidth();
            int numHorizontal = gridCalculations.calcNumHorizontal(imageWidth, smallestWidth);
            double imageHeight = image.getHeight();
            int numVertical = gridCalculations.calcNumVertical(imageHeight, smallestHeight);
            int[] frameBounds = gridCalculations.reduceFraction(numHorizontal, numVertical);
            frames.add(new Frame(frameBounds[0], frameBounds[1]));
        }

        int numFrames = getTotalFrames(frames);
        int[] gridShape = gridCalculations.getGridShape(numFrames);

        int collageWidth = smallestWidth * gridShape[1];
        int collageHeight = smallestHeight * gridShape[0];
        double scaleWidth = 1;
        double scaleHeight = 1;

        if (collageWidth > 2048) {
            scaleWidth = 2048 / (double) collageWidth;
            collageWidth = 2048;
        }

        if (collageHeight > 2048) {
            scaleHeight = 2048 / (double) collageHeight;
            collageHeight = 2048;
        }

        Collage collage = new Collage(collageWidth, collageHeight);

        int[][] imageLocations = new int[gridShape[0]][gridShape[1]];
        for (int[] row : imageLocations) Arrays.fill(row, -1);

        int index = 0;
        for (int i = 0; i < gridShape[0]; i++) {
            for (int j = 0; j < gridShape[1]; j++) {
                if (imageLocations[i][j] == -1) {
                    for (int[] row : imageLocations) System.out.println(Arrays.toString(row));
                    int spanWidth = frames.get(index).getWidth();
                    int spanHeight = frames.get(index).getHeight();
                    for (int a = 0; a < spanHeight; a++) {
                        for (int b = 0; b < spanWidth; b++) {
                            imageLocations[i + a][j + b] = index;
                        }
                    }
                    index++;
                }
            }
        }

        for (int[] row : imageLocations) System.out.println(Arrays.toString(row));

        ArrayList<Integer> visited = new ArrayList<>();
        int frameWidth = (int) (smallestWidth * scaleWidth);
        int frameHeight = (int) (smallestHeight * scaleHeight);
        for (int i = 0; i < gridShape[0]; i++) {
            for (int j = 0; j < gridShape[1]; j++) {
                index = imageLocations[i][j];
                if (!visited.contains(index)) {
                    visited.add(index);
                    BufferedImage image = images.get(index);
                    int imageWidth = frameWidth * frames.get(index).getWidth();
                    int imageHeight = frameHeight * frames.get(index).getHeight();
                    collage.addImage(image, frameWidth * j, frameHeight * i, imageWidth, imageHeight);
                }
            }
        }


        return collage;
    }
    private int getTotalFrames(ArrayList<Frame> frames) {
        int totalFrames = 0;
        for (Frame frame : frames) totalFrames += frame.getWidth() * frame.getHeight();

        return totalFrames;
    }
}