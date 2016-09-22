package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.dto.TestTask;
import com.venuyarra.aqa.services.MessagingService;
import com.venuyarra.aqa.services.SuiteDispatcherService;
import com.venuyarra.aqa.services.TestSuiteService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@Transactional
public class SuiteDispatcherServiceImpl implements SuiteDispatcherService {
    private TestSuiteService testSuiteService;
    private MessagingService messagingService;

    @Override
    public void dispatchSuite(TestTask testTask) {
        final TestSuite testSuite = testSuiteService.get(testTask.getTestSuiteId());
        messagingService.sendMessageToTopic(testSuite,testTask.getClientId(),testTask.getBrowserType());
    }

    public void setTestSuiteService(TestSuiteService testSuiteService) {
        this.testSuiteService = testSuiteService;
    }

    public void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }
}
