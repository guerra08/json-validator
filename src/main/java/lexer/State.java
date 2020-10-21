package lexer;

public enum State {

    LEFT_OBJECT,
    LEFT_ARRAY,
    RIGHT_OBJECT,
    RIGHT_ARRAY,
    STRING_START,
    STRING_VALUE,
    STRING_END,
    NUMBER_VALUE,
    NIL,
    ARRAY_ITEM,
    KEY_TO_VALUE,
    KEY_START,
    KEY_VALUE,
    KEY_END,
    SEPARATOR,

}
