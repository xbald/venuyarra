package com.venuyarra.aqa.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class MainFrame extends JFrame {
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 200));
        setState(Frame.NORMAL);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLocationRelativeTo(null);
    }

}
