package com.slobokot.similarto.matcher;

abstract class TheObject {
    private final Object object;

    protected TheObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    abstract public ObjectMatcher getItemMatcher(String matcher) throws CantCompareException;
}
