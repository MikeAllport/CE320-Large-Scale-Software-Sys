package group9.GridCollageGenerator;

import java.awt.*;

public class CollageText {
    private final int posX;
    private final int posY;
    private final String text;
    private final int size;
    private final Color color;

    public CollageText(String text, int x, int y, int size, Color color) {
        this.text = text;
        this.color = color;
        this.size = size;
        posX = x;
        posY = y;

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getText() {
        return text;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}