package com.policyexpert.sample.lib.actions;

import com.policyexpert.sample.lib.Configuration;
import com.policyexpert.sample.lib.data.EntryGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.policyexpert.sample.lib.actions.Get.*;

public class Populate {
    public static IPopulate asText = (WebDriver driver, String field, Object value) -> {
        text.get(driver, field, value).get(0).sendKeys(value.toString());
    };

    public static IPopulate asLookupText = (WebDriver driver, String field, Object value) -> {
        text.get(driver, field, value).get(0).sendKeys(value.toString());
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//li", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).click();
    };

    public static IPopulate asSelect = (WebDriver driver, String field, Object value) -> {
        Select selectControl = new Select(select.get(driver, field, value).get(0));
        selectControl.selectByVisibleText(value.toString());
    };
    public static IPopulate asDOBSelect = (WebDriver driver, String field, Object values) -> {
        int index = 0;
        for(String value : (String[])values) {
            Select selectControl = new Select(select.get(driver, field, value).get(index));
            selectControl.selectByVisibleText(value);
            index++;
        }
    };
    public static IPopulate asButton = (WebDriver driver, String field, Object value) -> {
        WebElement element = button.get(driver, field, value).get(0);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    };

    public static IPopulate asSectionButton = (WebDriver driver, String field, Object value) -> {
        WebElement element = sectionButton.get(driver, field, value).get(0);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    };

    public static IPopulate asForm = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryGroup> valuesMap = (LinkedHashMap<String, EntryGroup>) values;
        valuesMap.forEach((key, value) -> {
            value.getPopulateFunc().set(driver, key, value.getValues());
        });
    };
}
