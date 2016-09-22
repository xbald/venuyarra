package com.venuyarra.aqa.selenium;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.dto.ValidationCommand;
import com.venuyarra.aqa.executor.SeleniumCommandExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Select;

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

    private final WebDriver webDriver;

    public WebDriverCommandExecutor(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void executeSteps(List<SeleniumCommand> commandList) {
        for (SeleniumCommand seleniumCommand : commandList) {
            seleniumCommand.execute(this);
        }
    }

    @Override
    public ClientResponse execute(ClickCommand command) {
        ClientResponse clientResponse = new ClientResponse();


        String locatorType = command.getLocatorType();
        String locatorValue = command.getLocatorValue();
        final WebElement webElement = webDriver.findElement(LocatorResolver.resolve(locatorType, locatorValue));

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            webElement.click();
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }
        return clientResponse;
    }

    @Override
    public ClientResponse execute(EnterCommand command) {
        ClientResponse clientResponse = new ClientResponse();


        String locatorType = command.getLocatorType();
        String locatorValue = command.getLocatorValue();
        final WebElement webElement = webDriver.findElement(LocatorResolver.resolve(locatorType, locatorValue));

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            webElement.clear();
            webElement.sendKeys(command.getValue());
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }
        return clientResponse;
    }

    @Override
    public ClientResponse execute(ValidationCommand command) {
        ClientResponse clientResponse = new ClientResponse();

        String locatorType = command.getLocatorType();
        String locatorValue = command.getLocatorValue();
        final WebElement webElement = webDriver.findElement(LocatorResolver.resolve(locatorType, locatorValue));

        try {
            final String text = webElement.getText();
            final String expectedText = command.getExpectedResult();
            assertThat(
                    "Values not equal. Expected '" + expectedText + "' but found '" + text,
                    expectedText.equals(text)
            );
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }
        return clientResponse;
    }

    @Override
    public ClientResponse execute(SelectCommand command) {
        ClientResponse clientResponse = new ClientResponse();

        String locatorType = command.getLocatorType();
        String locatorValue = command.getLocatorValue();
        final WebElement webElement = webDriver.findElement(LocatorResolver.resolve(locatorType, locatorValue));

        try {
            assertThat(webElement, should(displayed()).whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2))));
            Select select = new Select(webElement);
            select.selectByValue(command.getValue());
            clientResponse.setExecutionResult(ExecutionResult.PASSED);
        } catch (Throwable throwable) {
            clientResponse.setExecutionResult(ExecutionResult.FAILED);
            clientResponse.setThrowable(throwable);
        }
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
