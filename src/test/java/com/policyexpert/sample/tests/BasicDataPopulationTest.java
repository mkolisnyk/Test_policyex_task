package com.policyexpert.sample.tests;

import static com.policyexpert.sample.lib.actions.Populate.*;

import com.policyexpert.sample.lib.Configuration;
import com.policyexpert.sample.lib.data.BooleanChoiceValue;
import com.policyexpert.sample.lib.data.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

@RunWith(JUnit4.class)
public class BasicDataPopulationTest {
    private WebDriver driver;
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
        // driver.quit();
    }

    @Test
    public void testPositiveFlow() {
        asForm.set(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
    }
}
