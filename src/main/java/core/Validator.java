package core;

import exception.InvalidTokenExcpetion;
import lexer.State;
import lexer.Token;
import lexer.TokenName;

import java.util.Stack;

public class Validator {
    /**
     * TODO: Add all the possible cases
     * Validates a JSON String
     * @throws Exception Invalid syntax
     * @param json String JSON
     * @return boolean
     */
    public boolean validateJSONString(String json) throws Exception{
        if(!json.isBlank() && !json.isEmpty()){
            Stack<State> stateStack = new Stack<>();
            stateStack.push(State.NIL);
            Token currentToken;
            for (Character c : json.toCharArray()){
                currentToken = new Token(c);
                switch (stateStack.peek()){
                    case NIL:
                        if(currentToken.getName() == TokenName.KEYWORD){
                            if(currentToken.getValue().equals('{')){
                                stateStack.push(State.LEFT_OBJECT);
                            }
                            else if(currentToken.getValue().equals('[')){
                                stateStack.push(State.LEFT_ARRAY);
                            }
                            else{
                                throw new InvalidTokenExcpetion();
                            }
                        }
                        else
                            throw new InvalidTokenExcpetion();
                        break;
                    case LEFT_OBJECT:
                        if(currentToken.getName() == TokenName.KEYWORD){
                            if(currentToken.getValue().equals('}')) stateStack.pop();
                            if(currentToken.getValue().equals('"')) stateStack.push(State.KEY_START);
                        }
                        break;
                    case LEFT_ARRAY:
                        if(currentToken.getName() == TokenName.KEYWORD){
                            if(currentToken.getValue().equals('{')){
                                stateStack.push(State.LEFT_OBJECT);
                            }
                            if(currentToken.getValue().equals('[')){
                                stateStack.push(State.LEFT_ARRAY);
                            }
                            if(currentToken.getValue().equals(']')) stateStack.pop();
                        }
                        break;
                    case KEY_START:
                        if(currentToken.getName() == TokenName.LETTER || currentToken.getName() == TokenName.NUMBER){
                            stateStack.pop();
                            stateStack.push(State.KEY_VALUE);
                        }
                        break;
                    case KEY_VALUE:
                        if(currentToken.getName() == TokenName.LETTER || currentToken.getName() == TokenName.NUMBER)
                            continue;
                        if(currentToken.getName() == TokenName.KEYWORD && currentToken.getValue().equals('"')){
                            stateStack.pop();
                            stateStack.push(State.KEY_END);
                        }
                        break;
                    case KEY_END:
                        if(currentToken.getName() == TokenName.KEYWORD && currentToken.getValue().equals(':')){
                            stateStack.pop();
                            stateStack.push(State.KEY_TO_VALUE);
                        }
                        else throw new InvalidTokenExcpetion();
                        break;
                    case KEY_TO_VALUE:
                        if(currentToken.getName() == TokenName.KEYWORD){
                            if(currentToken.getValue().equals('{')){
                                stateStack.pop();
                                stateStack.push(State.LEFT_OBJECT);
                            }
                            else if(currentToken.getValue().equals('[')){
                                stateStack.pop();
                                stateStack.push(State.LEFT_ARRAY);
                            }
                            else if(currentToken.getValue().equals('"')){
                                stateStack.pop();
                                stateStack.push(State.STRING_START);
                            }
                            else throw new InvalidTokenExcpetion();
                        }
                        if(currentToken.getName() == TokenName.NUMBER){
                            stateStack.push(State.NUMBER_VALUE);
                        }
                        break;
                    case STRING_START:
                        if(currentToken.getName() == TokenName.LETTER || currentToken.getName() == TokenName.NUMBER){
                            stateStack.pop();
                            stateStack.push(State.STRING_VALUE);
                        }
                        break;
                    case STRING_VALUE:
                        if(currentToken.getName() == TokenName.LETTER || currentToken.getName() == TokenName.NUMBER)
                            continue;
                        if(currentToken.getName() == TokenName.KEYWORD && currentToken.getValue().equals('"')){
                            stateStack.pop();
                            stateStack.push(State.STRING_END);
                        }
                        break;
                    case STRING_END:
                        if(currentToken.getName() == TokenName.KEYWORD){
                            if(currentToken.getValue().equals(',')){
                                stateStack.pop();
                                stateStack.push(State.SEPARATOR);
                            }
                            if(currentToken.getValue().equals('}')){
                                stateStack.pop();
                                stateStack.push(State.RIGHT_OBJECT);
                            }
                            if(currentToken.getValue().equals(']')){
                                stateStack.pop();
                                stateStack.push(State.RIGHT_ARRAY);
                            }
                        }

                }
            }
            State endState = stateStack.pop();
            return endState == State.RIGHT_ARRAY || endState == State.RIGHT_OBJECT || endState == State.NIL;
        }
        return false;
    }

}
