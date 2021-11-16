package group9.ButtonHandling;

import group9.GridCollageGenerator.Collage;
import group9.Piccollage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SaveCollageButtonHandler implements ActionListener {
    private final Piccollage theApp;

    public SaveCollageButtonHandler(Piccollage theApp) { this.theApp = theApp; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (theApp.getGeneratedCollages().size() > 0) {
            Collage collage = theApp.getGeneratedCollages().get(theApp.getCollagePage().getCollagePreviewIndex());
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter jpg = new FileNameExtensionFilter("JPG", ".jpg");
            fileChooser.setFileFilter(jpg);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", ".jpeg"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", ".png"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF", ".gif"));

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    collage.save(file.getAbsolutePath(),fileChooser.getFileFilter().getDescription().toLowerCase());
                    JOptionPane.showMessageDialog(null, "The collage has been successfully saved");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "The collage failed to save");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No file chosen");
            }
        } else JOptionPane.showMessageDialog(null, "No collage available to save");
    }
}