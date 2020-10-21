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
    void testValidEmptyJSONStrings() throws Exception {
        String validEmptyObject = "{}";
        String validEmptyArray = "[]";
        String validEmptyArrayInArray = "[[]]";
        String validEmptyObjectInArray = "[{}]";
        String inception = "[[[{}]]]";


        assertTrue(validator.validateJSONString(validEmptyObject));
        assertTrue(validator.validateJSONString(validEmptyArray));
        assertTrue(validator.validateJSONString(validEmptyArrayInArray));
        assertTrue(validator.validateJSONString(validEmptyObjectInArray));
        assertTrue(validator.validateJSONString(inception));
    }

    @Test
    void testInvalidEmptyJSONStrings() throws Exception {
        String invalidEmptyObject = "{";
        String invalidEmptyArrayInArray = "[[[]]";


        assertFalse(validator.validateJSONString(invalidEmptyObject));
        assertFalse(validator.validateJSONString(invalidEmptyArrayInArray));
    }

    @Test
    void testValidJSONStrings() throws Exception {
        String validWithOne = "{\"id\": \"juca123\"}";
        String validWithEmptyArray = "{\"id\": []}";
        String validWithEmptyObject = "{\"id\": {}}";
        String validWithObject = "{\"id\": {\"name\": \"bruno\"}}";

        assertTrue(validator.validateJSONString(validWithOne));
        assertTrue(validator.validateJSONString(validWithEmptyArray));
        assertTrue(validator.validateJSONString(validWithEmptyObject));
        assertTrue(validator.validateJSONString(validWithObject));
    }

    @Test
    void testInvalidJSONStrings() throws Exception {
        String invalidWithOne = "{\"id\": \"juca123}";
        String invalidWithEmptyArray = "{\"id\": [}";
        String invalidWithObject = "{\"id\": {\"name\": \"bruno}}";

        assertFalse(validator.validateJSONString(invalidWithOne));
        assertFalse(validator.validateJSONString(invalidWithEmptyArray));
        assertFalse(validator.validateJSONString(invalidWithObject));
    }

}
