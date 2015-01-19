package com.slobokot.similarto.matcher;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.slobokot.similarto.matcher.MatcherDescriptionVerifier.checkMatcherDescription;

public class MapDescriptionTest {
    @Test
    public void mapOfStringToString() {
        Map<String,String> map = new HashMap<String, String>() {
            {
                put("house", "big");
                put("a grain", "small");
                put("big red sun", "very big");
            }};

        checkMatcherDescription("{house : big, something : good, 'big red sun' : 'very big'}",
                map,
                "existing map entry ", "something : good", " at pos 12 near \"{house : big >>> , <<<  something : good, \"",
                "was ",
                "{a grain=small, big red sun=very big, house=big}");
    }
}
