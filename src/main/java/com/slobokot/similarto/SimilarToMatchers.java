package com.slobokot.similarto;

import org.hamcrest.BaseMatcher;

public class SimilarToMatchers {
    public static BaseMatcher<Object> similarTo(String text) {
        return new SimilarTo(text);
    }
}
