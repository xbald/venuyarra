package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.services.ResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;
import java.io.StringReader;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@Transactional
public class ResultsProcessor implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResultsService resultService;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                final String textClientResult = ((TextMessage) message).getText();
                logger.debug("Client response received {}", textClientResult);
                ClientResponse clientResponse = JAXB.unmarshal(
                        new StringReader(textClientResult),
                        ClientResponse.class
                );

                resultService.saveOrUpdateResult(clientResponse);

            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setResultService(ResultsService resultService) {
        this.resultService = resultService;
    }
}
