package com.slobokot.similarto.parser;

import com.slobokot.similarto.parser.Tokenizer;
import com.slobokot.similarto.parser.Tokenizer.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenizerTest {
    Tokenizer tokenizer;

    @Test
    public void noObject() throws Exception {
        tokenizer = new Tokenizer("");
        checkEvents(Token.END_OF_STREAM);
    }

    @Test
    public void emptyObject() throws Exception {
        tokenizer = new Tokenizer("{}");
        checkEvents(Token.CURLY_OPEN, Token.CURLY_CLOSE, Token.END_OF_STREAM);
    }

    @Test
    public void keyValue() throws Exception{
        tokenizer = new Tokenizer("{key:value}");
        assertEquals(Token.CURLY_OPEN, tokenizer.next());
        checkValue("key", ":", "value");
        checkEvents(Token.CURLY_CLOSE, Token.END_OF_STREAM);
    }

    @Test
    public void severalKeyValue() throws Exception {
        tokenizer = new Tokenizer("{key:value, key2 : value2 }");
        checkEvents(Token.CURLY_OPEN);
        checkValue("key", ":", "value");
        checkEvents(Token.COMMA);
        checkValue("key2", ":", "value2");
        checkEvents(Token.CURLY_CLOSE, Token.END_OF_STREAM);
    }

    @Test
    public void valueWithSpaces() throws Exception {
        tokenizer = new Tokenizer("{'big,:{}key':'big value'}");
        checkEvents(Token.CURLY_OPEN);
        checkValue("big,:{}key", ":", "big value");
        checkEvents(Token.CURLY_CLOSE, Token.END_OF_STREAM);
    }

    @Test
    public void whiteSpacesAreIgnored() throws Exception {
        tokenizer = new Tokenizer("{ \n \r \t big : \r \n \r\n value }");
        checkEvents(Token.CURLY_OPEN);
        checkValue("big", ":", "value");
        checkEvents(Token.CURLY_CLOSE, Token.END_OF_STREAM);
    }

    void checkValue(String... values) throws Exception {
        for(String value : values) {
            assertEquals(Token.VALUE, tokenizer.next());
            assertEquals(value, tokenizer.value());
        }
    }

    void checkEvents(Token... tokens) throws Exception {
        for(Token token : tokens)
            assertEquals(token, tokenizer.next());
    }
}