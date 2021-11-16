package group9.CollageWindow;

import group9.ButtonHandling.ConversionToGreyscaleButtonHandler;
import group9.ButtonHandling.RevertToColourFromGreyscale;
import group9.Piccollage;
import group9.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static group9.CollageWindow.Colors.*;

public class CollageOptions extends JTabbedPane {
    private final JComboBox<String> textSize;
    private final JComboBox<String> textColors;
    private final ButtonGroup radioButtonGroup;

    public CollageOptions(Piccollage theApp) {
        super();
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setMinimumSize(new Dimension(400, 90));

        JPanel tab1 = new JPanel();
        tab1.setLayout(new BoxLayout(tab1, BoxLayout.X_AXIS));

        JScrollPane tab1_scrollable = new JScrollPane(tab1); //making tab scrollable

        JLabel lblRetro = new JLabel();
        lblRetro.setIcon(getImageIcon());

        tab1.add(Box.createRigidArea(new Dimension(5, 0))); //add H-Gap
        tab1.add(lblRetro);
        tab1.add(Box.createRigidArea(new Dimension(5, 0))); //add H-Gap

        JLabel lblComic = new JLabel();
        lblComic.setText("Comic");

        tab1.add(Box.createRigidArea(new Dimension(5, 0))); //add H-Gap
        tab1.add(lblComic);
        tab1.add(Box.createRigidArea(new Dimension(5, 0))); //add H-Gap


        tab1_scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tab1_scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

       /* addTab("Filter", tab1_scrollable); //functionality to be implemented if there's more time


        JPanel tab2 = new JPanel();
        addTab("Frames", tab2);

        JPanel tab3 = new JPanel();
        addTab("Effects", tab3);
*/

        JPanel tab4 = new JPanel();
        addTab("Rotate", tab4);

        JPanel tab5 = new JPanel();
        JLabel label = new JLabel("Click on the collage to add text");
        tab5.add(label);
        addTab("Text", tab5);
        String[] sizes = {"Small", "Medium", "Large"};
        textSize = new JComboBox<>(sizes);
        tab5.add(textSize);
        String[] colors = {BLACK.getLabel(), RED.getLabel(), GREEN.getLabel(), WHITE.getLabel(), BLUE.getLabel(), YELLOW.getLabel()};
        textColors = new JComboBox<>(colors);
        tab5.add(textColors);


        JPanel tab6 = new JPanel();
        String[] colours = {BLACK.getLabel(), BEIGE.getLabel(), GREY.getLabel(), WHITE.getLabel()};
        JComboBox<String> comboColours = new JComboBox<>(colours);
        comboColours.addActionListener(new ColorComboListener(theApp));
        comboColours.setFont(new Font("Arial", Font.BOLD, 14));
        comboColours.setEditable(false);
        tab6.add(comboColours);
        addTab("Background Colour", tab6);

        JPanel tab8 = new JPanel();
        JButton grayscale = new JButton("Convert To Grayscale");
        JButton colour = new JButton("Revert To Colour");
        JRadioButton collageRadioButton = new JRadioButton("Collage");
        JRadioButton imageRadioButton = new JRadioButton("Image");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(collageRadioButton);
        radioButtonGroup.add(imageRadioButton);
        collageRadioButton.setSelected(true);
        grayscale.addActionListener(new ConversionToGreyscaleButtonHandler(theApp,radioButtonGroup));
        colour.addActionListener(new RevertToColourFromGreyscale(theApp,radioButtonGroup));
        tab8.add(grayscale);
        tab8.add(colour);
        tab8.add(collageRadioButton);
        tab8.add(imageRadioButton);
        addTab("Grayscale", tab8);
    }

    public static void main(String[] args) {
    }

    private ImageIcon getImageIcon() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir") + "/trunk/resources/icons/retro_filter.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Image img = Utils.rescaleIcon(image, 50, 50);

        return new ImageIcon(img);
    }

    public JComboBox<String> getTextSize(){
        return textSize;
    }

    public JComboBox<String> getTextColors(){
        return textColors;
    }

    public ButtonGroup getRadioButtons() {
        return radioButtonGroup;
    }
}