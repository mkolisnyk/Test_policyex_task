package com.policyexpert.sample.lib.actions;

import com.policyexpert.sample.lib.data.EntryGroup;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.policyexpert.sample.lib.actions.Get.*;

public class Verify {
    public static IVerify asLabelValue = (WebDriver driver, String field, Object value) -> {
        Assert.assertNotNull(label.get(driver, field, value).get(0));
    };
    public static IVerify asErrorLabelValue = (WebDriver driver, String field, Object value) -> {
        Assert.assertEquals(errorLabel.get(driver, field, value).get(0).getText(), value);
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
        Assert.assertEquals(
                String.format("Failed to verify '%s' button '%s' state", field, value),
                "selected",
                element.getAttribute("data-testid"));
    };

    public static IVerify asSectionButtonState = (WebDriver driver, String field, Object value) -> {
        WebElement element = sectionButton.get(driver, field, value).get(0);
        Assert.assertEquals(
                String.format("Failed to verify '%s' section button '%s' state", field, value),
                "selected",
                element.getAttribute("data-testid"));
    };
    public static IVerify asNothing = (WebDriver driver, String field, Object value) -> {
    };

    public static IVerify asFormValues = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryGroup> valuesMap = (LinkedHashMap<String, EntryGroup>) values;
        valuesMap.forEach((key, value) -> {
            value.getVerifyFunc().check(driver, key, value.getValues());
        });
    };
}
