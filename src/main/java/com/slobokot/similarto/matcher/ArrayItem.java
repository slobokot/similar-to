package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableItem;
import com.slobokot.similarto.parser.MutableTriple;
import com.slobokot.similarto.parser.ParseException;

import java.util.*;

public class ArrayItem extends Item {
    private final List<TripleV> array;

    public ArrayItem(MutableItem mutableItem) throws ParseException {
        super(mutableItem.position());

        if (mutableItem.getArray()!=null) {
            array = new ArrayList<TripleV>();
            for(MutableTriple triple : mutableItem.getArray()) {
                array.add(TripleV.create(triple));
            }
        } else {
            array = null;
        }
    }

    @Override
    public List<TripleV> array() {
        return array;
    }

    public @Override String string() {
        throw new RuntimeException("shouldn't be called");
    }

    @Override
    public String toString() {
        return array.toString();
    }

    @Override
    void matches(Object actual, String matcher) throws CantCompareException, NotMatchException {
        if(matcher!=":")
            throw new CantCompareException("Unknown matcher " + matcher , this);

        if (actual instanceof Map) {
            matchesMap((Map<Object, Object>) actual);
        }
        else if (actual instanceof Collection) {
            matchesCollection((Collection) actual);
        }
        else {
            matchesObject(actual);
        }
    }

    private void matchesObject(Object actual) throws CantCompareException, NotMatchException {
        for (TripleV triple : array) {
            triple.matches(actual);
        }
    }

    private void matchesCollection(Collection actual) throws CantCompareException, NotMatchException {
        Collection actualCollection = actual;

        if (actualCollection.size() != array.size())
            throw new NotMatchException("collection size to be " + array.size() + " but was " + actualCollection.size(),
                    this,
                    actual);


        Iterator actualIterator = actualCollection.iterator();
        Iterator expectedIterator = array.iterator();
        for (; ; ) {
            if (!actualIterator.hasNext())
                break;
            Object actualNext = actualIterator.next();
            TripleV expectedNext = (TripleV) expectedIterator.next();
            expectedNext.matches(actualNext);
        }
    }

    private void matchesMap(Map<Object, Object> actual) throws CantCompareException, NotMatchException {
        Map<Object,Object> actualMap = actual;
        if (actualMap.size() != array.size())
            throw new NotMatchException("map size to be " + array.size() + " but was " + actualMap.size(),
                    this,
                    actual);

        boolean[] found = new boolean[array.size()];
        for (Map.Entry<Object,Object> entry : actualMap.entrySet()) {
            for(int i = 0 ; i < array.size(); i++) {
                if (!found[i]) {
                    if (((TripleKMV)array.get(i)).matchesMapEntry(entry)) {
                        found[i] = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0 ; i < array.size() ;i ++) {
            if (!found[i])
                throw new NotMatchException("existing map entry ", array.get(i), actual);
        }
    }
}
