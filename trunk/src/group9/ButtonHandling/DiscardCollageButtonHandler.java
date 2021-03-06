package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscardCollageButtonHandler implements ActionListener {

    private final Piccollage theApp;

    public DiscardCollageButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.loadingScreenforButtons();
        if (theApp.getGeneratedCollages().size() > 0) {
            theApp.getGeneratedCollages().remove(theApp.getCollagePage().getCollagePreviewIndex());
            theApp.getCollagePage().setCollagePreviewImage(0);
            JOptionPane.showMessageDialog(null, "The collage has been discarded");
        } else {
            JOptionPane.showMessageDialog(null, "There are no more collages to discard");
        }
    }
}