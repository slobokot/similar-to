package com.slobokot.similarto.matcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.junit.Assert.assertThat;

public class ListTest {
    @Test
    public void listEmpty() {
        List<String> list = new ArrayList<String>();
        assertThat(list, similarTo("{}"));
    }

    @Test
    public void listOfStrings() {
        List<String> list = new ArrayList<String>();
        list.add("Hard");
        list.add("Core");
        list.add("Porn");

        assertThat(list, similarTo("{ Hard , Core, Porn }"));
    }
}
