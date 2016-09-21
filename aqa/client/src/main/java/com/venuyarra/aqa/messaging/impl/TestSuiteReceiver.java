package com.venuyarra.aqa.messaging.impl;

import com.google.common.base.Throwables;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.selenium.TestSuiteProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class TestSuiteReceiver implements MessageListener {
    private TestSuiteProcessor testSuiteProcessor;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                final String testSuiteXml = ((TextMessage) message).getText();
                TestSuite testSuite = JAXB.unmarshal(testSuiteXml, TestSuite.class);
                final String browser = message.getStringProperty("browser");
                final String url = message.getStringProperty("url");
                final String clientId = message.getStringProperty("clientId");
                try {
                    testSuiteProcessor.processTestSuite(testSuite, clientId, browser, url);
                } catch (Throwable e) {
                    //here we need just log error because all actions done even with error
                    logger.error(e.getMessage(), e);
                }

            }
        } catch (JMSException e) {
            Throwables.propagate(e);
        }

    }

    public void setTestSuiteProcessor(TestSuiteProcessor testSuiteProcessor) {
        this.testSuiteProcessor = testSuiteProcessor;
    }
}
