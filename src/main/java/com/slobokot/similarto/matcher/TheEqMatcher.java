package com.slobokot.similarto.matcher;

class TheEqMatcher implements ObjectMatcher {
    @Override
    public boolean match(Object expected, Object actual) {
        return expected != null ? expected.equals(actual) : expected == actual;
    }
}
