package com.slobokot.similarto.matcher;

class TheBoolean extends TheObject {
    private TheBoolean(Object object) {
        super(object);
    }

    public static TheObject from(String value) {
        if (value.equalsIgnoreCase("false"))
            return new TheBoolean(false);
        if (value.equalsIgnoreCase("true"))
            return new TheBoolean(true);
        throw new RuntimeException("Can't convert '" + value + "' to boolean");
    }

    @Override
    public ObjectMatcher getItemMatcher(String matcher) {
        return new TheEqMatcher();
    }
}
