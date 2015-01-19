package com.slobokot.similarto.matcher;

import org.junit.Test;

import static com.slobokot.similarto.matcher.MatcherDescriptionVerifier.checkMatcherDescription;
import static org.junit.Assert.assertThat;

public class ObjectDescriptionTest {

    public static class SingleStringProperty {
        public String getName() {
            return "Masha";
        }
    }

    @Test
    public void singleStringProperty() {
        checkMatcherDescription("{ name : Natasha }",
                new SingleStringProperty(),
                "value ", "Natasha", " at pos 9 near \"{ name :  >>> N <<< atasha }\"",
                "was ",
                "Masha");
    }
}