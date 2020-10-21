package lexer;

import lombok.Getter;

public class Token {

    @Getter
    private final Character value;
    @Getter
    private TokenName name;

    public Token(Character value){
        this.value = value;
        setName();
    }

    private void setName(){
        if(Collections.KEYWORDS.contains(value)){
            this.name = TokenName.KEYWORD;
        }
        else if(Character.isDigit(value)){
            this.name = TokenName.NUMBER;
        }
        else if(!Character.isDigit(value)){
            this.name = TokenName.LETTER;
        }
        else if(value.equals('.')){
            this.name = TokenName.DEC_SEPARATOR;
        }
        else if(value.equals(' ')){
            this.name = TokenName.SPACE;
        }
    }

}
