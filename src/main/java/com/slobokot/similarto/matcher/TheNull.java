package com.slobokot.similarto.matcher;

class TheNull extends TheObject {
    private TheNull() {
        super(null);
    }

    public static TheObject from(String value) {
        return value.equals("null") ? new TheNull() : TheString.from(value);
    }

    @Override
    public ObjectMatcher getItemMatcher(String matcher) {
        return new TheEqMatcher();
    }
}
