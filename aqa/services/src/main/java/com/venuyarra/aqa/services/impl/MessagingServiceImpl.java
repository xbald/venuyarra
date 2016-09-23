package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.services.MessagingService;
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
    private JmsTemplate aqaJmsTemplate;

    @Override
    public void sendMessageToTopic(TestSuite testSuite, String clientId, BrowserType browser) {
        aqaJmsTemplate.send(
                session -> {
                    TextMessage message = session.createTextMessage();

                    StringWriter sw = new StringWriter();
                    JAXB.marshal(testSuite, sw);
                    message.setText(sw.toString());

                    message.setStringProperty("clientId", clientId);
                    message.setStringProperty("browser", browser.toString());

                    return message;
                }
        );
    }

    public void setAqaJmsTemplate(JmsTemplate aqaJmsTemplate) {
        this.aqaJmsTemplate = aqaJmsTemplate;
    }

}
