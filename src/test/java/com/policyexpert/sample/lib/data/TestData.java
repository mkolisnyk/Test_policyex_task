package com.policyexpert.sample.lib.data;
import com.policyexpert.sample.lib.data.questions.AboutYouQuestions;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.policyexpert.sample.lib.actions.Populate.*;
import static com.policyexpert.sample.lib.actions.Verify.*;

public class TestData {

    private static final String defaultEmail = "vketipisz@qmetric.co.uk";
    private static final String defaultAddress = "Autonomy Capital Research LLP, 110 Bishopsgate, London, EC2N 4AY";

    public static final Map<String, EntryGroup> defaultTestData = new LinkedHashMap<String, EntryGroup>() {
        {
            put(AboutYouQuestions.Title, new EntryGroup(asSelect, asSelectedValue,"Mr"));
            put(AboutYouQuestions.FirstName, new EntryGroup(asText, asTextValue,"Sample"));
            put(AboutYouQuestions.LastName, new EntryGroup(asText, asTextValue,"Name"));
            String[] date = {"27", "July", "1987"};
            put(AboutYouQuestions.DateOfBirth, new EntryGroup(asDOBSelect, asDOBSelectedValue, date));
            put(AboutYouQuestions.MaritalStatus, new EntryGroup(asSelect, asSelectedValue,"Single"));
            put(AboutYouQuestions.Occupation, new EntryGroup(asLookupText, asTextValue,"Construction Engineer"));
            put(AboutYouQuestions.AnyoneSmokes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put(AboutYouQuestions.PhoneNumber, new EntryGroup(asText, asTextValue, "00000000000"));
            put(AboutYouQuestions.EMail, new EntryGroup(asText, asTextValue, defaultEmail));

            put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IAgree));

            // About property section
            put("Address of property to be insured", new EntryGroup(asLookupText, asNothing, defaultAddress));
            put("Is this the same as your correspondence address?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("What type of property is it?", new EntryGroup(asSelect, asSelectedValue,"House")); // Enum
            put("Which of these best describes your property?", new EntryGroup(asSelect, asSelectedValue,"Detached house")); // Enum
            put("Approximately in which year was your property built?", new EntryGroup(asText, asTextValue,"1980"));
            put("How many bedrooms does your property have?", new EntryGroup(asSelect, asSelectedValue,"3"));
            put("Do you own the property?", new EntryGroup(asSelect, asSelectedValue,"Yes - mortgaged")); // Enum
            put("Who is the property occupied by?", new EntryGroup(asSelect, asSelectedValue,"You and your family")); // Enum
            put("When is the property usually occupied?", new EntryGroup(asSelect, asSelectedValue,"During the day")); // Enum
            put("Are there trees taller than 10 metres within 5 metres of your property?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have a flat roof?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("What percentage of your roof is flat?", new EntryGroup(asSelect, asSelectedValue,"Less than 20%")); // Enum

            // Statements about property
            put("Statements about your property", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IAgree));

            // About safety and security
            put("Do all externally accessible windows have key-operated locks on them?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("Is your property self-contained, i.e. it has its own front door with its own lockable entrance?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have any French or patio doors?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have any other external doors?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have a working burglar alarm?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));

            //  About the joint policyholders
            put("Would you like to add a joint policyholder?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));

            // About your insurance history
            put("How many consecutive years have you held home insurance?", new EntryGroup(asSelect, asSelectedValue,"5 years")); // Enum
            put("In the past 3 years, has anyone living at the property made any home insurance claims or reported any losses to an insurer?",
                    new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));

            // About the cover you want
            put("When would you like cover to start?", new EntryGroup(asButton, asNothing,"Tomorrow"));
            put("Please select what type of home insurance you require", new EntryGroup(asSelect, asSelectedValue,"Buildings only")); // Enum
            put("Please enter the current market value of your property", new EntryGroup(asText, asTextValue,"£300,000"));
            put("Would you like to include upgraded accidental damage cover for Buildings?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("Standard Excess (other excesses may apply)", new EntryGroup(asSelect, asSelectedValue,"£200"));
        }
    };
}
