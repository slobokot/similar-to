package com.slobokot.similarto.matcher;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.junit.Assert.assertThat;

public class SetTest {
    @Test
    public void setEmpty() {
        Set<String> set = new HashSet<String>();
        assertThat(set, similarTo("{}"));
    }

    @Test
    public void setOfString() {
        Set<String> set = new HashSet<String>();
        set.add("father");
        set.add("daughter");
        set.add("sister");
        assertThat(set, similarTo("{father, daughter, sister}"));
    }
}
