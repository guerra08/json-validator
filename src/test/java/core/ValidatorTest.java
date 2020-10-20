package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = new Validator();
    }

    @Test
    void testValidJSONString(){
        String validEmpty = "[{}, {}]";
        String validArray = "[]";
        String validContent = "{\"id\": \"test\"}";
        String validWithNull = "{\"id\": nul}";
        assertTrue(validator.validateJSONString(validEmpty));
        assertTrue(validator.validateJSONString(validContent));
        assertTrue(validator.validateJSONString(validWithNull));
        assertTrue(validator.validateJSONString(validArray));
    }

    @Test
    void testInvalidJSONString(){
        String invalidEmpty = "[{}, {]";
        String invalidArray = "[ ]]";
        String invalidContent = "{\"aa}";
        String invalidObject = "{1}";
        assertFalse(validator.validateJSONString(invalidEmpty));
        assertFalse(validator.validateJSONString(invalidArray));
        //assertFalse(validator.validateJSONString(invalidContent));
        //assertFalse(validator.validateJSONString(invalidObject));
    }

}
