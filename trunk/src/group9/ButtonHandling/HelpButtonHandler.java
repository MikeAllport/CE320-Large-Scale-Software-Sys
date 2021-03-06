package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpButtonHandler implements ActionListener {
    private final Piccollage theApp;

    public HelpButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel title = new JLabel();
        ImageIcon titleIcon = new ImageIcon(System.getProperty("user.dir") + "/resources/icons/help_icon_button.png");

        Image image = titleIcon.getImage(); // transform it
        Image scaled = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(scaled);  // transform it back
        title.setIcon(titleIcon);

        title.setBorder(new EmptyBorder(15, 15, 15, 15));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        String helpText;
        if (theApp.getHomePage().getPage().isVisible()) {
            title.setText("Homepage");
            helpText =
                    " Homepage: Simply load images into the application using the load button in " +
                            "top left. Any images you load can viewed on the screen, you can choose them from the list on the left. " +
                            "Click the Delete button in the top " +
                            "left to remove any image you've selected.When you're ready to generate the collage, click the " +
                            "Generate button at the bottom";
        } else {
            title.setText("Collage Page");
            helpText = "You can cycle through collages using the back and next buttons. If you " +
                    "like the generated collage, click the Save button to save it to your desktop. " +
                    "If you don't like it, click the Delete button to discard the collage. Alternatively, " +
                    "click the Home button to return to the previous screen";
        }

        JTextArea helpfulInfo = new JTextArea(helpText);
        helpfulInfo.setWrapStyleWord(true);
        helpfulInfo.setLineWrap(true);
        helpfulInfo.setOpaque(false);
        helpfulInfo.setBorder(null);
        helpfulInfo.setEditable(false);
        helpfulInfo.setFocusable(false);
        helpfulInfo.setBorder(new EmptyBorder(0, 25, 25, 25));

        JFrame help = new JFrame("Help");
        help.setPreferredSize(new Dimension(400, 350));
        help.add(title, BorderLayout.PAGE_START);
        help.add(helpfulInfo);
        help.setLocationRelativeTo(null);
        help.setVisible(true);
        help.setResizable(false);
        help.pack();
    }
}