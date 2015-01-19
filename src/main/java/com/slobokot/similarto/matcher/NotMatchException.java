package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.Position;

public class NotMatchException extends Exception {
    private final Position expected;
    private final Object actual;

    public NotMatchException(String message, Position expected, Object actual) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    public Position getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }
}
