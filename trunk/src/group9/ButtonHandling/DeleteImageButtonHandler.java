package group9.ButtonHandling;

import group9.Piccollage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteImageButtonHandler implements ActionListener {
    private final Piccollage theApp;
    private final JList<File> filesList;

    public DeleteImageButtonHandler(Piccollage theApp, JList filesList) {
        this.theApp = theApp;
        this.filesList = filesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theApp.loadingScreenforButtons();
        if (filesList.getSelectedValue() != null) {
            File file = filesList.getSelectedValue();
            if (filesList.getModel().getSize() - 1 > filesList.getSelectedIndex()) {
                filesList.setSelectedIndex(filesList.getSelectedIndex() + 1);
            } else if (filesList.getModel().getSize() == 1) {
                filesList.clearSelection();
            } else {
                filesList.setSelectedIndex(filesList.getSelectedIndex() - 1);
            }
            JOptionPane.showMessageDialog(null, "The selected image has been deleted");
            theApp.deleteFile(file);
        }
    }
}