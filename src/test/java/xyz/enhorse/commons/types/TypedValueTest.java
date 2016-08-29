package xyz.enhorse.commons.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         29.08.2016
 */
public class TypedValueTest {

    @Test
    public void value() throws Exception {
        String expected = "string";
        TypedValue value = new TypedValue(expected);
        assertEquals(expected, value.value());
    }


    @Test
    public void type() throws Exception {
        String expected = "string";
        TypedValue value = new TypedValue(expected);
        assertEquals(String.class, value.type());
    }

}