package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@Transactional
public class MessagingServiceImpl implements MessagingService {
    private JmsTemplate aqaJmsTemplate;

    @Override
    public void sendMessageToTopic(TestSuite testSuite, String clientId, String browser) {
        aqaJmsTemplate.send(
                session -> {
                    TextMessage message = session.createTextMessage();
                    JAXBContext jaxbContext = null;
                    try {
                        jaxbContext = JAXBContext.newInstance(TestCase.class);
                        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                        StringWriter sw = new StringWriter();
                        jaxbMarshaller.marshal(testSuite, sw);

                        message.setText(sw.toString());
                        message.setStringProperty("clientId", clientId);
                        message.setStringProperty("browser", browser);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    return message;
                }
        );
    }

    public void setAqaJmsTemplate(JmsTemplate aqaJmsTemplate) {
        this.aqaJmsTemplate = aqaJmsTemplate;
    }

}
