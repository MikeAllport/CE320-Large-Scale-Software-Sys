import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

class TestRandomCollageGenerator {
    Dimension colageSize = new Dimension(500, 300);
    BufferedImage imgRandom = new BufferedImage(colageSize.width, colageSize.height, BufferedImage.TYPE_INT_RGB);
    ArrayList<BufferedImage> imageList;
    public String path;

    // this should take an arraylist of images to initialize, code here has been hacked
    // just to get it working and initialized
    TestRandomCollageGenerator()
    {
        ArrayList<BufferedImage> imageList = new ArrayList<>();
        URL image1 = this.getClass().getResource("test images/dog.jpg");
        URL image2 = this.getClass().getResource("test images/puppy.jpg");
        try {
            BufferedImage bufferedImage1 = ImageIO.read(image1);
            BufferedImage bufferedImage2 = ImageIO.read(image2);
            imageList.add(bufferedImage1);
            imageList.add(bufferedImage2);
        }
        catch (Exception ignore){
            System.out.println("Threre was an error in construction");
        };
        this.imageList = imageList;
        generateRandomCollage();
    }

    void generateRandomCollage()
    {
        Graphics2D randomGraphics = (Graphics2D) this.imgRandom.getGraphics();
        for (BufferedImage image: imageList)
        {
            Random random = new Random();
            int randomPositionX = (int) Math.floor(random.nextFloat() * colageSize.width + 1);
            int randomPositionY = (int) Math.floor(random.nextFloat() * colageSize.height + 1);
            int randomWidth = (int) Math.floor(random.nextFloat() * image.getWidth() + 1);
            float scaleDimensionDifference = randomWidth / (float) image.getWidth();
            int randomHeight = (int) Math.floor(scaleDimensionDifference * image.getHeight());
            randomGraphics.drawImage(image, randomPositionX, randomPositionY, randomWidth, randomHeight, null);
        }
        File generatedCollage = new File("trunk/RandomCollageGenerator/outputCollage.jpg");
        try {
            ImageIO.write(imgRandom, "jpg", generatedCollage);
        }
        catch (Exception ignore){
            System.out.println("Threre was an error");
        };
    }



    public static void main(String[] args) {
        TestRandomCollageGenerator gen = new TestRandomCollageGenerator();
    }
}