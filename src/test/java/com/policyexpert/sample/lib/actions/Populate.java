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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Populate {
    public static IPopulate asText = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).sendKeys(value.toString());
    };

    // list-group-item

    public static IPopulate asLookupText = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).sendKeys(value.toString());
        locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//li", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).click();
    };


    public static IPopulate asSelect = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//select", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value.toString());
    };
    public static IPopulate asDOBSelect = (WebDriver driver, String field, Object values) -> {
        int index = 1;
        for(String value : (String[])values) {
            By locator = By.xpath(String.format("(//*[contains(text(), '%s')]/../..//select)[%d]", field, index));
            new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(value);
            index++;
        }
    };
    public static IPopulate asButton = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/..//button[text() = '%s'] |" +
                " //*[contains(text(), '%s')]/../..//button[text() = '%s']", field, value.toString(), field, value.toString()));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    };

    public static IPopulate asSectionButton = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/..//button[text() = '%s']",
                field, value.toString(), field, value.toString()));

        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    };

    public static IPopulate asForm = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryPair> valuesMap = (LinkedHashMap<String, EntryPair>) values;
        valuesMap.forEach((key, value) -> {
            value.getPopulateFunc().set(driver, key, value.getValues());
        });
    };
}
