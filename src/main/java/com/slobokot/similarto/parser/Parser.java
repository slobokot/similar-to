package com.slobokot.similarto.parser;

import com.slobokot.similarto.matcher.TripleV;

public class Parser {

    public TripleV parse(String text) {
        try {
            Tokenizer tokenizer = new Tokenizer(text);
            MutableTriple triple = parseTriple(tokenizer);
            return TripleV.create(triple);
        }
        catch(ParseException ex) {
            throw new RuntimeException(ex.getMessage() + Stack.get(text, ex.getPosition()));
        }
    }

    private MutableTriple parseTriple(Tokenizer tokenizer) throws ParseException{
        MutableTriple triple = new MutableTriple(tokenizer.position());
        MutableItem key = null;
        MutableItem matcher = null;
        MutableItem value;

        value = parseItem(tokenizer);

        switch(tokenizer.clone().next()){
            case CURLY_OPEN:
            case VALUE:
                matcher = value;
                value = parseItem(tokenizer);
                break;
            case END_OF_STREAM:
            case COMMA:
            case CURLY_CLOSE:
                break;
            default:
                throw defaultException(tokenizer);
        }

        switch(tokenizer.clone().next()){
            case CURLY_OPEN:
            case VALUE:
                key = matcher;
                matcher = value;
                value = parseItem(tokenizer);
                break;
            case END_OF_STREAM:
            case COMMA:
            case CURLY_CLOSE:
                break;
            default:
                throw defaultException(tokenizer);
        }

        triple.setKey(key);
        triple.setMatcher(matcher);
        triple.setValue(value);
        return triple;
    }

    private MutableItem parseItem(Tokenizer tokenizer) throws ParseException{
        MutableItem item;

        switch(tokenizer.next()) {
            case VALUE:
                item = new MutableItem(tokenizer.position(), tokenizer.value());
                return item;
            case CURLY_OPEN:
                item = new MutableItem(tokenizer.position());

                if(tokenizer.clone().next() == Tokenizer.Token.CURLY_CLOSE){
                    tokenizer.next();
                    return item;
                }

                while(true) {
                    MutableTriple triple = parseTriple(tokenizer);
                    item.addTriple(triple);
                    switch(tokenizer.next()) {
                        case CURLY_CLOSE:
                            return item;
                        case COMMA:
                            break;
                        default:
                            throw defaultException(tokenizer);
                    }
                }
            case END_OF_STREAM:
                return null;
            default:
                throw defaultException(tokenizer);
        }
    }

    private static RuntimeException defaultException(Tokenizer tokenizer) {
        return new RuntimeException(String.format("Unexpected token %s %s", tokenizer.current(), Stack.get(tokenizer)));
    }
}
