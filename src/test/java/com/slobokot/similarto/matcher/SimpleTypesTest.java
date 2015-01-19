package com.slobokot.similarto.matcher;

import org.junit.Test;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SimpleTypesTest {
    @Test
    public void aString() {
        assertThat("Masha", similarTo("Masha"));
    }

    @Test
    public void stringNotMatch() {
        assertThat("Masha", not(similarTo("masha")));
    }

    @Test
    public void stringIgnoreCase() {
        assertThat("Masha", similarTo("ignoreCase mAshA"));
    }

    @Test
    public void stringMatchRegex() {
        assertThat("Masha", similarTo("matches Ma.h[abc]"));
    }

    @Test
    public void stringNotMatchRegex() {
        assertThat("Masha", not(similarTo("matches Ma.h[x]")));
    }

    @Test
    public void aInt() {
        assertThat(13, similarTo("13"));
    }

    @Test
    public void aIntNotMatch() {
        assertThat(13, not(similarTo("14")));
    }

    @Test
    public void aDouble() {
        assertThat(13.0, similarTo("13"));
    }

    @Test
    public void aDoubleNotMatch() {
        assertThat(13.0, not(similarTo("14")));
    }

    @Test
    public void aOptionalNotMatch() {
        assertThat(13.0, not(similarTo("14")));
    }

    @Test
    public void trueLoCase() {
        assertThat(true, similarTo("true"));
    }

    @Test
    public void trueUpCase() {
        assertThat(true, similarTo("TRUE"));
    }

    @Test
    public void trueNotMatch() {
        assertThat(true, not(similarTo("false")));
    }

    @Test
    public void falseLoCase() {
        assertThat(false, similarTo("false"));
    }

    @Test
    public void falseUpCase() {
        assertThat(false, similarTo("FALSE"));
    }

    @Test
    public void nullLoCase() {
        assertThat(null, similarTo("null"));
    }

    @Test
    public void nullUpCaseNotMatch() {
        assertThat(null, not(similarTo("Null")));
    }

    @Test
    public void nullDoesntMatchAnObject() {
        assertThat(new Object(), not(similarTo("null")));
    }
}
