package group9.CollageGenerators;

import group9.GridCollageGenerator.Collage;
import group9.utils.Utils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridCollageGenerator_PH extends JPanel {
    private final List<BufferedImage> imageList;
    private final int cell_width;
    private final int cell_height;
    private final int NUM_COLS;
    private final int WIDTH;
    private final int HEIGHT;
    private final List<JLabel> labelList;
    private int NUM_ROWS = 2;
    private BufferedImage image;

    public GridCollageGenerator_PH(List<BufferedImage> imageList, int width, int height, int horizontalGap, int verticalGap) //default ctor has 2 rows only
    {
        WIDTH = width;
        HEIGHT = height;
        this.imageList = imageList;
        if (imageList.size() % 2 != 0) //remove last picture in the list in case there is an odd number of pictures
            imageList.remove(imageList.size() - 1); //
        NUM_COLS = (imageList.size() <= 1) ? 1 : imageList.size() / 2;
        cell_width = (WIDTH - ((NUM_COLS - 1) * horizontalGap)) / NUM_COLS;
        cell_height = (HEIGHT - verticalGap) / NUM_ROWS;
        this.setSize(new Dimension(WIDTH, HEIGHT));
        labelList = new ArrayList<>();

        GridLayout layout = new GridLayout(NUM_ROWS, NUM_COLS, horizontalGap, verticalGap);
        this.setLayout(layout);

        createCollage();
        makeImage();
    }

    public GridCollageGenerator_PH(List<BufferedImage> imageList, int width, int height, int horizontalGap, int verticalGap,
                                   int rows, int cols) {
        WIDTH = width;
        HEIGHT = height;
        this.imageList = imageList;
        if (imageList.size() % 2 != 0) //remove last picture in the list in case there is an odd number of pictures
            imageList.remove(imageList.size() - 1); //
        NUM_ROWS = rows;
        NUM_COLS = cols;
        cell_width = (WIDTH - ((NUM_COLS - 1) * horizontalGap)) / NUM_COLS;
        cell_height = (HEIGHT - verticalGap) / NUM_ROWS;
        this.setSize(new Dimension(WIDTH, HEIGHT));
        labelList = new ArrayList<>();
        GridLayout layout = new GridLayout(NUM_ROWS, NUM_COLS, horizontalGap, verticalGap);
        this.setLayout(layout);

        createCollage();
        makeImage();
    }

    //ctor for custom no. of rows only
    public GridCollageGenerator_PH(List<BufferedImage> imageList, int width, int height, int horizontalGap, int verticalGap,
                                   int rows) {
        WIDTH = width;
        HEIGHT = height;
        this.imageList = imageList;
        if (imageList.size() % 2 != 0) //remove last picture in the list in case there is an odd number of pictures
            imageList.remove(imageList.size() - 1); //
        NUM_ROWS = rows;
        NUM_COLS = (imageList.size() <= 1)? 1: imageList.size() / NUM_ROWS;
        cell_width = (WIDTH - ((NUM_COLS - 1) * horizontalGap)) / NUM_COLS;
        cell_height = (HEIGHT - verticalGap) / NUM_ROWS;
        this.setSize(new Dimension(WIDTH, HEIGHT));
        labelList = new ArrayList<>();
        GridLayout layout = new GridLayout(NUM_ROWS, NUM_COLS, horizontalGap, verticalGap);
        this.setLayout(layout);

        createCollage();
        makeImage();
    }


    public static BufferedImage fixImage(BufferedImage img, double targetAspectRatio, int targetWidth, int targetHeight) {
        int img_width = img.getWidth();
        int img_height = img.getHeight();
        double img_aspectRatio = (float) img_width / img_height;
        if (img_aspectRatio < targetAspectRatio) //if the aspect ratio is lower than target, cut the height of the image
        {
            double height_to_cut = (-img_width + targetAspectRatio * img_height) / targetAspectRatio;
            return Scalr.crop(img, 0, (int) height_to_cut / 2, img_width, img_height - (int) height_to_cut);
        } else if (img_aspectRatio > targetAspectRatio) //if the aspect ratio is higher than target, cut the width
        {
            double width_to_cut = (float) (-img_height * targetAspectRatio + img_width) / 2;
            return Scalr.crop(img, (int) width_to_cut, 0, img_width - (int) width_to_cut * 2, img_height);
        } else {
            int wc = (img_width - targetWidth) / 2;
            int hc = (img_height - targetHeight) / 2;
            return Scalr.crop(img, wc / 2, hc / 2, img.getWidth() - wc, img.getHeight() - hc);
        }

    }

    private void createCollage() {
        double aspectRatio = (double) cell_width / cell_height;
        for (BufferedImage img : imageList) {
            if (img.getHeight() < cell_height && img.getWidth() < cell_width) {
                BufferedImage upscaledImage = Utils.rescaleIcon(img, img.getWidth() * (cell_width / img.getWidth() + 1),
                        img.getHeight() * (cell_height / img.getHeight()) + 1);
                BufferedImage croppedImg = fixImage(upscaledImage, aspectRatio, cell_width, cell_height);
                BufferedImage resizedImg = Utils.rescaleIcon(croppedImg, cell_width, cell_height);
                JLabel label = new JLabel(new ImageIcon(resizedImg));
                label.setSize(cell_width, cell_height);
                this.add(label);
                labelList.add(label);
            } else if (img.getHeight() > cell_height || img.getWidth() > cell_width) {
                BufferedImage croppedImg = fixImage(img, aspectRatio, cell_width, cell_height);
                BufferedImage resizedImg = Utils.rescaleIcon(croppedImg, cell_width, cell_height);
                JLabel label = new JLabel(new ImageIcon(resizedImg));
                label.setSize(cell_width, cell_height);
                this.add(label);
                labelList.add(label);
            } else if (img.getHeight() < cell_height || img.getWidth() < cell_width) {
                BufferedImage resizedImg = Utils.rescaleIcon(img, cell_width, cell_height);
                JLabel label = new JLabel(new ImageIcon(resizedImg));
                label.setSize(cell_width, cell_height);
                this.add(label);
                labelList.add(label);
            } else {
                JLabel label = new JLabel(new ImageIcon(img));
                label.setSize(cell_width, cell_height);
                this.add(label);
                labelList.add(label);
            }
        }
    }

    private void makeImage() {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        this.print(g);
        g.dispose();
        frame.dispose();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Collage getCollage() {
        Collage collage = new Collage(WIDTH, HEIGHT);
        for (JLabel label : labelList) {
            Icon icon = label.getIcon();
            BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            collage.addImage(bi, label.getX(), label.getY(), bi.getWidth(), bi.getHeight());
        }
        collage.draw();
        return collage;
    }

    public int getNUM_COLS() {
        return NUM_COLS;
    }

    public int getNUM_ROWS() {
        return NUM_ROWS;
    }

    public int getListSize()
    {
        return imageList.size();
    }
}
