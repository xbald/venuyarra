package com.venuyarra.aqa;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.UnsupportedEncodingException;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class ClientApp {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Launcher launcher = new Launcher();
        launcher.launch();
    }
}