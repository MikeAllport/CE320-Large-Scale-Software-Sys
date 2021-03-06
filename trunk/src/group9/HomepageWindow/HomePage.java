package group9.HomepageWindow;

import group9.ButtonHandling.*;
import group9.CustomComponents.CustomJPanel;
import group9.CustomComponents.PreviewImageJPanel;
import group9.Piccollage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomePage {
    private final CustomJPanel mainJPanel;

    public HomePage(Piccollage theApp) {
        this.mainJPanel = new CustomJPanel();
        mainJPanel.setPreferredSize(theApp.getPreferredSize());

        GridBagConstraints c = mainJPanel.getConstraints();

        //Adding 'load image', 'delete image', 'clear images" button to panel
        // Adding this panel to cell (0, 0) in main JPanel
        c.gridx = 0;
        c.gridy = 0;
        JButton button_LOAD = mainJPanel.createButton("LOAD");
        JButton button_DELETE = mainJPanel.createButton("DELETE");
        JButton button_CLEAR = mainJPanel.createButton("CLEAR");
        mainJPanel.add(mainJPanel.createButtonPanel(button_LOAD, button_DELETE, button_CLEAR), c);

        //'help' button
        JButton button_HELP = mainJPanel.createButton("HELP");

        //Adding 'help' button to cell (2, 0)
        c.gridx = 2;
        c.gridy = 0;
        mainJPanel.add(button_HELP, c);

        //Setting title for the application
        c.gridx = 1;
        c.gridy = 0;
        JLabel t = new JLabel("Piccollage", SwingConstants.CENTER);
        t.setFont(new Font("Helvetica", Font.BOLD, 40));
        mainJPanel.add(t, c);

        c.weightx = 0.5;
        c.weighty = 0.5;

        c.gridx = 0;
        c.gridy = 1;
        ImageList imageThumbnails = new ImageList();

        JLabel image = new JLabel(); //Preview of selected image in JList

        //Initialising JFileList
        JList<File> filesList = new JList<>(theApp.getFileNamesModel());
        filesList.setCellRenderer(mainJPanel.createListRenderer());
        filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filesList.addListSelectionListener(new DisplaySelectedImageHandler(theApp, image, filesList));

        //Adding List to Scrollable area
        JScrollPane s = new JScrollPane(filesList);
        s.setBorder(new EmptyBorder(10, 10, 10, 10));
        imageThumbnails.add(s, BorderLayout.CENTER);

        mainJPanel.add(imageThumbnails, c);


        //'generate collage' button
        JButton generateBtn = mainJPanel.createButton("generate");
        generateBtn.addActionListener(new GenerateCollageButtonHandler(theApp));

        //adding preview image panel to main JPanel
        PreviewImageJPanel previewImagesJPanel = new PreviewImageJPanel(image);
        previewImagesJPanel.setMaximumSize(new Dimension(500, 500));
        previewImagesJPanel.add(generateBtn, BorderLayout.PAGE_END);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        mainJPanel.add(previewImagesJPanel, c);

        c.gridwidth = 1;

        //Adding in buttonHandlers for their respective buttons
        button_LOAD.addActionListener(new LoadImageButtonHandler(theApp));
        button_DELETE.addActionListener(new DeleteImageButtonHandler(theApp, filesList));
        button_HELP.addActionListener(new HelpButtonHandler(theApp));
        button_CLEAR.addActionListener(new ClearAllButtonHandler(theApp));
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();
        f.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        HomePage page = new HomePage(new Piccollage());
        f.add(page.getPage());
        f.setVisible(true);
        f.pack();
    }

    public JPanel getPage() {
        return mainJPanel;
    }
}