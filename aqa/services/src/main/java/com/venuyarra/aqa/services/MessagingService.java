package com.venuyarra.aqa.services;

import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestSuite;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface MessagingService {
    void sendMessageToTopic(TestSuite testSuite, String clientId, BrowserType browser, String url);
}
