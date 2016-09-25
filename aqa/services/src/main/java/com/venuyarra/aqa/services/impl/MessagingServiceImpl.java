package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.services.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.TextMessage;
import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@Transactional
public class MessagingServiceImpl implements MessagingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate aqaJmsTemplate;

    @Override
    public void sendMessageToTopic(TestSuite testSuite, String clientId, BrowserType browser, String url) {
        aqaJmsTemplate.send(
                session -> {
                    TextMessage message = session.createTextMessage();

                    StringWriter sw = new StringWriter();
                    JAXB.marshal(testSuite, sw);
                    final String text = sw.toString();
                    message.setText(text);

                    logger.info("Sending suite id={} as xml={}", testSuite.getId(), text);
                    message.setStringProperty("clientId", clientId);
                    message.setStringProperty("browser", browser.name());
                    message.setStringProperty("url", url);

                    return message;
                }
        );
    }

    public void setAqaJmsTemplate(JmsTemplate aqaJmsTemplate) {
        this.aqaJmsTemplate = aqaJmsTemplate;
    }

}
