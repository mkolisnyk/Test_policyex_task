package com.policyexpert.sample.lib.data;

import com.policyexpert.sample.lib.actions.IPopulate;
import com.policyexpert.sample.lib.actions.IVerify;

public class EntryPair {
    private Object values;
    private IPopulate populateFunc;
    private IVerify verifyFunc;

    public EntryPair(IPopulate populateFunc, IVerify verifyFunc, Object values) {
        this.values = values;
        this.populateFunc = populateFunc;
        this.verifyFunc = verifyFunc;
    }

    public Object getValues() {
        return values;
    }

    public IPopulate getPopulateFunc() {
        return populateFunc;
    }

    public IVerify getVerifyFunc() {
        return verifyFunc;
    }
}
