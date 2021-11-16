package group9.UnitTests;

import group9.CollageWindow.Colors;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ColorsTest {

    @Test
    public void getColor() {
        Assert.assertEquals(Colors.getColor(Colors.BLACK),Color.black);
        Assert.assertEquals(Colors.getColor(Colors.BEIGE),new Color(255,228,196));
        Assert.assertEquals(Colors.getColor(Colors.WHITE),Color.white);
        Assert.assertNotEquals(Colors.getColor(Colors.BLUE),Color.white);

    }

    @Test
    public void getEnumFromString() {
        Assert.assertEquals(Colors.getEnumFromString("black"), Colors.BLACK);
        Assert.assertEquals(Colors.getEnumFromString("wHitE"), Colors.WHITE);
        Assert.assertEquals(Colors.getEnumFromString("yellow"), Colors.YELLOW);
    }

    @Test
    public void values() {
        Assert.assertEquals(Colors.BLACK.getLabel(), "Black");
        Assert.assertEquals(Colors.WHITE.getLabel(), "White");
        Assert.assertEquals(Colors.YELLOW.getLabel(), "Yellow");

    }

}