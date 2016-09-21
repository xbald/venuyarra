package com.venuyarra.aqa.messaging;

import com.venuyarra.aqa.dto.ClientResponse;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface JmsMessageSender {
    void sendCommandResult(ClientResponse clientResponse, String clientId, String browser);
}
