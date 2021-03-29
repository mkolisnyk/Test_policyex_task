package com.policyexpert.sample.lib.data;

import com.policyexpert.sample.lib.actions.IPopulate;

public class EntryPair {
    private Object values;
    private IPopulate populateFunc;

    public EntryPair(IPopulate populateFunc, Object values) {
        this.values = values;
        this.populateFunc = populateFunc;
    }

    public Object getValues() {
        return values;
    }

    public IPopulate getPopulateFunc() {
        return populateFunc;
    }
}
