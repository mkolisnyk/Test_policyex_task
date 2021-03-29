package com.policyexpert.sample.lib.actions;

import com.policyexpert.sample.lib.Configuration;
import com.policyexpert.sample.lib.data.EntryPair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Get {
    public static IGet label = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };
    public static IGet text = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };

    public static IGet select = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//select", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };
    public static IGet button = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/..//button[text() = '%s'] |" +
                " //*[contains(text(), '%s')]/../..//button[text() = '%s']", field, value.toString(), field, value.toString()));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };

    public static IGet sectionButton = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/..//button[text() = '%s']",
                field, value.toString(), field, value.toString()));

        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };
}
