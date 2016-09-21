package com.venuyarra.aqa.selenium;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class SeleniumFactory {
/*
    private final String browserType;
    private final Boolean isRemote;
    private final String hubUrl;
    private final String testSiteUrl;
    private final String osName;
    private final String browserVersion;
    private final Integer elementWaitSeconds;
    private final String ieDriverPath;
    private final String edgeDriverPath;
    private final String chromeDriverPath;

*/
/*
    public String getBrowserType() {
        return browserType;
    }
*/

/*
    public SeleniumFactory(
            String browserType,
            Boolean isRemote,
            String hubUrl,
            String testSiteUrl,
            String osName,
            String browserVersion,
            Integer elementWaitSeconds,
            String ieDriverPath,
            String edgeDriverPath,
            String chromeDriverPath
    ) {
        this.browserType = browserType;
        this.isRemote = isRemote;
        this.hubUrl = hubUrl;
        this.testSiteUrl = testSiteUrl;
        this.ieDriverPath = ieDriverPath;
        this.edgeDriverPath = edgeDriverPath;
        this.chromeDriverPath = chromeDriverPath;
        this.osName = osName.toUpperCase();
        this.browserVersion = browserVersion;
        this.elementWaitSeconds = elementWaitSeconds;
    }
*/

    public WebDriver getWebDriver(final String browserType) {

        DesiredCapabilities capabilities;
        String browserTypeLower = browserType.toLowerCase();
        browserTypeLower = browserTypeLower.replaceAll("\\d+", "");
        switch (browserTypeLower) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
/*
                capabilities.setCapability(CapabilityType.VERSION, browserVersion);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(osName));

                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
*/
                WebDriver chromeDriver = new ChromeDriver(capabilities);
//                chromeDriver.manage().timeouts().implicitlyWait(elementWaitSeconds, TimeUnit.SECONDS);
                return chromeDriver;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
/*
                capabilities.setCapability(CapabilityType.VERSION, browserVersion);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(osName));
                capabilities.setCapability("ignoreZoomSetting", true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, testSiteUrl);
                System.setProperty("webdriver.ie.driver", ieDriverPath);
*/
                WebDriver internetExplorerDriver = new InternetExplorerDriver(capabilities);
/*
                internetExplorerDriver.manage().timeouts().implicitlyWait(elementWaitSeconds, TimeUnit.SECONDS);
*/
                return internetExplorerDriver;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
/*
                capabilities.setCapability(CapabilityType.VERSION, browserVersion);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(osName));
*/
                WebDriver firefoxDriver = new FirefoxDriver(capabilities);
//                firefoxDriver.manage().timeouts().implicitlyWait(elementWaitSeconds, TimeUnit.SECONDS);
                return firefoxDriver;
            case "safari":
                capabilities = DesiredCapabilities.safari();
/*
                capabilities.setCapability(CapabilityType.VERSION, browserVersion);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(osName));
*/
                WebDriver safariDriver = new SafariDriver(capabilities);
//                safariDriver.manage().timeouts().implicitlyWait(elementWaitSeconds, TimeUnit.SECONDS);
                return safariDriver;
            case "edge":
                capabilities = DesiredCapabilities.edge();
/*
                capabilities.setCapability(CapabilityType.VERSION, browserVersion);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.valueOf(osName));
                System.setProperty("webdriver.edge.driver", edgeDriverPath);
*/
                WebDriver edgeDriver = new EdgeDriver(capabilities);
//                edgeDriver.manage().timeouts().implicitlyWait(elementWaitSeconds, TimeUnit.SECONDS);
                return edgeDriver;

            default:
                throw new IllegalArgumentException("Error: Unknown browser type");
        }
    }
}
