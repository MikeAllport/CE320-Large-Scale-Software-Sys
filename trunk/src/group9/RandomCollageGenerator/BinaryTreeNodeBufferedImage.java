package group9.RandomCollageGenerator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BinaryTreeNodeBufferedImage {
    final Bounds bounds;
    final BufferedImage image;
    BinaryTreeNodeBufferedImage leftChild, rightChild;

    BinaryTreeNodeBufferedImage(BufferedImage image, Bounds bounds) {
        this.bounds = bounds;
        this.image = image;
    }

    public ArrayList<BufferedImageWithBounds> getImages() {
        return new ArrayList<>(getImagesRecursive());
    }

    private ArrayList<BufferedImageWithBounds> getImagesRecursive() {
        ArrayList<BufferedImageWithBounds> images = new ArrayList<>();
        if (leftChild == null && rightChild == null) {
            BufferedImageWithBounds collageImage = new BufferedImageWithBounds(this.image, this.bounds);
            images.add(collageImage);
            return images;
        }
        assert leftChild != null;
        images.addAll(leftChild.getImagesRecursive());
        images.addAll(rightChild.getImagesRecursive());
        return images;
    }

    boolean isOverlapNormalized(Point point) {
        return this.bounds.isOverlapNormalized(point.getX(), point.getY(), bounds.getSize());
    }
}