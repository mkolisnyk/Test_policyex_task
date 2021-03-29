package com.policyexpert.sample.lib.actions;

import com.policyexpert.sample.lib.Configuration;
import com.policyexpert.sample.lib.data.EntryPair;
import org.junit.Assert;
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
import static com.policyexpert.sample.lib.actions.Get.sectionButton;

public class Verify {
    public static IVerify asLabelValue = (WebDriver driver, String field, Object value) -> {
        Assert.assertNotNull(label.get(driver, field, value).get(0));
    };

    public static IVerify asTextValue = (WebDriver driver, String field, Object value) -> {
        Assert.assertEquals(text.get(driver, field, value).get(0).getAttribute("value"), value);
    };

    public static IVerify asSelectedValue = (WebDriver driver, String field, Object value) -> {
        Select selectControl = new Select(select.get(driver, field, value).get(0));
        Assert.assertEquals(selectControl.getFirstSelectedOption().getText(), value);
    };
    public static IVerify asDOBSelectedValue = (WebDriver driver, String field, Object values) -> {
        int index = 0;
        for(String value : (String[])values) {
            Select selectControl = new Select(select.get(driver, field, value).get(index));
            Assert.assertEquals(selectControl.getFirstSelectedOption().getText(), value);
            index++;
        }
    };
    public static IVerify asButtonState = (WebDriver driver, String field, Object value) -> {
        WebElement element = button.get(driver, field, value).get(0);
        Assert.assertEquals(element.getAttribute("data-testid"), "selected");
    };

    public static IVerify asSectionButtonState = (WebDriver driver, String field, Object value) -> {
        WebElement element = sectionButton.get(driver, field, value).get(0);
        Assert.assertEquals(element.getAttribute("data-testid"), "selected");
    };
    public static IVerify asNothing = (WebDriver driver, String field, Object value) -> {
    };

    public static IVerify asFormValues = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryPair> valuesMap = (LinkedHashMap<String, EntryPair>) values;
        valuesMap.forEach((key, value) -> {
            value.getVerifyFunc().check(driver, key, value.getValues());
        });
    };
}
