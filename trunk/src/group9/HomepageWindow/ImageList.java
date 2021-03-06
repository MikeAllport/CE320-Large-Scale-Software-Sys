package group9.HomepageWindow;

import group9.Piccollage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ImageList extends JPanel {
    public ImageList() {
        super();

        setBackground(Color.WHITE); //Setting background

        setLayout(new BorderLayout(15, 15)); //set layout

        //Creating border
        TitledBorder t = new TitledBorder(new LineBorder(Color.BLACK, 3), "IMAGES SELECTED");

        //Aligning title to center
        t.setTitleJustification(TitledBorder.CENTER);
        setBorder(t);

        //Setting size of panel in parent window
        setPreferredSize(new Dimension(300, 400));
    }
}