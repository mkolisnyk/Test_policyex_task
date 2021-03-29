package com.policyexpert.sample.tests;

import com.policyexpert.sample.lib.TestCommon;
import org.junit.Test;

import static com.policyexpert.sample.lib.actions.Populate.asButton;
import static com.policyexpert.sample.lib.actions.Verify.asLabelValue;

public class MandatoryFieldsTest extends TestCommon {

    @Test
    public void testPositiveFlow() {
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }
}
