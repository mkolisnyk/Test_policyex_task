package com.policyexpert.sample.tests;

import com.policyexpert.sample.lib.TestCommon;
import com.policyexpert.sample.lib.data.EntryGroup;
import com.policyexpert.sample.lib.data.questions.AboutYouQuestions;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.policyexpert.sample.lib.actions.Populate.*;
import static com.policyexpert.sample.lib.actions.Verify.asErrorLabelValue;
import static com.policyexpert.sample.lib.actions.Verify.asTextValue;

public class ErrorMessagesTest extends TestCommon {

    @Test
    public void testEmptyInputErrorLabels() {
        Map<String, String> expectedLabels = new LinkedHashMap<String, String>() {
            {
                put("Title", "Please let us know your title");
                put("First name", "Please let us know your first name");
                put("Last name", "Please let us know your last name");
                put("What is your date of birth?", "Please enter your date of birth");
                put("What is your marital status?", "Please answer this question to continue");
                put("What is your occupation?", "Please start typing and choose an occupation");
                put("Does anybody living in the property smoke?", "Please answer this question to continue");
                put("Main telephone number", "Insert only digits, up to a maximum of 15. Can start with '+' for international numbers.");
                put("What is your e-mail?", "Please enter a valid email address");
                put("Statements about you", "Please let us know your title");
                put("Address of property to be insured", "Please start to type in your address");
                put("Is this the same as your correspondence address?", "Please answer this question to continue");
                put("What type of property is it?", "Please answer this question to continue");
                put("Approximately in which year was your property built?", "Please answer this question to continue");
                put("How many bedrooms does your property have?", "Please answer this question to continue");
                put("Do you own the property?", "Please answer this question to continue");
                put("Who is the property occupied by?", "Please answer this question to continue");
                put("When is the property usually occupied?", "Please answer this question to continue");
                put("Are there trees taller than 10 metres within 5 metres of your property?", "Please answer this question to continue");
                put("Does your property have a flat roof?", "Please answer this question to continue");
                put("Statements about your property", "Please let us know your title");
                put("Do all externally accessible windows have key-operated locks on them?", "Please answer this question to continue");
                put("Is your property self-contained, i.e. it has its own front door with its own lockable entrance?", "Please answer this question to continue");
                put("Does your property have any French or patio doors?", "Please answer this question to continue");
                put("Does your property have any other external doors?", "Please answer this question to continue");
                put("Does your property have a working burglar alarm?", "Please answer this question to continue");
                put("Would you like to add a joint policyholder?", "Please answer this question to continue");
                put("How many consecutive years have you held home insurance?", "Please answer this question to continue");
                put("In the past 3 years, has anyone living at the property made any home insurance claims or reported any losses to an insurer?", "Please answer this question to continue");
                put("When would you like cover to start?", "Please enter a date up to 30 days in the future");
                put("Please select what type of home insurance you require", "Please answer this question to continue");
            }
        };
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        expectedLabels.forEach((key, value) -> {
            asErrorLabelValue.check(driver, key, value);
        });
    }

    @Test
    public void testImproperlyFormattedFields() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
//                putAll(TestData.defaultTestData);
//                // No error message appears here
//                put(AboutYouQuestions.FirstName, new EntryGroup(asText, asTextValue,"A 123"));
//                // No error message appears here
//                put(AboutYouQuestions.LastName, new EntryGroup(asText, asTextValue,"i%6dsfg"));
//                // No error message appears here
//                String date[] = new String[] {"30", "February", "1993"};
//                put(AboutYouQuestions.DateOfBirth, new EntryGroup(asDOBSelect, asDOBSelectedValue, date));
                put(AboutYouQuestions.Occupation, new EntryGroup(asText, asTextValue, "Something unknown"));
//                // No error message appears here
//                put(AboutYouQuestions.PhoneNumber, new EntryGroup(asText, asTextValue, "abcd12jfdhgd"));
                put(AboutYouQuestions.EMail, new EntryGroup(asText, asTextValue, "1_3.mail"));
            }
        };
        Map<String, String> expectedLabels = new LinkedHashMap<String, String>() {
            {
                put("First name", "Please let us know your first name");
                put("Last name", "Please let us know your last name");
                put("What is your date of birth?", "Please enter your date of birth");
                put("What is your occupation?", "Please start typing and choose an occupation");
                put("Main telephone number", "Insert only digits, up to a maximum of 15. Can start with '+' for international numbers.");
                put("What is your e-mail?", "Please enter a valid email address");
            }
        };
        asForm.set(driver, null, testData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        expectedLabels.forEach((key, value) -> {
            asErrorLabelValue.check(driver, key, value);
        });
    }
}
