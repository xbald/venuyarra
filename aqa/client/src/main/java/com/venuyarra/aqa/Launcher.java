package com.venuyarra.aqa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class Launcher {
    public void launch() {
        String[] contextPaths = new String[]{"classpath:ui-context.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
    }
}
