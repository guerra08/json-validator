package core;

import java.util.*;

public class Validator {

    private final Set<Character> openingSymbols = new HashSet<>(Arrays.asList('{', '[', '"'));
    private final Set<Character> closingSymbols = new HashSet<>(Arrays.asList('}', ']', '"'));
    private final Set<Map.Entry<Character, Character>> pairs = new HashSet<>(
            Arrays.asList(Map.entry('{', '}'), Map.entry('[', ']'), Map.entry('"', '"'))
    );

    /**
     * Validates a JSON String
     * @param json String JSON
     * @return boolean
     */
    public boolean validateJSONString(String json){
        Stack<Character> opening = new Stack<>();
        json.chars().forEach(c -> {
            Character current = (char) c;
            if(isClosingSymbol(current) && matches(opening.peek(), current)){
                opening.pop();
            }
            else if(isOpeningSymbol(current)) opening.push(current);
        });
        return opening.isEmpty();
    }

    private boolean isOpeningSymbol(Character c){
        return openingSymbols.contains(c);
    }

    private boolean isClosingSymbol(Character c){
        return closingSymbols.contains(c);
    }

    private boolean matches(Character first, Character second){
        Map.Entry<Character, Character> pair = Map.entry(first, second);
        return pairs.contains(pair);
    }

}
