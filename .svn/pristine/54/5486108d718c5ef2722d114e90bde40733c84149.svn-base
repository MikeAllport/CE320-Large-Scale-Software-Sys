package group9.GridCollageGenerator;

public class GridCalculations {

    public int calcNumHorizontal(double imageWidth, int smallestFrameWidth) {

        return (int) imageWidth / smallestFrameWidth;
    }

    public int calcNumVertical(double imageHeight, int smallestFrameHeight) {

        return (int) imageHeight / smallestFrameHeight;
    }

    public int[] getGridShape(int n) {
        int factor = (int) Math.sqrt(n);
        while (n % factor != 0) factor--;

        return new int[]{factor, n / factor};
    }

    public int[] reduceFraction(int width, int height) {
        int divisor;

        divisor = getCommonDivisor(width, height);

        width = width / divisor;
        height = height / divisor;

        return new int[]{width, height};
    }

    public int getCommonDivisor(int a, int b) {
        if (b == 0) return a;

        return getCommonDivisor(b, a % b);
    }
}
