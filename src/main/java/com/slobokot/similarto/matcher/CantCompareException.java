package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.Position;

public class CantCompareException extends Exception {
    private final Position position;

    public CantCompareException(String message, Position position) {
        super(message);
        this.position = position;
    }

    public CantCompareException(String message, Throwable cause, Position position) {
        super(message, cause);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
