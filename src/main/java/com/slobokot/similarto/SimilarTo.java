package com.slobokot.similarto;

import com.slobokot.similarto.matcher.CantCompareException;
import com.slobokot.similarto.matcher.NotMatchException;
import com.slobokot.similarto.parser.Parser;
import com.slobokot.similarto.matcher.TripleV;
import com.slobokot.similarto.parser.Stack;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.junit.Assert.fail;

class SimilarTo extends BaseMatcher<Object> {
    private Object mismatchExpectedObject;
    private TripleV triple;
    private final String text;
    private Object mismatchActualObject;
    private String mismatchPosition;
    private String mismatchMessage;

    public SimilarTo(String text) {
        this.text = text;
        triple = new Parser().parse(text);
    }

    @Override
    public boolean matches(Object actual) {
        try {
            triple.matches(actual);
            return true;
        } catch(NotMatchException e) {
            mismatchMessage = e.getMessage();
            mismatchExpectedObject = e.getExpected();
            mismatchActualObject = e.getActual();
            mismatchPosition = Stack.get(text, e.getExpected().position());
            return false;
        } catch(CantCompareException e) {
            throw new RuntimeException(e.getMessage() + " " + Stack.get(text, e.getPosition().position()), e.getCause());
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(mismatchMessage).appendValue(mismatchExpectedObject).appendText(" " + mismatchPosition);
    }

    @Override
    public void describeMismatch(final Object item, final Description description) {
        description.appendText("was ").appendValue(mismatchActualObject);
    }
}
