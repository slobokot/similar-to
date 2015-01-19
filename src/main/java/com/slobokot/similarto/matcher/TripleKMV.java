package com.slobokot.similarto.matcher;

import com.slobokot.similarto.parser.MutableTriple;
import com.slobokot.similarto.parser.ParseException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class TripleKMV extends TripleMV {
    private final Item key;

    public TripleKMV(MutableTriple builder) throws ParseException {
        super(builder);
        key = Item.create(builder.getKey());
    }

    public Item key() {
        return key;
    }

    @Override
    public String toString() {
        return key.toString() + " " + super.toString();
    }

    @Override
    public void matches(Object actual) throws CantCompareException, NotMatchException {
        String key = ((StringItem)key()).string();
        Object propertyValue = getValue(actual, key);
        value().matches(propertyValue, matcher());
    }

    public boolean matchesMapEntry(Map.Entry mapEntry) throws CantCompareException {
        try {
            key().matches(mapEntry.getKey(), Matchers.STRICT);
            value().matches(mapEntry.getValue(), matcher());
            return true;
        }
        catch(NotMatchException e) {
            return false;
        }
    }

    Object getValue(Object o, String propertyName) throws CantCompareException, NotMatchException {
        try {
            try {
                String methodName = "get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
                return o.getClass().getMethod(methodName).invoke(o);
            } catch (NoSuchMethodException ex) {
                try {
                    return o.getClass().getMethod(propertyName).invoke(o);
                } catch (NoSuchMethodException e) {
                    throw new CantCompareException("Property " + propertyName + " doesn't exist.", e, this);
                }
            }
        } catch (InvocationTargetException e) {
            throw new CantCompareException("InvocationTargetException for property " + propertyName + " " + e.getMessage() + ".", e, this);
        } catch (IllegalAccessException e) {
            throw new CantCompareException("IllegalAccessException for property " + propertyName + " " + e.getMessage() + ".", e, this);
        }
    }
}
