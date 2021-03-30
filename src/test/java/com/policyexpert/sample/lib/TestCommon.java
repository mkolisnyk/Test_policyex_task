package com.policyexpert.sample.lib;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCommon {
    protected WebDriver driver;
    @Before
    public void setUp() throws Exception {
        String browser = Configuration.get("browser");
        DesiredCapabilities cap = new DesiredCapabilities();
        if (browser.equals("remote")) {
            cap.setBrowserName(Configuration.get("remote_browser"));
        }
        Driver.add(browser, cap);
        driver = Driver.current();
        driver.get(Configuration.get("url"));
//
//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);
//        driver.get(Configuration.get("url"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
