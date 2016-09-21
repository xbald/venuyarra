package com.venuyarra.aqa.messaging.impl;

import com.google.common.base.Throwables;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.messaging.JmsMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@Transactional
public class JmsMessageSenderImpl implements JmsMessageSender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate aqaJmsTemplate;

    @Override
    public void sendCommandResult(ClientResponse clientResponse, String clientId, String browser) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClientResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(clientResponse, sw);

            final String text = sw.toString();

            aqaJmsTemplate.send(
                    session -> {
                        try {
                            TextMessage message = session.createTextMessage();
                            message.setText(text);
                            message.setStringProperty("clientId", clientId);
                            message.setStringProperty("browser", browser);
                            return message;
                        } catch (JMSException e) {
                            //TODO Probably here should be some mechanism of storing message to temp.txt for further resending
                            logger.error("Unable to create response message from " + text);
                            Throwables.propagate(e);
                        }
                        return null;
                    }

            );
        } catch (JAXBException e) {
            throw new RuntimeException("Cannot marshall client respomse", e);
        }
    }

    public void setAqaJmsTemplate(JmsTemplate aqaJmsTemplate) {
        this.aqaJmsTemplate = aqaJmsTemplate;
    }
}
