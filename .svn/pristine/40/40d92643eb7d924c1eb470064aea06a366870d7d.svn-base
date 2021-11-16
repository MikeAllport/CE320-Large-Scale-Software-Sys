package group9.RandomCollageGenerator;

import java.awt.*;

public class Bounds {
    private final int xPosition1;
    private final int yPosition1;
    private final int xPosition2;
    private final int yPosition2;
    private final Dimension size;
    // following positions are normalized to the nearest width i.e if width is 100, and position x is
    // 120, position x normalized is 100
    private int xNormalizedPosition1, xNormalizedPosition2, yNormalizedPosition1, yNormalizedPosition2;

    public Bounds(int xPosition1, int x2, int yPosition1, int yPosition2) {
        this.xPosition1 = xPosition1;
        this.xPosition2 = x2;
        this.yPosition1 = yPosition1;
        this.yPosition2 = yPosition2;
        this.size = new Dimension(x2 - xPosition1, yPosition2 - yPosition1);
        setNormalizedXPositions();
        setNormalizedYPositions();
    }

    // gets normalized bounds
    public Bounds(int x, int y, Dimension size) {
        this.xPosition1 = x;
        this.xPosition2 = x + size.width;
        this.yPosition1 = y;
        this.yPosition2 = y + size.height;
        this.size = size;
        setNormalizedXPositions();
        setNormalizedYPositions();
    }

    private void setNormalizedXPositions() {
        int pointsBeginXMod = xPosition1 / this.size.width;
        int boundsXMin = pointsBeginXMod * this.size.width;
        this.xNormalizedPosition1 = boundsXMin;
        this.xNormalizedPosition2 = boundsXMin + this.size.width;
    }

    private void setNormalizedYPositions() {
        int pointsBeginYMod = yPosition1 / this.size.height;
        int boundsYMin = pointsBeginYMod * this.size.height;
        this.yNormalizedPosition1 = boundsYMin;
        this.yNormalizedPosition2 = boundsYMin + this.size.height;
    }

    public boolean isOverlap(Bounds other) {
        return this.xPosition1 < other.xPosition2 &&
                this.xPosition2 > other.xPosition1 &&
                this.yPosition1 < other.yPosition2 &&
                this.yPosition2 > other.yPosition1;
    }

    public boolean isOverlapNormalized(int x, int y, Dimension size) {
        Bounds otherBounds = new Bounds(x, y, size);
        return isOverlapNormalized(otherBounds);
    }

    public boolean isOverlapNormalized(Bounds other) {
        return this.xNormalizedPosition1 < other.xNormalizedPosition2 &&
                this.xNormalizedPosition2 > other.xNormalizedPosition1 &&
                this.yNormalizedPosition1 < other.yNormalizedPosition2 &&
                this.yNormalizedPosition2 > other.yNormalizedPosition1;
    }

    public int getxPosition2() {
        return xPosition2;
    }

    public int getyPosition2() {
        return yPosition2;
    }

    public int getxNormalizedPosition1() {
        return xNormalizedPosition1;
    }

    public int getxNormalizedPosition2() {
        return xNormalizedPosition2;
    }

    public int getyNormalizedPosition1() {
        return yNormalizedPosition1;
    }

    public int getyNormalizedPosition2() {
        return yNormalizedPosition2;
    }

    public int getWidth() {
        return size.width;
    }

    public int getHeight() {
        return size.height;
    }

    public Dimension getSize() {
        return size;
    }
}