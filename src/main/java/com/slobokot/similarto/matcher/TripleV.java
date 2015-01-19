package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableTriple;
import com.slobokot.similarto.parser.ParseException;
import com.slobokot.similarto.parser.Position;

public class TripleV extends Position {
    private final Item value;

    public TripleV(MutableTriple builder) throws ParseException {
        super(builder.position());

        value = Item.create(builder.getValue());
    }

    public Item value() {
        return value;
    }

    public String matcher() { return ":"; }

    @Override
    public String toString() {
        return value.toString();
    }

    public static TripleV create(MutableTriple triple) throws ParseException{
        if (triple.getKey() != null)
            return new TripleKMV(triple);
        if (triple.getMatcher() != null)
            return new TripleMV(triple);
        return new TripleV(triple) ;
    }

    public void matches(Object actual) throws CantCompareException, NotMatchException {
        if (value == null && actual == null)
            return;

        value.matches(actual, matcher());
    }
}
