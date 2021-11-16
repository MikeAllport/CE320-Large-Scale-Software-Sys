package group9.CollageWindow;

import group9.GridCollageGenerator.Collage;
import group9.Piccollage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static group9.CollageWindow.Colors.*;

public class ColorComboListener implements ActionListener {
    Piccollage theApp;

    public ColorComboListener(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox box = (JComboBox) e.getSource();
        String boxValue = (String) box.getSelectedItem();
        Colors colorEnum = Colors.getEnumFromString(boxValue);
        if (colorEnum == null)
            return;
        switch (colorEnum) {
            case BLACK:
                Collage.setBackgroundColor(Colors.getColor(BLACK));
                break;
            case BEIGE:
                Collage.setBackgroundColor(Colors.getColor(BEIGE));
                break;
            case GREY:
                Collage.setBackgroundColor(Colors.getColor(GREY));
                break;
            case WHITE:
                Collage.setBackgroundColor(Colors.getColor(WHITE));
                break;
        }
        theApp.getCollagePage().setCollagePreviewImage(0);
    }
}
