package com.venuyarra.aqa;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by NIKOLAI on 20.09.2016.
 */
public class Launcher {
    public void launch() throws UnsupportedEncodingException {
        String[] contextPaths = new String[]{"classpath:ui-context.xml"};
        final File file = new File(
                this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
        );
        String jarName = file.getName();
        String clientId = URLDecoder.decode(jarName, "UTF-8");
        clientId = clientId.substring(0, clientId.lastIndexOf("."));
        System.out.println("ClientId=" + clientId);

        String absolutePath = file.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));

        System.setProperty("webdriver.ie.driver", absolutePath + File.separator + "IEDriverServer.exe");
        System.setProperty("webdriver.chrome.driver", absolutePath + File.separator + "chromedriver.exe");

        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextPaths);

        final SingleConnectionFactory connectionFactoryBean = applicationContext.getBean("connectionFactoryBean", SingleConnectionFactory.class);
        connectionFactoryBean.setClientId(clientId);

        final DefaultMessageListenerContainer myDurableConsumer = applicationContext.getBean("myDurableConsumer", DefaultMessageListenerContainer.class);
        myDurableConsumer.setClientId(clientId);
        myDurableConsumer.setMessageSelector("clientId='" + clientId + "'");
        myDurableConsumer.setDurableSubscriptionName(clientId);
        myDurableConsumer.start();

    }
}
