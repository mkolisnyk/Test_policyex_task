package com.policyexpert.sample.lib.data;

public enum BooleanChoiceValue {
    Yes("Yes"),
    No("No"),
    IAgree("I agree"),
    IDisagree("I disagree");
    private String value;

    BooleanChoiceValue(String param) {
        this.value = param;
    }

    @Override
    public String toString() {
        return value;
    }
}
