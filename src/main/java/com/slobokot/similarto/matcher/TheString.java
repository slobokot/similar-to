package com.slobokot.similarto.matcher;

public class TheString extends TheObject {
    private TheString(Object object) {
        super(object);
    }

    public static TheObject from(String value) {
        return new TheString(value);
    }

    public ObjectMatcher getItemMatcher(String matcher) throws CantCompareException {
        if (matcher.equals(Matchers.MATCHES))
            return new ObjectMatcher() {
                @Override
                public boolean match(Object expected, Object actual) {
                    return actual.toString().matches(expected.toString());
                }
            };
        if (matcher.equals(Matchers.IGNORE_CASE))
            return new ObjectMatcher() {
                @Override
                public boolean match(Object expected, Object actual) {
                    return expected.toString().equalsIgnoreCase(actual.toString());
                }
            };
        if (matcher.equals(Matchers.STRICT))
            return new TheEqMatcher();

        throw new CantCompareException("Unknown matcher " + matcher,null);
    }
}
