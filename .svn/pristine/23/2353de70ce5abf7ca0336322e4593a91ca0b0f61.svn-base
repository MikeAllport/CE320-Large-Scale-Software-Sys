package group9.RandomCollageGenerator;

import group9.GridCollageGenerator.Collage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class CollageGeneratorBinaryTree implements ICollageGenerator {
    final boolean fixAspectRatio;
    private final Dimension FRAME_DIMENSIONS;
    private ArrayList<BufferedImage> inputImageList;
    private BinaryTreeBufferedImage generatedImageTree;

    public CollageGeneratorBinaryTree(ArrayList<BufferedImage> inputImageList, Dimension frameSize, boolean fixAspectRatio) {
        this.FRAME_DIMENSIONS = frameSize;
        this.fixAspectRatio = fixAspectRatio;
        copyImagesToLocal(inputImageList);
        generateImageTree();
    }

    private void copyImagesToLocal(ArrayList<BufferedImage> inputImageList) {
        this.inputImageList = new ArrayList<>();
        this.inputImageList.addAll(inputImageList);
    }

    private void generateImageTree() {
        ArrayList<BufferedImage> copyInputImageList = new ArrayList<>(this.inputImageList);
        this.generatedImageTree = new BinaryTreeBufferedImage(FRAME_DIMENSIONS);
        Random random = new Random();
        for (int howManyTimes = 0; howManyTimes < this.inputImageList.size(); ++howManyTimes) {
            int randomImageIndex = (int) (random.nextFloat() * copyInputImageList.size());
            int randomPositionX = (int) (random.nextFloat() * FRAME_DIMENSIONS.width + 1);
            int randomPositionY = (int) (random.nextFloat() * FRAME_DIMENSIONS.height + 1);
            Point position = new Point(randomPositionX, randomPositionY);
            this.generatedImageTree.insertImage(position, copyInputImageList.get(randomImageIndex));
            copyInputImageList.remove(randomImageIndex);
        }
    }

    @Override
    public Collage getCollage() {
        Collage collage = new Collage(FRAME_DIMENSIONS.width, FRAME_DIMENSIONS.height);
        ArrayList<BufferedImageWithBounds> generatedImagesWithBoundsList =
                this.generatedImageTree.getImagesWithBoundList();
        generatedImagesWithBoundsList.sort(BufferedImageWithBounds::compareTo);
        for (BufferedImageWithBounds image : generatedImagesWithBoundsList) {
            if (fixAspectRatio)
                addImageWithFixedAspect(collage, image);
            else
                addImageWithoutFixedAspect(collage, image);
        }
        collage.draw();
        return collage;
    }

    private void addImageWithoutFixedAspect(Collage collage, BufferedImageWithBounds image) {
        collage.addImage(image.bufferedImage,
                image.bounds.getxNormalizedPosition1(),
                image.bounds.getyNormalizedPosition1(),
                image.bounds.getSize().width,
                image.bounds.getSize().height);
    }

    private void addImageWithFixedAspect(Collage collage, BufferedImageWithBounds image) {
        Dimension imageSize = new Dimension(image.bufferedImage.getWidth(), image.bufferedImage.getHeight());
        Dimension sizeToDraw = getScaledDimension(imageSize, image.bounds.getSize());
        Dimension offset = getOffsets(sizeToDraw, image.bounds.getSize());
        //Random random = new Random();
        collage.addImage(image.bufferedImage,
                // delete random and 2x multiplication for uniform distributed images (samey looking placement)
                image.bounds.getxNormalizedPosition1() + offset.width,
                image.bounds.getyNormalizedPosition1() + offset.height,
                sizeToDraw.width,
                sizeToDraw.height);
    }

    private Dimension getScaledDimension(Dimension imageSize, Dimension bounds) {
        Dimension output = new Dimension(imageSize.width, imageSize.height);
        double scaleWidth = bounds.width / imageSize.getWidth();
        double scaleHeight = bounds.height / imageSize.getHeight();
        double scaleToUse = Math.min(scaleWidth, scaleHeight);
        output.width = (int) Math.round(scaleToUse * output.width);
        output.height = (int) Math.round(scaleToUse * output.height);
        return output;
    }

    private Dimension getOffsets(Dimension sizeToDraw, Dimension bounds) {
        Dimension offset = new Dimension(0, 0);
        if (sizeToDraw.width < bounds.width)
            offset.width = (bounds.width - sizeToDraw.width) / 2;
        else
            offset.height = (bounds.height - sizeToDraw.height) / 2;
        return offset;
    }
}