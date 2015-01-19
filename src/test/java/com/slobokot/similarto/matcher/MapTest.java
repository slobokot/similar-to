package com.slobokot.similarto.matcher;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.junit.Assert.assertThat;

public class MapTest {
    @Test
    public void mapEmpty() {
        Map<String,Object> map = new HashMap<String, Object>();
        assertThat(map, similarTo("{}"));
    }

    @Test
    public void mapOfStringToString() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("house", "big");
        map.put("a grain", "small");
        map.put("big red sun", "very big");
        assertThat(map, similarTo("{house : big, 'a grain' : small, 'big red sun' : 'very big'}"));
    }
}
