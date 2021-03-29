package com.policyexpert.sample.lib.actions;

import org.openqa.selenium.WebDriver;

public interface IPopulate {
    void set(WebDriver driver, String field, Object value);
}
