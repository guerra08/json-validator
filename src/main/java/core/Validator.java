package core;

import java.util.*;

public class Validator {

    private final Set<Character> openingSymbols = new HashSet<>(Arrays.asList('{', '['));
    private final Set<Character> closingSymbols = new HashSet<>(Arrays.asList('}', ']'));
    private final Set<Map.Entry<Character, Character>> pairs = new HashSet<>(
            Arrays.asList(Map.entry('{', '}'), Map.entry('[', ']'), Map.entry('"', '"'))
    );

    /**
     * TODO: Validate the contents of the JSON. Currently, it only checks the skeleton
     * Validates a JSON String
     * @param json String JSON
     * @return boolean
     */
    public boolean validateJSONString(String json){
        Stack<Character> openingStack = new Stack<>();
        Stack<Character> closingStack = new Stack<>();
        for (Character current : json.toCharArray()){
            if(isOpeningSymbol(current)){
                openingStack.push(current);
            }
            if(isClosingSymbol(current)){
                closingStack.push(current);
            }
            if(!openingStack.isEmpty() && !closingStack.isEmpty()){
                if(matches(openingStack.peek(), closingStack.peek())){
                    openingStack.pop();
                    closingStack.pop();
                }
            }
        }
        return openingStack.isEmpty() && closingStack.isEmpty();
    }

    private boolean isOpeningSymbol(Character c){
        return openingSymbols.contains(c);
    }

    private boolean isDoubleQuotes(Character c){
        return c.equals('"');
    }

    private boolean isClosingSymbol(Character c){
        return closingSymbols.contains(c);
    }

    private boolean matches(Character first, Character second){
        Map.Entry<Character, Character> pair = Map.entry(first, second);
        return pairs.contains(pair);
    }

}
