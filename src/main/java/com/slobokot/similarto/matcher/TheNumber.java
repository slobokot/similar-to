package com.slobokot.similarto.matcher;

class TheNumber extends TheObject {
    private TheNumber(Object object) {
        super(object);
    }

    public static TheObject from(Object value) {
        return new TheNumber(value);
    }

    @Override
    public ObjectMatcher getItemMatcher(String matcher) {
        return new TheEqMatcher();
    }
}
