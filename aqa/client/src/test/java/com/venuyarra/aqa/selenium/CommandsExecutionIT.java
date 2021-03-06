package com.venuyarra.aqa.selenium;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.Parameter;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.dto.ValidationCommand;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NIKOLAI on 22.09.2016.
 */
public class CommandsExecutionIT extends BaseTest {

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
        validationCommand.setLocatorList(
                Collections.singletonList(
                        new Parameter(
                                4L,
                                "xpath",
                                "//input[@id='uh-search-box']/following-sibling::div/div/ul/li[1]//b")
                )
        );
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

    @Test
    public void severalCommandsTest() {
        final WebDriver webDriver = getWebDriverFactory().getWebDriver("firefox");

        WebDriverCommandExecutor commandExecutor = new WebDriverCommandExecutor(webDriver);
        webDriver.get("https://www.yahoo.com/");

        List<SeleniumCommand> commandList = new ArrayList<>();
        commandList.addAll(Arrays.asList(
                createValidationCommand(),
                createEnterCommand(),
                createClickCommand()
        ));

        List<ClientResponse> clientResponseList =
                commandList
                        .stream()
                        .map(seleniumCommand -> seleniumCommand.execute(commandExecutor))
                        .collect(Collectors.toList());

        clientResponseList.forEach(System.out::println);

        webDriver.quit();
    }


    private EnterCommand createEnterCommand() {
        EnterCommand enterCommand = new EnterCommand();
        enterCommand.setLocatorList(Collections.singletonList(new Parameter(1L, "id", "uh-search-box")));
        enterCommand.setValue("Google");

        return enterCommand;
    }

    private ClickCommand createClickCommand() {
        ClickCommand clickCommand = new ClickCommand();

        clickCommand.setLocatorList(
                Collections.singletonList(
                        new Parameter(
                                2L,
                                "xpath",
                                "//div[@class='App-Bd']//a[@href='https://www.yahoo.com/news/']"
                        )
                )
        );

        return clickCommand;
    }

    private ValidationCommand createValidationCommand() {
        ValidationCommand validationCommand = new ValidationCommand();
        validationCommand.setLocatorList(
                Collections.singletonList(
                        new Parameter(
                                2L,
                                "xpath",
                                "//div[@id='mega-topbar']/ul[1]/li[1]/a")
                )
        );
        validationCommand.setExpectedResult("Home");
        return validationCommand;
    }
}
