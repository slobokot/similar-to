package com.slobokot.similarto.parser;

import com.slobokot.similarto.matcher.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    Parser parser = new Parser();

    @Test
    public void emptyJson() {
        TripleV triple = parser.parse("");
        assertNull(triple.value());
    }

    @Test
    public void emptyObject() {
        TripleV triple = parser.parse("{}");
        assertTrue(triple.getClass() == TripleV.class);
        assertTrue(triple.value().getClass() == ArrayItem.class);
        assertEquals("[]", triple.value().toString());
    }

    @Test
    public void twoObjectsWithoutArrayAndWithoutCommaCauseException() {
        expectedException.expect(RuntimeException.class);
        parser.parse("{}{}");
    }

    @Test
    public void keyValue() {
        TripleV triple = parser.parse("{masha:girl}");
        assertKeyValue(triple.value().array().get(0), "masha", ":", "girl");
    }

    @Test
    public void severalKeyValues() {
        TripleV triple = parser.parse("{ name : Masha, price:100, age:14.5 }");
        assertKeyValue(triple.value().array().get(0), "name", ":", "Masha");
        assertKeyValue(triple.value().array().get(1), "price", ":", "100");
        assertKeyValue(triple.value().array().get(2), "age", ":", "14.5");
    }

    @Test
    public void keyIsArray() {
        TripleV triple = parser.parse("{{2,3,4} : girl}");
        TripleKMV first = (TripleKMV)triple.value().array().get(0);
        List<TripleV> key = first.key().array();
        assertEquals("2", key.get(0).value().string());
        assertEquals("3", key.get(1).value().string());
        assertEquals("4", key.get(2).value().string());
        assertEquals("girl", first.value().string());
    }

    @Test
    public void valueIsArray() {
        TripleV triple = parser.parse("{masha:{2,3,4}}");
        TripleKMV first = (TripleKMV)triple.value().array().get(0);
        assertEquals("masha", first.key().string());
        assertEquals("2", first.value().array().get(0).value().string());
        assertEquals("3", first.value().array().get(1).value().string());
        assertEquals("4", first.value().array().get(2).value().string());
    }

    @Test
    public void valueIsArrayOfKeyValueObjects() {
        TripleV triple = parser.parse("{masha: { {boobs:5},{height:5.3} } }");
        TripleKMV first = (TripleKMV)triple.value().array().get(0);
        assertEquals("masha", first.key().string());
        TripleV value1 = first.value().array().get(0).value().array().get(0);
        assertKeyValue(value1, "boobs", ":", "5");
        TripleV value2 = first.value().array().get(1).value().array().get(0);
        assertKeyValue(value2, "height", ":", "5.3");
    }

    @Test
    public void valueIsArrayOfArrays() {
        TripleV root = parser.parse("{masha: { {a,b},{1,2} } }");
        TripleKMV triple = (TripleKMV)root.value().array().get(0);
        assertEquals("masha", triple.key().string());
        TripleV firstValue = triple.value().array().get(0);
        assertEquals("a", firstValue.value().array().get(0).value().string());
        assertEquals("b", firstValue.value().array().get(1).value().string());
        TripleV secondValue = triple.value().array().get(1);
        assertEquals("1", secondValue.value().array().get(0).value().string());
        assertEquals("2", secondValue.value().array().get(1).value().string());
    }

    @Test
    public void keyValueAfterArrayOfArrays() {
        TripleV triple = parser.parse("{masha: { {a,b},{1,2} }, bob:hello }");
        TripleKMV first = (TripleKMV)triple.value().array().get(0);
        assertEquals("masha", first.key().string());
        TripleKMV second = (TripleKMV)triple.value().array().get(1);
        assertEquals("bob", second.key().string());
        assertEquals("hello", second.value().string());
    }

    @Test
    public void objectOfObjectsOfObjects() {
        TripleV root = parser.parse("{ cars:{first:{car:ferrari, engine:v8}, second:{car:mercedes, engine:v6}} }");
        TripleKMV triple = (TripleKMV)root.value().array().get(0);
        assertEquals("cars", triple.key().string());
        TripleKMV first = (TripleKMV)triple.value().array().get(0);
        assertEquals("first", first.key().string());
        TripleV car = first.value().array().get(0);
        assertKeyValue(car, "car", ":", "ferrari");
        TripleV engine = first.value().array().get(1);
        assertKeyValue(engine, "engine", ":", "v8");

        TripleV second = triple.value().array().get(1);
        car = second.value().array().get(0);
        assertKeyValue(car, "car", ":", "mercedes");
        engine = second.value().array().get(1);
        assertKeyValue(engine, "engine", ":", "v6");
    }

    @Test
    public void simpleValue() {
        TripleV triple = parser.parse("cars");
        assertEquals("cars", triple.value().string());
    }

    @Test
    public void matcherWithValue() {
        TripleV triple = parser.parse("eq cars");
        assertEquals("eq", triple.matcher());
        assertEquals("cars", triple.value().string());
    }

    @Test
    public void matcherWithObject() {
        TripleV triple = parser.parse("eq {}");
        assertEquals("eq", triple.matcher());
        assertTrue(triple.value().array().isEmpty());
    }

    private void assertKeyValue(TripleV triple, String key, String matcher, String value) {
        TripleKMV t = (TripleKMV)triple;
        assertEquals(key, t.key().string());
        assertEquals(matcher, t.matcher());
        assertEquals(value, t.value().string());
    }
}