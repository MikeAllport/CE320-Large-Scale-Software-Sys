package group9;

import group9.ButtonHandling.ConversionToGreyscaleButtonHandler;
import group9.CollageGenerators.GridCollageGenerator_PH;
import group9.CollageWindow.CollagePage;
import group9.GridCollageGenerator.Collage;
import group9.HomepageWindow.HomePage;
import group9.HomepageWindow.MenuBar;
import group9.RandomCollageGenerator.CollageGeneratorBinaryTree;
import group9.RandomCollageGenerator.ICollageGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Piccollage extends JFrame {
    protected final ArrayList<Collage> listOfGeneratedCollages = new ArrayList<>();
    protected final ArrayList<File> imageFiles = new ArrayList<>();
    protected final ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
    protected final DefaultListModel<File> filenamesModel = new DefaultListModel<>();//list model to work with JList
    protected final HomePage homepage;
    protected final CollagePage collagePage;

    public Piccollage() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
        setResizable(false);
        setLayout(new CardLayout(10, 10));
        MenuBar m = new MenuBar();
        setJMenuBar(m);

        this.homepage = new HomePage(this);
        add("homePage", homepage.getPage());

        this.collagePage = new CollagePage(this);
        add("collagePage", collagePage.getPage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("GROUP 09");
        ImageIcon titleIcon = new ImageIcon("trunk\\resources\\icons\\frame_title_icon_piccollage.png");
        setIconImage(titleIcon.getImage());
        setVisible(true);
        pack();
    }

    public static void main(String[] args) throws IOException {
        JWindow loadingScreen = new JWindow(); //loading screen before application runs
        loadingScreen.getContentPane().add(
                new JLabel("", new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/loading.gif"), SwingConstants.CENTER));
        loadingScreen.setBounds(575, 375, 400, 160);
        new Thread(() -> {
            try {
                loadingScreen.setVisible(true);
                Thread.sleep(3000);
                loadingScreen.setVisible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadingScreen.dispose();
        }).start();
        new Piccollage();
    }

    public ArrayList<Collage> getGeneratedCollages() {
        return listOfGeneratedCollages;
    }

    public DefaultListModel<File> getFileNamesModel() {
        return filenamesModel;
    }

    public ArrayList<File> getImageFiles() {
        return imageFiles;
    }

    public HomePage getHomePage() {
        return this.homepage;
    }

    public CollagePage getCollagePage() {
        return this.collagePage;
    }

    public void addFiles(File[] files) {
        imageFiles.addAll(Arrays.asList(files));
        addFileNamesToModel(files);
    }

    public void deleteFile(File file) {
        imageFiles.remove(getFileFromName(file.getName()));
        filenamesModel.removeElement(file);
    }

    public void clearAllLoadedImages() {
        imageFiles.clear();
        filenamesModel.clear();
    }

    public File getFileFromName(String filename) {
        for (File f : imageFiles) if (f.getName().equals(filename)) return f;
        return null;
    }

    public void createImagesFromFiles() throws IOException {
        for (File f : imageFiles) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(f);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            bufferedImages.add(image);
        }
    }

    public void addFileNamesToModel(File[] files) {
        for (File f : files) {
            filenamesModel.addElement(f);
        }
    }

    public ArrayList<BufferedImage> getBufferedImages() {
        try {
            bufferedImages.clear();
            createImagesFromFiles();
            return bufferedImages;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void generateCollages() {
        this.getGeneratedCollages().clear();
        if (this.bufferedImages.size() <= 0)
            return;

        GridCollageGenerator_PH ph = new GridCollageGenerator_PH(this.bufferedImages, 1920, 1080, 25,
                25);
        this.listOfGeneratedCollages.add(ph.getCollage());

        if (this.bufferedImages.size() > 2 && (this.bufferedImages.size() - 1) % 3 == 0) {
            GridCollageGenerator_PH ph2 = new GridCollageGenerator_PH(this.bufferedImages, 1920, 1080, 25,
                    25, 3);
            this.listOfGeneratedCollages.add(ph2.getCollage());
        }

        if (this.bufferedImages.size() < 9) {
            GridCollageGenerator_PH ph3 = new GridCollageGenerator_PH(this.bufferedImages, 1920, 1080, 25,
                    25, 1);
            this.listOfGeneratedCollages.add(ph3.getCollage());
        }

        if (this.bufferedImages.size() % 4 == 0) {
            GridCollageGenerator_PH ph4 = new GridCollageGenerator_PH(this.bufferedImages, 1920, 1080, 25,
                    25, 4);
            this.listOfGeneratedCollages.add(ph4.getCollage());
        }

        Dimension sizeToDraw = new Dimension(
                this.getCollagePage().getPage().getWidth(),
                this.getCollagePage().getPage().getHeight());

        ICollageGenerator generator = new CollageGeneratorBinaryTree(this.bufferedImages, sizeToDraw, true);
        this.listOfGeneratedCollages.add(generator.getCollage());
        generator = new CollageGeneratorBinaryTree(this.bufferedImages, sizeToDraw, true);
        this.listOfGeneratedCollages.add(generator.getCollage());
        generator = new CollageGeneratorBinaryTree(this.bufferedImages, sizeToDraw, true);
        this.listOfGeneratedCollages.add(generator.getCollage());
    }

    public void loadingScreenforButtons() { //method is called on the button handlers that invoke it
        JWindow loadingScreenForButtons = new JWindow();
        loadingScreenForButtons.getContentPane().add(
                new JLabel("", new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/loading2.gif"), SwingConstants.CENTER));
        loadingScreenForButtons.setBounds(1100, 75, 65, 65);
        new Thread(() -> {
            try {
                loadingScreenForButtons.setVisible(true);
                Thread.sleep(200);
                loadingScreenForButtons.setVisible(false);
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            loadingScreenForButtons.dispose();
        }).start();
    }

}