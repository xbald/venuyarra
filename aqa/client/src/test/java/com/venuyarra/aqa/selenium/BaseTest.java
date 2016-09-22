package com.venuyarra.aqa.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by NIKOLAI on 22.09.2016.
 */
@ContextConfiguration(locations = "classpath:ui-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SeleniumFactory webDriverFactory;

    public SeleniumFactory getWebDriverFactory() {
        return webDriverFactory;
    }

    public void setWebDriverFactory(SeleniumFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }
}
