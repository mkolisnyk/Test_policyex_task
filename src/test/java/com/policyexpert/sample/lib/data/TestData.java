package com.policyexpert.sample.lib.data;
import com.policyexpert.sample.lib.data.questions.AboutYouQuestions;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.policyexpert.sample.lib.actions.Populate.*;
import static com.policyexpert.sample.lib.actions.Verify.*;

public class TestData {

    public static final String defaultEmail = "vketipisz@qmetric.co.uk";
    public static final String defaultAddress = "Autonomy Capital Research LLP, 110 Bishopsgate, London, EC2N 4AY";

    public static final Map<String, EntryGroup> defaultTestData = new LinkedHashMap<String, EntryGroup>() {
        {
            put(AboutYouQuestions.Title, new EntryGroup(asSelect, asSelectedValue,"Mr"));
            put(AboutYouQuestions.FirstName, new EntryGroup(asText, asTextValue,"Sample"));
            put(AboutYouQuestions.LastName, new EntryGroup(asText, asTextValue,"Name"));
            String date[] = new String[] {"27", "July", "1987"};
            put(AboutYouQuestions.DateOfBirth, new EntryGroup(asDOBSelect, asDOBSelectedValue, date));
            put(AboutYouQuestions.MaritalStatus, new EntryGroup(asSelect, asSelectedValue,"Single"));
            put(AboutYouQuestions.Occupation, new EntryGroup(asLookupText, asTextValue,"Construction Engineer"));
            put(AboutYouQuestions.AnyoneSmokes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put(AboutYouQuestions.PhoneNumber, new EntryGroup(asText, asTextValue, "00000000000"));
            put(AboutYouQuestions.EMail, new EntryGroup(asText, asTextValue, defaultEmail));


//            Title
//            First name
//            Last name
//            What is your date of birth?
//            What is your marital status?
//            What is your occupation?
//            Does anybody living in the property smoke?
//            Main telephone number
//            What is your e-mail?


            put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IAgree));
//            Your property is NOT used for business purposes
//            None of the occupants have been declared bankrupt within 5 years
//            None of the occupants have been served with a County Court Judgement within 5 years
//            None of the occupants have been declined home insurance or had a renewal refused within 5 years
//            None of the occupants have had their home insurance cancelled or special terms imposed within 5 years
//            None of the occupants have been convicted or charged with any offence other than a motoring offence

            // About property section

            // Autonomy Capital Research LLP, 110 Bishopsgate, London, EC2N 4AY
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

//            Address of property to be insured
//            Is this the same as your correspondence address?
//            What type of property is it?
//            Which of these best describes your property?
//            Approximately in which year was your property built?
//            How many bedrooms does your property have?
//            Do you own the property?
//            Who is the property occupied by?
//            When is the property usually occupied?
//            Are there trees taller than 10 metres within 5 metres of your property?
//            Does your property have a flat roof?
//            What percentage of your roof is flat?

            // Statements about property
            put("Statements about your property", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IAgree));

//            It is your main residence
//            The walls are made of brick, stone or concrete
//            The roof is made of tile or slate
//            Your property is in a good state of repair
//            Your property or any property within 100m of your boundary wall has NOT been affected by flood within the last 10 years
//            Your property has NOT been subject to subsidence or had structural support or underpinning within the last 10 years
//            To the best of your knowledge neighbouring properties have NOT suffered from subsidence, landslip, heave or river erosion within the last 10 years
//            It does NOT show signs of cracking in external walls
//            It is NOT a listed building
//            It is NOT undergoing any major building works e.g. renovations, structural alterations or flood damage repairs?

            // About safety and security

            put("Do all externally accessible windows have key-operated locks on them?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("Is your property self-contained, i.e. it has its own front door with its own lockable entrance?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have any French or patio doors?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have any other external doors?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
            put("Does your property have a working burglar alarm?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));

//            Do all externally accessible windows have key-operated locks on them?
//                Is your property self-contained, i.e. it has its own front door with its own lockable entrance?
//            Does your property have any French or patio doors?
//                Does your property have any other external doors?
//                Does your property have a working burglar alarm?

            //  About the joint policyholders
//            Would you like to add a joint policyholder?
            put("Would you like to add a joint policyholder?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));

//            About your insurance history
            put("How many consecutive years have you held home insurance?", new EntryGroup(asSelect, asSelectedValue,"5 years")); // Enum
            put("In the past 3 years, has anyone living at the property made any home insurance claims or reported any losses to an insurer?",
                    new EntryGroup(asButton, asButtonState, BooleanChoiceValue.No));
//            How many consecutive years have you held home insurance?
//            In the past 3 years, has anyone living at the property made any home insurance claims or reported any losses to an insurer?

//            About the cover you want
//            When would you like cover to start?
            put("When would you like cover to start?", new EntryGroup(asButton, asNothing,"Tomorrow"));
            put("Please select what type of home insurance you require", new EntryGroup(asSelect, asSelectedValue,"Buildings only")); // Enum
            put("Please enter the current market value of your property", new EntryGroup(asText, asTextValue,"£300,000"));
            put("Would you like to include upgraded accidental damage cover for Buildings?", new EntryGroup(asButton, asButtonState, BooleanChoiceValue.Yes));
            put("Standard Excess (other excesses may apply)", new EntryGroup(asSelect, asSelectedValue,"£200"));
        }
    };
}
