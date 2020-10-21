package lexer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Collections {

    public final static Set<Character> KEYWORDS = new HashSet<>(Arrays.asList('{', '}', '[', ']', ':', ',', '"'));
    public final static Set<String> CONSTANTS = new HashSet<>(Arrays.asList("true", "false", "null"));

}
