package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousCollageButtonHandler implements ActionListener {
    private final Piccollage theApp;

    public PreviousCollageButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.getCollagePage().loadingScreenforButtonsCollagePage();
        theApp.getCollagePage().setCollagePreviewImage(-1);
        if (theApp.getGeneratedCollages().size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no collages to cycle through");
        }
        if (theApp.getGeneratedCollages().size() == 1) {
            JOptionPane.showMessageDialog(null, "There is only one collage");
        }
    }
}