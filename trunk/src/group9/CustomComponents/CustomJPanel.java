package group9.CustomComponents;

import group9.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomJPanel extends JPanel {
    private final GridBagConstraints c;

    public CustomJPanel() {
        super();
        this.c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);  //top padding
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
    }

    public GridBagConstraints getConstraints() {
        return c;
    }

    public CustomJButton createButton(String type) {
        return new CustomJButton(type);
    }

    public JPanel createButtonPanel(JButton... buttons) {
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        for (JButton btn : buttons) {
            btnPanel.add(btn);
        }
        return btnPanel;
    }

    public CustomListRenderer createListRenderer() {
        return new CustomListRenderer();
    }
}

//Custom edition of the JButton for all image-only buttons
class CustomJButton extends JButton {
    public CustomJButton(String type) {
        super();
        ImageIcon icon;
        setBorderPainted(false); //Prevents gray background
        setContentAreaFilled(false); //Prevents gray background
        setFocusPainted(false); //Prevents gray background
        setOpaque(false);
        switch (type.toLowerCase()) {
            case "load":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/load_image_button.png");
                break;
            case "delete":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/delete_button.png");
                break;
            case "help":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/help_icon_button.png");
                break;
            case "generate":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/generate_collage_button.png");
                break;
            case "customise":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/customise_image_button.png");
                break;
            case "home":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/home_image_button.png");
                break;
            case "previous":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/previous_image_button.png");
                break;
            case "next":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/next_image_button.png");
                break;
            case "save":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/save_collage_button.jfif");
                break;
            case "clear":
                icon = new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/clearall_button_icon.jfif");
                break;
            default:
                throw new IllegalStateException("Unexpected button type: " + type);
        }

        //Scaling the images to ensure entire image icon is seen on the button
        Image resizingIcon = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizingIcon);
        setIcon(icon);
        setPreferredSize(new Dimension(70, 70));
        setBorder(null);
    }
}

//Modifies the cells in the JList & Turns a regular JList into a table of thumbnails
class CustomListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        //Getting the image from the JListModel
        BufferedImage image = null;
        try {
            image = ImageIO.read((File) value);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //Rescaling it to a thumbnail's size
        image = Utils.rescaleIcon(image, 50, 50);
        ImageIcon testIcon = new ImageIcon(image);

        //Enabling multiple columns in JList
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        //JList columns will adapt to the length of the component its in
        list.setVisibleRowCount(0);

        //Set fixed dimensions for all images to make it more uniform and organised
        list.setFixedCellWidth(80);
        list.setFixedCellHeight(80);

        //Setting the label's text as a sub-title for each specific thumbnail
        label.setPreferredSize(new Dimension(80, 80));
        label.setText(((File) value).getName());
        label.setIcon(testIcon);

        //Setting text to be centered and below each image thumbnail
        label.setHorizontalTextPosition(JLabel.CENTER); //center label vertically
        label.setVerticalTextPosition(JLabel.BOTTOM); //center label horizontally

        label.setVerticalAlignment(JLabel.CENTER);  //center text vertically
        label.setHorizontalAlignment(JLabel.CENTER);//center text horizontally
        return label;
    }
}