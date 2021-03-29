package com.policyexpert.sample.tests;

import com.policyexpert.sample.lib.TestCommon;
import com.policyexpert.sample.lib.data.TestData;
import org.junit.Test;

import static com.policyexpert.sample.lib.actions.Populate.asButton;
import static com.policyexpert.sample.lib.actions.Populate.asForm;
import static com.policyexpert.sample.lib.actions.Verify.asFormValues;
import static com.policyexpert.sample.lib.actions.Verify.asLabelValue;

public class BasicDataPopulationTest extends TestCommon {

    @Test
    public void testPositiveFlow() {
        asForm.set(driver, null, TestData.defaultTestData);
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }

    @Test
    public void testStatementsAboutYouDetailedFill() {
        asForm.set(driver, null, TestData.defaultTestData);
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }

    @Test
    public void testStatementsAboutYourPropertyDetailedFill() {
        asForm.set(driver, null, TestData.defaultTestData);
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }

    @Test
    public void testSafetyAndSecurityFill() {
        asForm.set(driver, null, TestData.defaultTestData);
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }
}
