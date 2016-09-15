package xyz.enhorse.commons;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         15/09/16
 */
public class PureValueTest {

    @Test
    public void type_BOOLEAN() throws Exception {
        PureValue value = new PureValue(Boolean.FALSE);

        assertEquals(PureTypes.BOOLEAN, value.type());
    }


    @Test
    public void type_STRING() throws Exception {
        PureValue value = new PureValue("value");

        assertEquals(PureTypes.STRING, value.type());
    }


    @Test
    public void type_NULL() throws Exception {
        PureValue value = new PureValue(null);

        assertEquals(PureTypes.NULL, value.type());
    }


    @Test
    public void type_CHAR() throws Exception {
        PureValue value = new PureValue('a');

        assertEquals(PureTypes.CHAR, value.type());
    }


    @Test
    public void type_INTEGER() throws Exception {
        PureValue value = new PureValue(Integer.MAX_VALUE);

        assertEquals(PureTypes.INTEGER, value.type());
    }


    @Test
    public void type_LONG() throws Exception {
        PureValue value = new PureValue(Long.MAX_VALUE);

        assertEquals(PureTypes.LONG, value.type());
    }


    @Test
    public void type_DOUBLE() throws Exception {
        PureValue value = new PureValue(Double.MAX_VALUE);

        assertEquals(PureTypes.DOUBLE, value.type());
    }


    @Test
    public void type_Object() throws Exception {
        PureValue value = new PureValue(new Date());

        assertEquals(PureTypes.STRING, value.type());
    }


    @Test
    public void value_BOOLEAN() throws Exception {
        PureValue value = new PureValue(Boolean.FALSE);

        assertEquals(Boolean.FALSE, value.value());
    }


    @Test
    public void value_STRING() throws Exception {
        PureValue value = new PureValue("value");

        assertEquals("value", value.value());
    }


    @Test
    public void value_NULL() throws Exception {
        PureValue value = new PureValue(null);

        assertNull(value.value());
    }


    @Test
    public void value_CHAR() throws Exception {
        PureValue value = new PureValue('a');

        assertEquals('a', value.value());
    }


    @Test
    public void value_INTEGER() throws Exception {
        PureValue value = new PureValue(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, value.value());
    }


    @Test
    public void value_LONG() throws Exception {
        PureValue value = new PureValue(Long.MAX_VALUE);

        assertEquals(Long.MAX_VALUE, value.value());
    }


    @Test
    public void value_DOUBLE() throws Exception {
        PureValue value = new PureValue(Double.MAX_VALUE);

        assertEquals(Double.MAX_VALUE, (Double) value.value(), 0.0000001);
    }


    @Test
    public void value_Object() throws Exception {
        Date date = new Date();
        PureValue value = new PureValue(date);

        assertEquals(date.toString(), value.value());
    }


    @Test
    public void _toString() throws Exception {
        String toString = new PureValue(false).toString();

        assertTrue("doesn't contains value", toString.contains("false"));
        assertTrue("doesn't contains type", toString.contains(PureTypes.BOOLEAN.toString()));
    }


    @Test
    public void toString_CHAR() throws Exception {
        String toString = new PureValue('a').toString();

        assertTrue(toString.contains("\'a\'"));
    }


    @Test
    public void toString_STRING() throws Exception {
        String toString = new PureValue("aaa").toString();

        assertTrue(toString.contains("\"aaa\""));
    }


    @Test
    public void equals_same() throws Exception {
        PureValue value = new PureValue(false);

        assertEquals(value, value);
    }


    @Test
    public void equals_identical() throws Exception {
        PureValue value1 = new PureValue(false);
        PureValue value2 = new PureValue(false);

        assertEquals(value1, value2);
    }


    @Test
    public void equals_different() throws Exception {
        PureValue value1 = new PureValue(false);
        PureValue value2 = new PureValue(true);

        assertNotEquals(value1, value2);
    }


    @Test
    public void equals_null() throws Exception {
        PureValue value1 = new PureValue(false);

        assertNotEquals(value1, null);
    }


    @Test
    public void hashCode_identical() throws Exception {
        PureValue value1 = new PureValue(false);
        PureValue value2 = new PureValue(false);

        assertEquals(value1.hashCode(), value2.hashCode());
    }


    @Test
    public void hashCode_different() throws Exception {
        PureValue value1 = new PureValue(false);
        PureValue value2 = new PureValue(true);

        assertNotEquals(value1.hashCode(), value2.hashCode());
    }
}