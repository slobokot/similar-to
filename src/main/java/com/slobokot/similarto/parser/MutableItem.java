package com.slobokot.similarto.parser;

import java.util.ArrayList;
import java.util.List;

public class MutableItem extends Position {
    String string;
    List<MutableTriple> array;

    public MutableItem(int position) {
        super(position);
        array = new ArrayList<MutableTriple>();
    }

    public MutableItem(int position, String value) {
        super(position);
        setString(value);
    }

    public boolean isArray() {
        return array != null;
    }

    public boolean isString() {
        return string != null;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<MutableTriple> getArray() {
        return array;
    }

    public void addTriple(MutableTriple triple) {
        array.add(triple);
    }
}
