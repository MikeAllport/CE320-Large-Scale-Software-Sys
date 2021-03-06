package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateCollageButtonHandler implements ActionListener {
    private final Piccollage theApp;

    public GenerateCollageButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.loadingScreenforButtons();

        if (theApp.getBufferedImages().size() > 1) {
            theApp.generateCollages();
            CardLayout c = (CardLayout) (theApp.getContentPane().getLayout());
            theApp.getCollagePage().setCollagePreviewImage(0);
            c.show(theApp.getContentPane(), "collagePage");
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient images uploaded");
        }
    }
}