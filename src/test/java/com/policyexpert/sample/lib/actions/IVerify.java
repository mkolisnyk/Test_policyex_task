package com.policyexpert.sample.lib.actions;

import org.openqa.selenium.WebDriver;

public interface IVerify {
    void check(WebDriver driver, String field, Object value);
}
