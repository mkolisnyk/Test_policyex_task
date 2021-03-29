package com.policyexpert.sample.lib.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IGet {
    List<WebElement> get(WebDriver driver, String field, Object value);
}
