package group9.ButtonHandling;

import group9.Piccollage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class LoadImageButtonHandler implements ActionListener {

    private final Piccollage theApp;
    private final ArrayList<File> imageFiles = new ArrayList<>();

    public LoadImageButtonHandler(Piccollage theApp) {
        this.theApp = theApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser c = new JFileChooser(".");
        c.setMultiSelectionEnabled(true);
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        c.setFileFilter(imageFilter);
        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int rVal = c.showOpenDialog(theApp);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            if (c.getSelectedFile().isDirectory()) {
                File[] files = c.getSelectedFile().listFiles();

                //Recursively check directory for image files
                assert files != null;
                theApp.addFiles(getFiles(files));
            } else {
                theApp.addFiles(c.getSelectedFiles());
            }
        }
    }

    //Recursively get all image from directory and sub-directories
    public File[] getFiles(File[] directory) {

        //Relevant file extensions
        String[] ACCEPTED_EXTENSIONS = {".png", ".jpg", ".jpeg"};

        //Loop through files in directory
        for (File f : directory) {

            //recursively call itself if the file is a directory
            if (f.isDirectory()) {
                getFiles(Objects.requireNonNull(f.listFiles()));
            } else {
                //Get file extension
                String path = f.getAbsolutePath();
                String ext = path.substring(path.lastIndexOf("."));

                //Compare file extension to list of accepted extensions
                for (String s : ACCEPTED_EXTENSIONS) {
                    if (s.equals(ext)) {

                        //Add file to arraylist
                        this.imageFiles.add(f);
                    }
                }
            }
        }
        //turn arraylist to string array
        return imageFiles.toArray(new File[0]);
    }
}