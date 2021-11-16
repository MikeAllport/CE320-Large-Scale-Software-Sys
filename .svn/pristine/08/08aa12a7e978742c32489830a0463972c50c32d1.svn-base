package group9.UnitTests;

import group9.GridCollageGenerator.GridCalculations;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GridCalculationsTesting {

    GridCalculations gridCalculations = new GridCalculations();

    @Test
    public void commonDivisorTest() {
        Assert.assertEquals(4, gridCalculations.getCommonDivisor(4, 12));
    }


    @Test
    public void gridShapeTest() {
        int[] output = gridCalculations.getGridShape(12);
        Assert.assertEquals(3, output[0]);
        Assert.assertEquals(4, output[1]);

        output = gridCalculations.getGridShape(20);
        Assert.assertEquals(4, output[0]);
        Assert.assertEquals(5, output[1]);

        output = gridCalculations.getGridShape(17);
        Assert.assertEquals(1, output[0]);
        Assert.assertEquals(17, output[1]);
    }

    @Test
    public void testReduceFraction() {
        int [] output = gridCalculations.reduceFraction(10, 15);
        Assert.assertEquals(2,output[0]);
        Assert.assertEquals(3,output[1]);

        output = gridCalculations.reduceFraction(25, 20);
        Assert.assertEquals(5,output[0]);
        Assert.assertEquals(4,output[1]);

        output = gridCalculations.reduceFraction(1100, 2500);
        Assert.assertEquals(11,output[0]);
        Assert.assertEquals(25,output[1]);
    }
}
