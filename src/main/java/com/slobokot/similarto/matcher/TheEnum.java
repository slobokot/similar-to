package com.slobokot.similarto.matcher;

class TheEnum extends TheObject {

    private TheEnum(Object object) {
        super(object);
    }

    public static TheObject from(String value) {
        return new TheEnum(value);
    }

    @Override
    public ObjectMatcher getItemMatcher(String matcher) {
        return new ObjectMatcher() {
            @Override
            public boolean match(Object expected, Object actual) {
                return actual.toString().equalsIgnoreCase(expected.toString());
            }
        };
    }
}
