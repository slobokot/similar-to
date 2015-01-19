package com.slobokot.similarto.parser;

public class Stack {
    public static String get(String similarto, int position) {
        StringBuilder b = new StringBuilder("at pos ");
        b.append(position);
        b.append(" near \"");
        int startIndex = position - 20;
        if (startIndex < 0)
            startIndex = 0;
        b.append(similarto.substring(startIndex, position));
        b.append(" >>> ");
        if (position < similarto.length()) {
            b.append(similarto.charAt(position));
            b.append(" <<< ");
            int endIndex = position + 20;
            if (endIndex >= similarto.length()) endIndex = similarto.length();
            if (position + 1 < similarto.length())
                b.append(similarto.substring(position + 1, endIndex));
        }
        b.append("\"");

        return b.toString();
    }

    public static String get(Tokenizer tokenizer) {
        return get(tokenizer.similarto(), tokenizer.position());
    }
}
