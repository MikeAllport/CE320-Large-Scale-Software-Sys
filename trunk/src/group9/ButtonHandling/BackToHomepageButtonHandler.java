package group9.ButtonHandling;

import group9.Piccollage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToHomepageButtonHandler implements ActionListener {
    private final Piccollage theApp;

    public BackToHomepageButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.loadingScreenforButtons();
        CardLayout c = (CardLayout) (theApp.getContentPane().getLayout());
        c.show(theApp.getContentPane(), "homePage");
    }
}