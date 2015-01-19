package com.slobokot.similarto.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.mockito.ArgumentCaptor;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MatcherDescriptionVerifier {
    public static void checkMatcherDescription(String similarto,
                                               Object object,
                                               String expectedMesage,
                                               String expectedValue,
                                               String expectedPosition,
                                               String actualMessage,
                                               String actualValue) {
        Matcher similarTo = similarTo(similarto);
        similarTo.matches(object);

        Description describeTo = mockDescription();

        similarTo.describeTo(describeTo);
        verifyDescription(describeTo, expectedMesage, expectedValue, expectedPosition);

        Description describeMismatch = mockDescription();
        similarTo.describeMismatch(null, describeMismatch);
        verifyDescription(describeMismatch, actualMessage, actualValue, null);
    }

    private static Description mockDescription() {
        Description description = mock(Description.class);
        when(description.appendText(anyString())).thenReturn(description);
        when(description.appendValue(anyObject())).thenReturn(description);
        return description;
    }

    private static void verifyDescription(Description description, String message, String value, String position) {
        verify(description).appendText(message);
        ArgumentCaptor<Object> describeToValue = ArgumentCaptor.forClass(Object.class);
        verify(description).appendValue(describeToValue.capture());
        assertEquals(value, describeToValue.getValue().toString());

        if (position != null)
            verify(description).appendText(position);
    }
}
