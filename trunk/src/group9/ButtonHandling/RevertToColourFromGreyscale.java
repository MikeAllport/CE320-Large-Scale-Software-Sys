package group9.ButtonHandling;

import group9.GridCollageGenerator.Collage;
import group9.GridCollageGenerator.CollageImage;
import group9.Piccollage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

public class RevertToColourFromGreyscale implements ActionListener {

    Piccollage theApp;
    ButtonGroup radioButtonGroup;

    public RevertToColourFromGreyscale(Piccollage theApp, ButtonGroup radioButtonGroup)
    {
        this.theApp = theApp;
        this.radioButtonGroup = radioButtonGroup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (theApp.getGeneratedCollages().size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no collages currently loaded");
        } else {
            String selected = "";
            for (Enumeration<AbstractButton> buttons = radioButtonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    selected = button.getText();
                }
            }
            if(selected.equals("Collage")) convertCollage();
            if(selected.equals("Image")) convertImage();
            theApp.getCollagePage().setCollagePreviewImage(0);
        }
    }

    private void convertCollage() {
        Collage collage = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex());
        collage.setGreyscale(false);
    }

    private void convertImage() {
        Point p = theApp.getCollagePage().getPointOnCollage();
        CollageImage selected = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex()).getImageFromPoint(p);
        if(selected==null)JOptionPane.showMessageDialog(null, "No image selected");
        else {
            selected.setGreyscale(false);
            theApp.getCollagePage().setPointOnCollage(new Point(-1,-1));
        }
    }
}