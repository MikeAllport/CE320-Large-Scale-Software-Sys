package group9.CollageWindow;

import java.awt.*;

public enum Colors {
    BLACK("BLACK"), BEIGE("BEIGE"), GREY("GREY"), WHITE("WHITE"), RED("RED"), BLUE("BLUE"),
    GREEN("GREEN"), YELLOW("YELLOW");

    private final String label;

    Colors(String label) {
        this.label = label;
    }

    public static Color getColor(Colors color) {
        switch (color) {
            case BLACK:
                return Color.BLACK;
            case BEIGE:
                return new Color(255, 228, 196);
            case GREY:
                return Color.GRAY;
            case WHITE:
                return Color.WHITE;
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case YELLOW:
                return Color.YELLOW;
            case BLUE:
                return Color.BLUE;
            default:
                return Color.ORANGE;
        }
    }

    public static Colors getEnumFromString(String toCheck) {
        try {
            return Colors.valueOf(toCheck.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    public String getLabel() {
        return label.charAt(0) + label.substring(1).toLowerCase();
    }
}

