package group9.RandomCollageGenerator;

import java.awt.image.BufferedImage;

public class BufferedImageWithBounds implements Comparable<BufferedImageWithBounds> {
    final BufferedImage bufferedImage;
    final Bounds bounds;

    public BufferedImageWithBounds(BufferedImage image, Bounds bounds) {
        this.bufferedImage = image;
        this.bounds = bounds;
    }

    @Override
    // purpose is linear dimension conversion to sort image in descending order (bigger to smaller)
    public int compareTo(BufferedImageWithBounds o) {
        double thisSize = bounds.getSize().width + bounds.getSize().height;
        double otherSize = o.bounds.getSize().width + o.bounds.getSize().height;
        return (int) (otherSize - thisSize);
    }
}