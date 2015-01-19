package com.slobokot.similarto.parser;

public class MutableTriple extends Position {
    MutableItem key;
    MutableItem matcher;
    MutableItem value;

    public MutableTriple(int position) {
        super(position);
    }

    public MutableItem getKey() {
        return key;
    }

    public void setKey(MutableItem key) {
        this.key = key;
    }

    public MutableItem getMatcher() {
        return matcher;
    }

    public void setMatcher(MutableItem matcher) {
        this.matcher = matcher;
    }

    public MutableItem getValue() {
        return value;
    }

    public void setValue(MutableItem value) {
        this.value = value;
    }
}
