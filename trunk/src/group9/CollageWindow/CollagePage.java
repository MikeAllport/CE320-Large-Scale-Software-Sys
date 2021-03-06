package group9.CollageWindow;

import group9.ButtonHandling.*;
import group9.CustomComponents.CustomJPanel;
import group9.CustomComponents.PreviewImageJPanel;
import group9.Piccollage;
import group9.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class CollagePage {
    private final Piccollage theApp;
    private final CustomJPanel mainJPanel;
    private final PreviewImageJPanel previewCollageJPanel;
    private final JScrollPane s;
    private int index = 0;
    private Point pointOnCollage = new Point(-1,-1);

    public CollagePage(Piccollage theApp) {
        this.theApp = theApp;
        this.mainJPanel = new CustomJPanel();
        this.mainJPanel.setPreferredSize(theApp.getPreferredSize());
        GridBagConstraints c = mainJPanel.getConstraints();

        c.weightx = 0.5; //all cells have equal width

        //Adding 'HOME', 'HELP' button to panel
        // Adding this panel to cell (0, 0) in main JPanel
        c.gridx = 0;
        c.gridy = 0;
        JButton button_HOME = mainJPanel.createButton("HOME");
        JButton button_HELP = mainJPanel.createButton("HELP");
        mainJPanel.add(mainJPanel.createButtonPanel(button_HOME, button_HELP), c);

        //Setting title for the application
        //Adding TITLE to cell (1, 0) in main JPanel
        c.gridx = 1;
        c.gridy = 0;
        JLabel t = new JLabel("Piccollage", SwingConstants.CENTER);
        t.setFont(new Font("Helvetica", Font.BOLD, 40));
        mainJPanel.add(t, c);

        //Adding 'SAVE', 'DELETE' button to panel
        // Adding this panel to cell (2, 0) in main JPanel
        c.gridx = 2;
        c.gridy = 0;
        JButton button_SAVE = mainJPanel.createButton("SAVE");
        JButton button_DELETE = mainJPanel.createButton("DELETE");
        mainJPanel.add(mainJPanel.createButtonPanel(button_SAVE, button_DELETE), c);

        //Adding 'COLLAGE OPTIONS' to panel
        // Adding this panel to cell (0, 1) in main JPanel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        CollageOptions options = new CollageOptions(theApp);
        options.setPreferredSize(new Dimension(mainJPanel.getPreferredSize().width, 90));
        mainJPanel.add(options, c);

        //Adding 'NEXT', 'PREVIOUS' button to panel
        // Adding this panel to cell (2, 1) in main JPanel
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        JButton button_PREVIOUS = mainJPanel.createButton("PREVIOUS");
        JButton button_NEXT = mainJPanel.createButton("NEXT");
        mainJPanel.add(mainJPanel.createButtonPanel(button_PREVIOUS, button_NEXT), c);

        //Adding image to Scrollable area
        JLabel image = new JLabel();
        previewCollageJPanel = new PreviewImageJPanel(image);
        image.addMouseListener(new CollageMouseListener(theApp, options));
        previewCollageJPanel.setMinimumSize(new Dimension(800, 900));
        s = new JScrollPane(previewCollageJPanel);

        //Adding collage image preview panel to cell (0, 2)
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weighty = 1;
        mainJPanel.add(s, c);

        button_HOME.addActionListener(new BackToHomepageButtonHandler(theApp));
        button_PREVIOUS.addActionListener(new PreviousCollageButtonHandler(theApp));
        button_SAVE.addActionListener(new SaveCollageButtonHandler(theApp));
        button_NEXT.addActionListener(new NextCollageButtonHandler(theApp));
        button_HELP.addActionListener(new HelpButtonHandler(theApp));
        button_DELETE.addActionListener(new DiscardCollageButtonHandler(theApp));
    }

    public void setCollagePreviewImage(int index) {
        if (theApp.getGeneratedCollages().size() > 0) {
            this.index += index;
            if (this.index < 0)
                this.index = theApp.getGeneratedCollages().size() - 1;
            else
                this.index = this.index % theApp.getGeneratedCollages().size();
            previewCollageJPanel.setImage(Utils.resizeImage(
                    theApp.getGeneratedCollages().get(this.index).getCollageAsImage(),
                    previewCollageJPanel.getWidth(),
                    previewCollageJPanel.getHeight()));
            previewCollageJPanel.revalidate();
            previewCollageJPanel.repaint();
            s.repaint();
            s.revalidate();
        } else {
            previewCollageJPanel.setImage(null);
        }
    }

    public void setCollagePreviewImage(int index, Point p) {
        if (theApp.getGeneratedCollages().size() > 0) {
            this.index += index;
            if (this.index < 0)
                this.index = theApp.getGeneratedCollages().size() - 1;
            else
                this.index = this.index % theApp.getGeneratedCollages().size();
            previewCollageJPanel.setImage(Utils.resizeImage(
                    theApp.getGeneratedCollages().get(this.index).getCollageAsImage(p),
                    previewCollageJPanel.getWidth(),
                    previewCollageJPanel.getHeight()));
            previewCollageJPanel.revalidate();
            previewCollageJPanel.repaint();
            s.repaint();
            s.revalidate();
        } else {
            previewCollageJPanel.setImage(null);
        }
    }

    public int getCollagePreviewIndex() {
        return index;
    }

    public PreviewImageJPanel getPreviewCollageJPanel() {
        return previewCollageJPanel;
    }

    public JPanel getPage() {
        return mainJPanel;
    }

    public void setPointOnCollage(Point pointOnCollage) {
        this.pointOnCollage = pointOnCollage;
    }

    public Point getPointOnCollage() {
        return pointOnCollage;
    }

    public void loadingScreenforButtonsCollagePage() { //method is called on the button handlers that invoke it
        theApp.loadingScreenforButtons();
        /*JWindow loadingScreenforButtons = new JWindow();
        loadingScreenforButtons.getContentPane().add(
                new JLabel("", new ImageIcon(System.getProperty("user.dir") + "/trunk/resources/icons/loading2.gif"), SwingConstants.CENTER));
        loadingScreenforButtons.setBounds(1000, 75, 65, 65);
        new Thread(() -> {
            try {
                loadingScreenforButtons.setVisible(true);
                Thread.sleep(200);
                loadingScreenforButtons.setVisible(false);
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            loadingScreenforButtons.dispose();
        }).start();

         */
    }
}