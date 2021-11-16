package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearAllButtonHandler implements ActionListener {
    final Piccollage theApp;

    public ClearAllButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.loadingScreenforButtons();
        if (theApp.getImageFiles().size() > 0) {
            theApp.clearAllLoadedImages();
            JOptionPane.showMessageDialog(null, "The loaded images have been cleared");
        } else JOptionPane.showMessageDialog(null, "No loaded images to be cleared");
    }
}