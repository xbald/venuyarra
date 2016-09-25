package com.venuyarra.aqa.selenium.impl;

import com.google.common.base.Throwables;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.messaging.JmsMessageSender;
import com.venuyarra.aqa.selenium.SeleniumFactory;
import com.venuyarra.aqa.selenium.TestSuiteProcessor;
import com.venuyarra.aqa.selenium.WebDriverCommandExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class TestSuiteProcessorImpl implements TestSuiteProcessor {
    private SeleniumFactory webDriverFactory;
    private JmsMessageSender jmsMessageSender;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void processTestSuite(TestSuite testSuite, String clientId, String browser, String url) {
        List<TestCase> testCaseList = testSuite.getTestCaseList();
        WebDriver webDriver = null;
        try {
            webDriver = webDriverFactory.getWebDriver(browser);
            WebDriverCommandExecutor webDriverCommandExecutor = new WebDriverCommandExecutor(webDriver);
            for (TestCase testCase : testCaseList) {
                logger.debug("Processing test case id={} title='{}'", testSuite.getId(), testSuite.getTitle());
                List<SeleniumCommand> seleniumCommandList = testCase.getCommandList();
                if (!seleniumCommandList.isEmpty()) {
                    webDriver.get(url);
                    for (SeleniumCommand seleniumCommand : seleniumCommandList) {
                        logger.debug("Executing command {} ", seleniumCommand);
                        final ClientResponse clientResponse = seleniumCommand.execute(webDriverCommandExecutor);
                        clientResponse.setSuiteId(testSuite.getId());
                        try {
                            logger.debug("Result obtained {}", clientResponse);
                            jmsMessageSender.sendCommandResult(clientResponse, clientId, browser);
                        } catch (JmsException jmsException) {
                            //TODO Probably here should be some mechanism of storing message to temp.txt for further resending
                            logger.error("Unable to send message to server" + clientResponse, jmsException);
                            Throwables.propagate(jmsException);
                        }
                    }
                }
            }
        } catch (UnreachableBrowserException e) {
            for (TestCase aCase : testCaseList) {
                List<SeleniumCommand> seleniumCommandList = aCase.getCommandList();
                if (!seleniumCommandList.isEmpty()) {
                    for (SeleniumCommand seleniumCommand : seleniumCommandList) {
                        ClientResponse clientResponse = new ClientResponse();
                        clientResponse.setExecutionResult(ExecutionResult.FAILED);
                        clientResponse.setCommandId(seleniumCommand.getId());
                        clientResponse.setThrowable(e);
                        try {
                            jmsMessageSender.sendCommandResult(clientResponse, clientId, browser);
                        } catch (JmsException jmsException) {
                            //TODO Probably here should be some mechanism of storing message to temp.txt for further resending
                            logger.error("Unable to send mssageto server" + clientResponse, jmsException);
                            Throwables.propagate(jmsException);
                        }
                    }
                }
            }
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    public void setWebDriverFactory(SeleniumFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }

    public void setJmsMessageSender(JmsMessageSender jmsMessageSender) {
        this.jmsMessageSender = jmsMessageSender;
    }

}
