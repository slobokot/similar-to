package com.slobokot.similarto.matcher;

import org.junit.Test;

import static com.slobokot.similarto.matcher.MatcherDescriptionVerifier.checkMatcherDescription;

public class SimpleTypesDesciriptionTest {
    @Test
    public void aString() {
        checkMatcherDescription("Xasha",
                "Masha",
                "value ", "Xasha", " at pos 0 near \" >>> X <<< asha\"",
                "was ",
                "Masha");
    }

    @Test
    public void aInt() {
        checkMatcherDescription("15",
                12,
                "value ", "15", " at pos 0 near \" >>> 1 <<< 5\"",
                "was ",
                "12");
    }

    @Test
    public void trueLocase() {
        checkMatcherDescription("true",
                false,
                "value ", "true", " at pos 0 near \" >>> t <<< rue\"",
                "was ",
                "false");
    }
}
