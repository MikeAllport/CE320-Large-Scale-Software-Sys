package group9.CollageWindow;

import group9.Piccollage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class CollageMouseListener extends MouseAdapter {

    private final Piccollage theApp;
    private final CollageOptions options;

    public CollageMouseListener (Piccollage theApp, CollageOptions options) {
        this.theApp = theApp;
        this.options = options;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if (theApp.getGeneratedCollages().size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no collages currently loaded");
        } else {
            int collageWidth = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex()).getWidth();
            int collageHeight = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex()).getHeight();
            double targetWidth = theApp.getCollagePage().getPreviewCollageJPanel().getWidth();
            double targetHeight = theApp.getCollagePage().getPreviewCollageJPanel().getHeight();
            double scaleX = targetWidth/collageWidth;
            double scaleY = targetHeight/collageHeight;
            int x = (int)(e.getX()/scaleX);
            int y = (int)(e.getY()/scaleY);

            Point p = new Point(x,y);
            if (options.getSelectedIndex()==1) createText(x,y);
            if (options.getSelectedIndex()==3) {
                String selected = "";
                for (Enumeration<AbstractButton> buttons = options.getRadioButtons().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selected = button.getText();
                    }
                }
                if(selected.equals("Collage")) theApp.getCollagePage().setCollagePreviewImage(0);
                if(selected.equals("Image")) {
                    setPoint(p);
                    theApp.getCollagePage().setCollagePreviewImage(0, p);
                }

            }
        }
    }

    private void createText(int x, int y){
        String textSize = (String) options.getTextSize().getSelectedItem();
        int size = 0;
        assert textSize != null;
        if (textSize.equals("Small"))
            size = 20;
        if (textSize.equals("Medium"))
            size = 25;
        if (textSize.equals("Large"))
            size = 30;

        System.out.println(size);
        String textColor = (String) options.getTextColors().getSelectedItem();
        assert textColor != null;
        Colors colorEnum = Colors.getEnumFromString(textColor.toUpperCase());
        assert colorEnum != null;
        Color color = Colors.getColor(colorEnum);

        String text = JOptionPane.showInputDialog("Enter text");
        theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex()).addText(text, x,y, size, color);
        theApp.getCollagePage().setCollagePreviewImage(0);
        System.out.println(text);
    }

    private void setPoint(Point p){
        theApp.getCollagePage().setPointOnCollage(p);
    }
}
