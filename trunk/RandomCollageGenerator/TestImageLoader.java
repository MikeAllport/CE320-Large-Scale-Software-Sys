import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class TestImageLoader {
    private static final String IMAGE_FILENAMES[] = new String[]{ "generic1.jpg", "generic2.jpg", "generic3.jpg", "generic4.jpg",
            "generic5.jpg", "generic6.jpg" };
    private static final String IMAGE_DIRECTORY = "res/";

    private static ArrayList<URL> getImageURLs()
    {
        ArrayList<URL> imageURLsList = new ArrayList<>();
        for (String filename: IMAGE_FILENAMES)
        {
            URL imageURL = TestImageLoader.class.getResource(IMAGE_DIRECTORY + filename);
            imageURLsList.add(imageURL);
        }
        return imageURLsList;
    }

    public static ArrayList<BufferedImage> getBufferedImages()
    {
        ArrayList<URL> imageURLsList = getImageURLs();
        ArrayList<BufferedImage> imageList = new ArrayList<>();
        for (URL imageURL: imageURLsList)
        {
            try {
                BufferedImage image = ImageIO.read(imageURL);
                imageList.add(image);
            } catch (Exception ignore){}
        }
        return imageList;
    }
}
