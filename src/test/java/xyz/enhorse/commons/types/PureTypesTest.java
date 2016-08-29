package xyz.enhorse.commons.types;

import org.junit.Test;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.types.PureTypes.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         29.08.2016
 */
public class PureTypesTest {

    @Test
    public void convert_NULL() throws Exception {
        assertNull(PureTypes.convert(null));
    }


    @Test
    public void cast_NULL() throws Exception {
        assertNull(NULL.cast(null));
    }


    @Test
    public void pureValueOf_NULL() throws Exception {
        assertNull(PureTypes.pureValueOf(null).value());
    }


    @Test
    public void convert_BOOLEAN() throws Exception {
        assertEquals(Boolean.TRUE, PureTypes.convert("true"));
    }


    @Test
    public void cast_BOOLEAN() throws Exception {
        assertEquals(Boolean.FALSE, BOOLEAN.cast("false"));
    }


    @Test
    public void pureValueOf_BOOLEAN() throws Exception {
        assertTrue(PureTypes.pureValueOf("true").value());
    }


    @Test
    public void convert_INTEGER() throws Exception {
        int expected = 123;
        Object actual = PureTypes.convert(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void cast_INTEGER() throws Exception {
        int expected = 543;
        Object actual = INTEGER.cast(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void pureValueOf_INTEGER() throws Exception {
        int expected = -978;
        int actual = PureTypes.pureValueOf(String.valueOf(expected)).value();
        assertEquals(expected, actual);
    }


    @Test
    public void convert_LONG() throws Exception {
        long expected = Long.MAX_VALUE - 5;
        Object actual = PureTypes.convert(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void cast_LONG() throws Exception {
        long expected = Long.MAX_VALUE - 5;
        Object actual = LONG.cast(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void pureValueOf_LONG() throws Exception {
        long expected = Long.MAX_VALUE - 5;
        long actual = PureTypes.pureValueOf(String.valueOf(expected)).value();
        assertEquals(expected, actual);
    }


    @Test
    public void convert_DOUBLE() throws Exception {
        double expected = Math.PI;
        Object actual = PureTypes.convert(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void cast_DOUBLE() throws Exception {
        double expected = Math.PI;
        Object actual = DOUBLE.cast(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void pureValueOf_DOUBLE() throws Exception {
        double expected = Math.PI;
        double actual = PureTypes.pureValueOf(String.valueOf(expected)).value();
        assertEquals(expected, actual, 0.0001);
    }


    @Test
    public void convert_CHAR() throws Exception {
        char expected = '*';
        Object actual = PureTypes.convert(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void cast_CHAR() throws Exception {
        char expected = '*';
        Object actual = CHAR.cast(String.valueOf(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void pureValueOf_CHAR() throws Exception {
        char expected = '*';
        char actual = PureTypes.pureValueOf(String.valueOf(expected)).value();
        assertEquals(expected, actual);
    }


    @Test
    public void convert_STRING() throws Exception {
        String expected = "string";
        Object actual = PureTypes.convert(expected);
        assertEquals(expected, actual);
    }


    @Test
    public void cast_STRING() throws Exception {
        String expected = "string";
        Object actual = STRING.cast(expected);
        assertEquals(expected, actual);
    }


    @Test
    public void pureValueOf_STRING() throws Exception {
        String expected = "string";
        String actual = PureTypes.pureValueOf(expected).value();
        assertEquals(expected, actual);
    }
}