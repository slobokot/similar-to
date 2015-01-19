package com.slobokot.similarto.matcher;

public class Convert {
    public static TheObject stringTo(String value, Object destinationObject) throws CantCompareException {
        try {
            if (destinationObject instanceof String)
                return TheString.from(value);
            if (destinationObject instanceof Integer)
                return TheNumber.from(Integer.valueOf(value));
            if (destinationObject instanceof Boolean)
                return TheBoolean.from(value);
            if (destinationObject instanceof Enum<?>)
                return TheEnum.from(value);
            if (destinationObject instanceof Character)
                return TheChar.from(value);
            if (destinationObject instanceof Double)
                return TheNumber.from(Double.valueOf(value));
            if (destinationObject instanceof Float)
                return TheNumber.from(Float.valueOf(value));

            return TheNull.from(value);
        }
        catch(NumberFormatException e) {
            throw new CantCompareException(e.getMessage(), e, null);
        }
    }
}
