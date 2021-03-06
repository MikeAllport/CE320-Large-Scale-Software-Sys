package group9.CustomComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PreviewImageJPanel extends JPanel {

    private final JLabel imageLbl;
    private BufferedImage image;

    public PreviewImageJPanel(JLabel imageLbl) {
        super();
        this.imageLbl = imageLbl;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(imageLbl.getPreferredSize()));
        setLayout(new BorderLayout());

        //Add a border with a title to show bounding boxes
        TitledBorder t = new TitledBorder(new LineBorder(Color.BLACK, 3), "PREVIEW IMAGE");
        t.setTitleJustification(TitledBorder.CENTER);
        setBorder(t);

        imageLbl.setBorder(new EmptyBorder(15, 15, 15, 15));
        imageLbl.setHorizontalAlignment(JLabel.CENTER);
        imageLbl.setVerticalAlignment(JLabel.CENTER);

        imageLbl.setVerticalTextPosition(JLabel.BOTTOM);
        imageLbl.setHorizontalTextPosition(JLabel.CENTER);

        add(imageLbl);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        if (image != null) {
            this.imageLbl.setIcon(new ImageIcon(image));
        } else {
            this.imageLbl.setIcon(null);
        }
        repaint();
        revalidate();
    }
}