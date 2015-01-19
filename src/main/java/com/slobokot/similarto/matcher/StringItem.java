package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableItem;
import com.slobokot.similarto.parser.ParseException;

import java.util.List;

public class StringItem extends Item {
    private final String string;

    public StringItem(MutableItem mutableItem) throws ParseException {
        super(mutableItem.position());
        string = mutableItem.getString();
    }

    public String string() {
        return string;
    }

    @Override
    public List<TripleV> array() {
        throw new RuntimeException("Shouldn't be called");
    }

    @Override
    public String toString() {
        return string();
    }

    @Override
    void matches(Object actual, String matcher) throws NotMatchException, CantCompareException {
        TheObject expected;
        ObjectMatcher itemMatcher;
        try {
            expected = Convert.stringTo(string(), actual);
            itemMatcher = expected.getItemMatcher(matcher);
        } catch(CantCompareException e) {
            throw new CantCompareException(e.getMessage(), e.getCause(), this);
        }

        if (!itemMatcher.match(expected.getObject(), actual))
            throw new NotMatchException("value ", this, actual);

    }
}
