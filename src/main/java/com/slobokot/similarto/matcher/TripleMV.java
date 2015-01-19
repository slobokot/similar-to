package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableTriple;
import com.slobokot.similarto.parser.ParseException;

public class TripleMV extends TripleV {
    private final String matcher;

    public TripleMV(MutableTriple builder) throws ParseException {
        super(builder);

        if (builder.getMatcher() != null) {
            if (!builder.getMatcher().isString())
                throw new ParseException("Matcher has to be a string", builder.getMatcher().position());
            matcher = builder.getMatcher().getString();
        } else {
            matcher = null;
        }
    }

    @Override
    public String matcher() {
        return matcher;
    }

    @Override
    public String toString() {
        return matcher + " " + super.toString();
    }
}
