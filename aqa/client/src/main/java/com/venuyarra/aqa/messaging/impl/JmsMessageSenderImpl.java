package com.venuyarra.aqa.messaging.impl;

import com.google.common.base.Throwables;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.messaging.JmsMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
//@Transactional
public class JmsMessageSenderImpl implements JmsMessageSender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate aqaJmsTemplate;

    @Override
    public void sendCommandResult(ClientResponse clientResponse, String clientId, String browser) {
        StringWriter sw = new StringWriter();
        JAXB.marshal(clientResponse, sw);
        final String text = sw.toString();
        aqaJmsTemplate.send(
                session -> {
                    try {
                        TextMessage message = session.createTextMessage();
                        message.setText(text);
                        message.setStringProperty("clientId", clientId);
                        message.setStringProperty("browser", browser);
                        logger.debug("Sending response to server as xml {}", text);
                        return message;
                    } catch (JMSException e) {
                        //TODO Probably here should be some mechanism of storing message to temp.txt for further resending
                        logger.error("Unable to create response message " + text);
                        Throwables.propagate(e);
                    }
                    return null;
                }

        );
    }

    public void setAqaJmsTemplate(JmsTemplate aqaJmsTemplate) {
        this.aqaJmsTemplate = aqaJmsTemplate;
    }
}
