package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.TestSuite;
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
    public void DispatchSuite(Long suiteId, String clientId, String browser) {
        final TestSuite testSuite = testSuiteService.get(suiteId);
        messagingService.sendMessageToTopic(testSuite,clientId,browser);
    }
}
