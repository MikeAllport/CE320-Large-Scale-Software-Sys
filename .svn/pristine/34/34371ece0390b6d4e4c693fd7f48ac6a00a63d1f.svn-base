package group9.ButtonHandling;

import group9.GridCollageGenerator.Collage;
import group9.GridCollageGenerator.CollageImage;
import group9.Piccollage;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.Enumeration;

public class ConversionToGreyscaleButtonHandler implements ActionListener {

    Piccollage theApp;
    ButtonGroup radioButtonGroup;

    public ConversionToGreyscaleButtonHandler(Piccollage theApp, ButtonGroup radioButtonGroup)
    {
        this.theApp = theApp;
        this.radioButtonGroup = radioButtonGroup;
    }

    //required for JUnit testing not for the button handler
    public static BufferedImage convertImageToBlackWhite(BufferedImage image) {
        BufferedImageOp bufferedImageOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        BufferedImage convertedImage = bufferedImageOp.filter(image, null);
        convertedImage.createGraphics();
        return convertedImage;
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
        collage.setGreyscale(true);
    }

    private void convertImage() {
        Point p = theApp.getCollagePage().getPointOnCollage();
        CollageImage selected = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex()).getImageFromPoint(p);
        if(selected==null)JOptionPane.showMessageDialog(null, "No image selected");
        else {
            selected.setGreyscale(true);
            theApp.getCollagePage().setPointOnCollage(new Point(-1,-1));
        }
    }
}