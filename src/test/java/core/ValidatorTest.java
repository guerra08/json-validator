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
        String validContent = "{\"id\": \"test\"}";
        assertTrue(validator.validateJSONString(validEmpty));
        assertTrue(validator.validateJSONString(validContent));
    }

    @Test
    void testInvalidJSONString(){
        String invalidEmpty = "[{}, {]";
        String invalidContent = "{\"aa}";
        assertFalse(validator.validateJSONString(invalidEmpty));
        assertFalse(validator.validateJSONString(invalidContent));
    }

}
