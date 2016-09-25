package com.venuyarra.aqa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class Launcher {
    public void launch() throws UnsupportedEncodingException {
        String[] contextPaths = new String[]{"classpath:ui-context.xml"};
        String path = new File(
                this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
        ).getName();
        String clientId = URLDecoder.decode(path, "UTF-8");
        System.out.println("ClientId=" + clientId);
        new ClassPathXmlApplicationContext(contextPaths);
    }
}
