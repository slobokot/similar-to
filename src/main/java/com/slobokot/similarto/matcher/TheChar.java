package com.slobokot.similarto.matcher;

class TheChar extends TheObject {
    private TheChar(Object object) {
        super(object);
    }

    public static TheObject from(String value) {
        if (value.length() != 1) {
            throw new RuntimeException("value '" + value + "' is not a char");
        }
        return new TheChar(value.charAt(0));
    }

    @Override
    public ObjectMatcher getItemMatcher(String matcher) {
        return null;
    }
}
