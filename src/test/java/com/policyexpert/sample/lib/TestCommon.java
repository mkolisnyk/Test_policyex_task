package com.policyexpert.sample.lib;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TestCommon {
    protected WebDriver driver;
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
//        Driver.add(Configuration.get("browser"), cap);
//        driver = Driver.current();
//        driver.get(Configuration.get("url"));
        cap.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);
        driver.get(Configuration.get("url"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
