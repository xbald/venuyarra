package com.venuyarra.aqa.selenium;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.ValidationCommand;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by NIKOLAI on 22.09.2016.
 */
public class CommandsExecutionTest extends BaseTest {

    @Autowired
    TestSuiteProcessor testSuiteProcessor;

    @Test
    public void validationCommandTest() {
        ValidationCommand validationCommand = createValidationCommand();

        final WebDriver webDriver = getWebDriverFactory().getWebDriver("firefox");
        webDriver.get("https://www.yahoo.com/");
        WebDriverCommandExecutor commandExecutor
                = new WebDriverCommandExecutor(webDriver);

        final ClientResponse clientResponse = validationCommand.execute(commandExecutor);
        Assert.assertEquals(
                clientResponse.getExecutionResult(),
                ExecutionResult.PASSED,
                "Invalid execution result"
        );

        webDriver.quit();

    }

    @Test
    public void enterCommandTest() {
        EnterCommand command = createEnterCommand();

        final WebDriver webDriver = getWebDriverFactory().getWebDriver("firefox");
        webDriver.get("https://www.yahoo.com/");
        WebDriverCommandExecutor commandExecutor
                = new WebDriverCommandExecutor(webDriver);

        final ClientResponse clientResponse = command.execute(commandExecutor);
        Assert.assertEquals(
                clientResponse.getExecutionResult(),
                ExecutionResult.PASSED,
                "Invalid execution result"
        );

        ValidationCommand validationCommand = createValidationCommand();
        validationCommand.setLocatorType("xpath");
        validationCommand.setLocatorValue("//input[@id='uh-search-box']/following-sibling::div/div/ul/li[1]//b");
        validationCommand.setExpectedResult("google");

        final ClientResponse response = validationCommand.execute(commandExecutor);
        Assert.assertEquals(
                response.getExecutionResult(),
                ExecutionResult.PASSED,
                "Invalid execution result"
        );

        webDriver.quit();
    }

    @Test
    public void clickCommandTest() throws InterruptedException {
        final WebDriver webDriver = getWebDriverFactory().getWebDriver("firefox");

        WebDriverCommandExecutor commandExecutor = new WebDriverCommandExecutor(webDriver);
        webDriver.get("https://www.yahoo.com/");

        ClickCommand clickCommand = createClickCommand();

        final ClientResponse clientResponse = clickCommand.execute(commandExecutor);
        Assert.assertEquals(
                clientResponse.getExecutionResult(),
                ExecutionResult.PASSED,
                "Invalid execution result"
        );
        Thread.sleep(2000);
        final String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(
                currentUrl,
                "https://www.yahoo.com/news/",
                "Invalid current URL"
        );
    }


    private EnterCommand createEnterCommand() {
        EnterCommand enterCommand = new EnterCommand();
        enterCommand.setLocatorType("id");
        enterCommand.setLocatorValue("uh-search-box");
        enterCommand.setValue("Google");

        return enterCommand;
    }

    private ClickCommand createClickCommand() {
        ClickCommand clickCommand = new ClickCommand();

        clickCommand.setLocatorType("xpath");
        clickCommand.setLocatorValue("//div[@class='App-Bd']//a[@href='https://www.yahoo.com/news/']");

        return clickCommand;
    }

    private ValidationCommand createValidationCommand() {
        ValidationCommand validationCommand = new ValidationCommand();
        validationCommand.setLocatorType("xpath");
        validationCommand.setLocatorValue("//div[@id='mega-topbar']/ul[1]/li[1]/a");
        validationCommand.setExpectedResult("Home");
        return validationCommand;
    }
}
