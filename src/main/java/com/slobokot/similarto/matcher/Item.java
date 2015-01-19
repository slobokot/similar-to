package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableItem;
import com.slobokot.similarto.parser.ParseException;
import com.slobokot.similarto.parser.Position;

import java.util.List;

abstract public class Item extends Position {

    Item(int position) throws ParseException {
        super(position);
    }

    public static Item create(MutableItem mutableItem) throws ParseException {
        if (mutableItem == null)
            return null;
        if (mutableItem.isArray())
            return new ArrayItem(mutableItem);
        return new StringItem(mutableItem);
    }

    abstract void matches(Object actual, String matcher) throws CantCompareException, NotMatchException;

    // these methods are here just to make tests easier
    public abstract String string();
    public abstract List<TripleV> array();
}
