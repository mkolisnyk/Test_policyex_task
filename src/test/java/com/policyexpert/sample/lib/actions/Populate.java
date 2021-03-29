package com.policyexpert.sample.lib.actions;

import com.policyexpert.sample.lib.Configuration;
import com.policyexpert.sample.lib.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Populate {
    public static void asText(WebDriver driver, String field, Object value) {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).sendKeys(value.toString());
    }
    public static void asSelect(WebDriver driver, String field, Object value) {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//select", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value.toString());
    }
    public static void asDOBSelect(WebDriver driver, String field, Object values) {
        int index = 1;
        for(String value : (String[])values) {
            By locator = By.xpath(String.format("(//*[contains(text(), '%s')]/../..//select)[%d]", field, index));
            new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(value);
            index++;
        }
    }
    public static void asButton(WebDriver driver, String field, Object value){
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//button[text() = '%s']", field, value.toString()));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).click();
    };
}
