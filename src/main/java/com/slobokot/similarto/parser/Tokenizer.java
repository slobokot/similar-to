package com.slobokot.similarto.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Tokenizer  {
    private final String text;
    private String value;
    private Token token;
    private int currentPos;
    private int tokenPos;
    private static final Set<Character> unquotedSymbols;
    private static final Set<Character> quotedSymbols;

    static {
        unquotedSymbols = new HashSet<Character>();
        unquotedSymbols.addAll(Arrays.asList(new Character[]{'{', '}', ':', ',', ' ', '\t', '\r', '\n'}));
        quotedSymbols = new HashSet<Character>();
        quotedSymbols.add('\'');
    }

    public Tokenizer(String text) {
        this.text = text;
    }

    public Tokenizer clone() {
        Tokenizer tokenizer = new Tokenizer(text);
        tokenizer.value = value;
        tokenizer.token = token;
        tokenizer.currentPos = currentPos;
        return tokenizer;
    }

    public String similarto() {
        return text;
    }

    public Token next() throws ParseException{
        skipWhiteSpace();
        tokenPos = currentPos;
        if (currentPos == text.length())
            return token = Token.END_OF_STREAM;

        switch(text.charAt(currentPos)) {
            case '{': currentPos++; return token = Token.CURLY_OPEN;
            case '}': currentPos++; return token = Token.CURLY_CLOSE;
            case ',': currentPos++; return token = Token.COMMA;
            case ':': currentPos++; value = ":"; return token = Token.VALUE;
            default:
                value = readString();
                return token = Token.VALUE;
        }
    }

    public String value() { return value; }

    public int position() { return tokenPos; }

    public Token current() { return token; }

    private void skipWhiteSpace() {
        while(currentPos < text.length()) {
            char v = text.charAt(currentPos);
            if (v == ' ' || v == '\t' || v == '\r' || v == '\n') {
                currentPos++;
            }
            else {
                return;
            }
        }
    }

    private String readString() throws ParseException{
        StringBuilder s = new StringBuilder();
        Set<Character> endSymbols;

        if( currentPos < text.length()) {
            char v = text.charAt(currentPos);
            if (v == '\'') {
                endSymbols = quotedSymbols;
                currentPos++;
            } else {
                endSymbols = unquotedSymbols;
            }

            while(currentPos < text.length()) {
                v = text.charAt(currentPos);
                if (endSymbols.contains(v)) {
                    break;
                }
                currentPos++;
                s.append(v);
            }

            String result = s.toString();

            if (endSymbols == quotedSymbols) {
                if(currentPos >= text.length())
                    throw new ParseException("Unexpected end of similar-to text", text.length()-1);
                currentPos++;
            } else {
                result = result.trim();
            }

            return result;
        }

        return "";
    }



    static enum Token {
        VALUE,
        CURLY_OPEN,
        CURLY_CLOSE,
        COMMA,
        END_OF_STREAM
    }
}
