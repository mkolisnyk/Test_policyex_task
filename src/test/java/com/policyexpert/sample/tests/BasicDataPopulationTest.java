package com.policyexpert.sample.tests;

import com.policyexpert.sample.lib.TestCommon;
import com.policyexpert.sample.lib.data.BooleanChoiceValue;
import com.policyexpert.sample.lib.data.EntryGroup;
import com.policyexpert.sample.lib.data.TestData;
import com.policyexpert.sample.lib.data.questions.StatementAboutYouQuestions;
import com.policyexpert.sample.lib.data.questions.StatementAboutYourPropertyQuestions;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.policyexpert.sample.lib.actions.Populate.*;
import static com.policyexpert.sample.lib.actions.Verify.*;

public class BasicDataPopulationTest extends TestCommon {

    @Test
    public void testPositiveFlow() {
        asForm.set(driver, null, TestData.defaultTestData);
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }

    @Test
    public void testStatementsAboutYouDetailedFillAllAgreed() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
                putAll(TestData.defaultTestData);
            }
        };
        testData.put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNotForBusinessPurposes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneDeclaredBankrupcy, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceDeclined, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceCancelled, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneServedCountryCourtJudgement, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneConvictedWithAnyOffence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));

        asForm.set(driver, null, TestData.defaultTestData);

        testData.remove("Statements about you");
        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }
    @Test
    public void testStatementsAboutYouDetailedFillDetails() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
                putAll(TestData.defaultTestData);
            }
        };
        testData.put(StatementAboutYouQuestions.isNotForBusinessPurposes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));
        testData.put(StatementAboutYouQuestions.whatTypeOfBusiness, new EntryGroup(asSelect, asSelectedValue, "Other"));
        testData.put(StatementAboutYouQuestions.numberOfEmployees, new EntryGroup(asSelect, asSelectedValue, "5"));
        testData.put(StatementAboutYouQuestions.numberOfDailyVisitors, new EntryGroup(asSelect, asSelectedValue, "20"));

        testData.put(StatementAboutYouQuestions.isNoneDeclaredBankrupcy, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));
        testData.put(StatementAboutYouQuestions.bankrupsyType, new EntryGroup(asSelect, asSelectedValue, "Commercial"));
        testData.put(StatementAboutYouQuestions.hasBeenDischarged, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
        testData.put(StatementAboutYouQuestions.dischargeDate, new EntryGroup(asText, asTextValue, "2020-10-05"));

        testData.put(StatementAboutYouQuestions.isNoneInsuranceDeclined, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNoneInsuranceCancelled, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNoneServedCountryCourtJudgement, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNoneConvictedWithAnyOffence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IDisagree));
        testData.put(StatementAboutYouQuestions.numberOfCCJs, new EntryGroup(asSelect, asSelectedValue, "1"));
        testData.put(StatementAboutYouQuestions.valueOfCCJ, new EntryGroup(asSelect, asSelectedValue, "Below £1,000"));

        asForm.set(driver, null, TestData.defaultTestData);
        testData.remove("Statements about you");

        asFormValues.check(driver, null, TestData.defaultTestData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }

    @Test
    public void testStatementsAboutYourPropertyAllAgreed() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
                putAll(TestData.defaultTestData);
            }
        };
        testData.put("Statements about your property", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYourPropertyQuestions.isMainResidence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.areWallsBrickStoneConcrete, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isRoofMadeOfTileOrSlate, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isPropertyInGoodState, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isNoBoundaryWallAffectedByFlooding, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isNotSubjectToSubsidence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.areNeighboursNotSubjectOfSubsidence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.noCrackingInWalls, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isNotListedBuilding, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYourPropertyQuestions.isNotUndergoingBuildingWork, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));

        asForm.set(driver, null, testData);

        testData.remove("Statements about your property");
        asFormValues.check(driver, null, testData);
        asButton.set(driver, "Go to compare quotes", "Go to compare quotes");
        asLabelValue.check(driver, "Sorry, quotes unavailable", null);
    }
}
