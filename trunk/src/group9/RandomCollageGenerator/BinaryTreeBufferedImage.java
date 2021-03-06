package group9.RandomCollageGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BinaryTreeBufferedImage {
    final Dimension size;
    BinaryTreeNodeBufferedImage root;

    public BinaryTreeBufferedImage(Dimension dimension) {
        this.root = null;
        this.size = dimension;
    }

    public void insertImage(Point point, BufferedImage image) {
        if (root == null) {
            root = new BinaryTreeNodeBufferedImage(image, new Bounds(point.getX(), point.getY(), size));
            return;
        }

        BinaryTreeNodeBufferedImage child = root;
        boolean inserted = false;
        while (!inserted) {
            if (child.leftChild == null && child.rightChild == null) {
                Bounds currentPositionAndArea = child.bounds;
                int leftX = currentPositionAndArea.getxNormalizedPosition1();
                int leftY = currentPositionAndArea.getyNormalizedPosition1();
                int rightx, righty;
                Dimension halfCurrentSize;
                boolean shouldSplitHorizontally = currentPositionAndArea.getWidth() > currentPositionAndArea.getHeight();
                if (shouldSplitHorizontally) {
                    halfCurrentSize = new Dimension((int) (currentPositionAndArea.getWidth() / 2f),
                            currentPositionAndArea.getHeight());
                    rightx = leftX + halfCurrentSize.width;
                    righty = leftY;
                } else {
                    halfCurrentSize = new Dimension(currentPositionAndArea.getWidth(),
                            (int) (currentPositionAndArea.getHeight() / 2f));
                    rightx = leftX;
                    righty = leftY + halfCurrentSize.height;
                }
                Bounds leftSplitBounds = new Bounds(leftX, leftY, halfCurrentSize);
                Bounds rightSplitBounds = new Bounds(rightx, righty, halfCurrentSize);
                child.leftChild = new BinaryTreeNodeBufferedImage(child.image, leftSplitBounds);
                child.rightChild = new BinaryTreeNodeBufferedImage(image, rightSplitBounds);
                inserted = true;
            }
            assert child.leftChild != null;
            if (child.leftChild.isOverlapNormalized(point))
                child = child.leftChild;
            else
                child = child.rightChild;
        }
    }

    public ArrayList<BufferedImageWithBounds> getImagesWithBoundList() {
        ArrayList<BufferedImageWithBounds> images = new ArrayList<>();
        if (root == null)
            return images;
        images.addAll(root.getImages());
        return images;
    }
}