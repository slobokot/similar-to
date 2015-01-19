package com.slobokot.similarto.parser;

public class ParseException extends Exception {
    int position;

    public ParseException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
