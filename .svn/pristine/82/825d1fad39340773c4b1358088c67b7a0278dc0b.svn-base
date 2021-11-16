package group9.HomepageWindow;

import group9.Piccollage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuBar extends JMenuBar {
    JMenu file, view, general, group;
    JMenuItem newCollage, exitProgram,
            fullScreen, minimize,
            ag, bm, et, ep, hk, lc, luc, ma, mt, ss, ym;

    public MenuBar() {
        initializeMenus();
        initializeMenuItems();

        this.add(file);
        this.add(view);
        this.add(general);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Menu");
        MenuBar m = new MenuBar();
        f.setJMenuBar(m);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void initializeMenus() {
        this.file = new JMenu("File");
        this.view = new JMenu("View");
        this.general = new JMenu("General");
        this.group = new JMenu("Group 09");
    }

    private void initializeMenuItems() {

        // Items under header 'File'
        this.newCollage = new CustomJMenuItem("New Collage");
        this.exitProgram = new CustomJMenuItem("Exit");
        file.add(newCollage);
        file.add(exitProgram);

        //Items under header 'View'
        this.fullScreen = new CustomJMenuItem("Full Screen");
        this.minimize = new CustomJMenuItem("Minimize");
        view.add(fullScreen);
        view.add(minimize);

        // Items under header 'General'
        general.add(group);

        //Items under header 'Group 09'
        this.ag = new JMenuItem("Arnav Ghosh - ag18071");
        this.bm = new JMenuItem("Bartosz Markiewicz - bm17247");
        this.et = new JMenuItem("Erik Tambla - et18310");
        this.ep = new JMenuItem("Erikas Pleikys - ep18373");
        this.hk = new JMenuItem("Hiraj Keshavji - hk16494");
        this.lc = new JMenuItem("Lewis Colley - lc17176");
        this.luc = new JMenuItem("Luke Cotton - lc18166");
        this.ma = new JMenuItem("Michael Allport - ma18533");
        this.mt = new JMenuItem("Mihai Tanasa - mt18886");
        this.ss = new JMenuItem("Sumayya Shakur - ss18559");
        this.ym = new JMenuItem("Yiangos Michael - gm18227");
        group.add(ag);
        group.add(bm);
        group.add(et);
        group.add(ep);
        group.add(hk);
        group.add(lc);
        group.add(luc);
        group.add(ma);
        group.add(mt);
        group.add(ss);
        group.add(ym);
    }
}

class CustomJMenuItem extends JMenuItem {
    public CustomJMenuItem(String text) {
        super(text);

        switch (text.toLowerCase()) {
            case "new collage":
                addActionListener(e -> {
                    try {
                        new Piccollage();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                break;
            case "exit":
                addActionListener(e -> System.exit(0));
                break;
            case "full screen":
                addActionListener(e -> {
                    JPopupMenu popupMenu = (JPopupMenu) getParent();
                    Component invoker = popupMenu.getInvoker();
                    JComponent invokerAsJComponent = (JComponent) invoker;
                    Container parentFrame = invokerAsJComponent.getTopLevelAncestor();
                    ((JFrame) parentFrame).setExtendedState(JFrame.MAXIMIZED_BOTH);
                });
                break;
            case "minimize":
                addActionListener(e -> {
                    JPopupMenu popupMenu = (JPopupMenu) getParent();
                    Component invoker = popupMenu.getInvoker();
                    JComponent invokerAsJComponent = (JComponent) invoker;
                    Container parentFrame = invokerAsJComponent.getTopLevelAncestor();
                    ((JFrame) parentFrame).setExtendedState(JFrame.ICONIFIED);
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + text.toLowerCase());
        }
    }
}