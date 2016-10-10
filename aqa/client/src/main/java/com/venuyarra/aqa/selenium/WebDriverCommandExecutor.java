package com.venuyarra.aqa.selenium;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.DoubleClickCommand;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.Parameter;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.ValidationCommand;
import com.venuyarra.aqa.executor.SeleniumCommandExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.matchers.decorators.MatcherDecoratorsBuilder.should;
import static ru.yandex.qatools.matchers.decorators.TimeoutWaiter.timeoutHasExpired;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class WebDriverCommandExecutor implements SeleniumCommandExecutor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WebDriver webDriver;

    public WebDriverCommandExecutor(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public ClientResponse execute(ClickCommand command) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setStartedAt(new Date());
        clientResponse.setCommandId(command.getId());
        logger.debug("Executing command {}", command);
        final WebElement webElement = locateElement(command.getLocatorList());
        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            webElement.click();
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }

        clientResponse.setFinishedAt(new Date());
        return clientResponse;
    }

    private WebElement locateElement(List<Parameter> locatorList) {
        List<By> bys = new ArrayList<>();
        for (Parameter parameter : locatorList) {
            final By by = LocatorResolver.resolve(parameter.getLocatorType(), parameter.getLocatorValue());
            bys.add(by);
        }
        return webDriver.findElement(new ByChained(bys.toArray(new By[]{})));
    }

    @Override
    public ClientResponse execute(EnterCommand command) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setStartedAt(new Date());
        clientResponse.setCommandId(command.getId());
        logger.debug("Executing command {}", command);

        final WebElement webElement = locateElement(command.getLocatorList());

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            webElement.clear();
            webElement.sendKeys(command.getValue());
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }

        clientResponse.setFinishedAt(new Date());
        return clientResponse;
    }

    @Override
    public ClientResponse execute(ValidationCommand command) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setCommandId(command.getId());
        clientResponse.setStartedAt(new Date());
        logger.debug("Executing command {}", command);

        try {
            final String text = getActualResult(command);
            clientResponse.setReturnedValue(text);
            final String expectedText = command.getExpectedResult();
            assertThat(
                    "Values not equal. Expected '" + expectedText + "' but found '" + text,
                    expectedText.equals(text)
            );
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            logger.debug("Validation failed with ", throwable);
            clientResponse.setThrowable(throwable);
        }

        clientResponse.setFinishedAt(new Date());
        return clientResponse;
    }

    private String getActualResult(ValidationCommand command) {
        final WebElement webElement = locateElement(command.getLocatorList());
        String attr = command.getAttribute();
        if (attr == null) {
            return webElement.getText();
        } else {
            if (attr.toLowerCase().equals("innerhtml")) {
                return webElement.getAttribute("innerHTML");
            } else {
                return webElement.getAttribute(attr);
            }
        }
    }

    @Override
    public ClientResponse execute(SelectCommand command) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setCommandId(command.getId());
        clientResponse.setStartedAt(new Date());
        logger.debug("Executing command {}", command);

        final WebElement webElement = locateElement(command.getLocatorList());

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            Select select = new Select(webElement);
            select.selectByValue(command.getValue());
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }

        clientResponse.setFinishedAt(new Date());
        return clientResponse;
    }

    @Override
    public ClientResponse execute(DoubleClickCommand doubleClickCommand) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setStartedAt(new Date());
        clientResponse.setCommandId(doubleClickCommand.getId());
        logger.debug("Executing command {}", doubleClickCommand);

        final WebElement webElement = locateElement(doubleClickCommand.getLocatorList());

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));

            Actions actions = new Actions(webDriver);
            actions.doubleClick(webElement).build().perform();

            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }

        clientResponse.setFinishedAt(new Date());
        return clientResponse;
    }

    private static class LocatorResolver {

        public static By resolve(String locatorType, String locatorValue) {
            switch (locatorType.toLowerCase()) {
                case "cssselector":
                    return By.cssSelector(locatorValue);
                case "id":
                    return By.id(locatorValue);
                case "xpath":
                    return By.xpath(locatorValue);
                case "linktext":
                    return By.linkText(locatorValue);
                case "classname":
                    return By.className(locatorValue);
                case "partiallinktext":
                    return By.partialLinkText(locatorValue);
                case "tagname":
                    return By.tagName(locatorValue);
                case "name":
                    return By.name(locatorValue);
                default:
                    throw new IllegalArgumentException("locator of '" + locatorType + "' type is not supported");

            }
        }
    }
}
