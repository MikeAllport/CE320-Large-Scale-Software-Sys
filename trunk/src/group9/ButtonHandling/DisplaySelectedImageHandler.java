package group9.ButtonHandling;

import group9.Piccollage;
import group9.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplaySelectedImageHandler implements ListSelectionListener {

    private final Piccollage theApp;
    private final JLabel image;
    private final JList<File> filesList;

    public DisplaySelectedImageHandler(Piccollage theApp, JLabel image, JList<File> filesList) {
        this.theApp = theApp;
        this.image = image;
        this.filesList = filesList;
    }

    public void valueChanged(ListSelectionEvent e) {
        new Thread(() -> {
            try {
                //when no item is selected
                if (filesList.getSelectedValue() == null) {
                    SwingUtilities.invokeLater(() -> {
                        image.setIcon(null);
                        image.setText(null);
                    });
                } else { //When there is an item selected
                    BufferedImage img = ImageIO.read(theApp.getFileFromName(filesList.getSelectedValue().getName()));
                    img = Utils.rescaleIcon(img, 400, 400);
                    ImageIcon testIcon = new ImageIcon(img);

                    SwingUtilities.invokeLater(() -> {
                        image.setIcon(testIcon);
                        image.setText(filesList.getSelectedValue().getName());
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}