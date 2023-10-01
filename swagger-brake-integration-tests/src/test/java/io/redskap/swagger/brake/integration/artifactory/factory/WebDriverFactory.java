package io.redskap.swagger.brake.integration.artifactory.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private static final String CHROME_DRIVER_VERSION_ENV_VAR = "CHROME_DRIVER_VERSION";
    private static final String CHROME_BROWSER_VERSION_ENV_VAR = "CHROME_BROWSER_VERSION";
    public static final String DEFAULT_DRIVER_VERSION = "117.0.5938.132";
    public static final String DEFAULT_BROWSER_VERSION = "117.0.5938.132";

    public static WebDriver create() {
        WebDriverManager
                .chromedriver()
                .browserVersion(getBrowserVersion())
                .driverVersion(getDriverVersion())
                .setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1600,900");
        // https://stackoverflow.com/questions/75718422/org-openqa-selenium-remote-http-connectionfailedexception-unable-to-establish-w
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    private static String getDriverVersion() {
        String chromeDriverVersion = System.getenv(CHROME_DRIVER_VERSION_ENV_VAR);
        if (StringUtils.isBlank(chromeDriverVersion)) {
            chromeDriverVersion = DEFAULT_DRIVER_VERSION;
        }
        return chromeDriverVersion;
    }

    private static String getBrowserVersion() {
        String chromeBrowserVersion = System.getenv(CHROME_BROWSER_VERSION_ENV_VAR);;
        if (StringUtils.isBlank(chromeBrowserVersion)) {
            chromeBrowserVersion = DEFAULT_BROWSER_VERSION;
        }
        return chromeBrowserVersion;
    }
}
