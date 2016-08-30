package xyz.enhorse.commons.parameters;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         30.08.2016
 */
public class ParameterTest {

    private static final String NAME = "parameter";
    private static final int INT_VALUE = 42;
    private static final String STRING_VALUE = "value";


    @Test
    public void create_intValue() throws Exception {
        Parameter parameter = new Parameter<>(NAME, INT_VALUE);
        assertNotNull(parameter);
    }


    @Test
    public void create_StringValue() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertNotNull(parameter);
    }


    @Test
    public void create_nullValue() throws Exception {
        Parameter parameter = new Parameter<>(NAME, null);
        assertNotNull(parameter);
    }


    @Test
    public void create_emptyValue() throws Exception {
        Parameter parameter = new Parameter<>(NAME, "");
        assertNotNull(parameter);
    }


    @Test
    public void create_UrlUnsafeValue() throws Exception {
        Parameter parameter = new Parameter<>(NAME, "*");
        assertNotNull(parameter);
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nullName() throws Exception {
        assertNull(new Parameter<>(null, STRING_VALUE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_emptyName() throws Exception {
        assertNull(new Parameter<>("", STRING_VALUE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nameFromSpaces() throws Exception {
        assertNull(new Parameter<>("  ", STRING_VALUE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nameNotUrlSafe() throws Exception {
        assertNull(new Parameter<>("*", STRING_VALUE));
    }


    @Test
    public void name() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertEquals(NAME, parameter.name());
    }


    @Test
    public void type_String() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertEquals(STRING_VALUE.getClass(), parameter.type());
    }


    @Test
    public void type_int() throws Exception {
        Parameter parameter = new Parameter<>(NAME, INT_VALUE);
        assertEquals(int.class, parameter.type());
    }


    @Test
    public void type_null() throws Exception {
        Parameter parameter = new Parameter<>(NAME, null);
        assertNull(parameter.type());
    }


    @Test
    public void value_int() throws Exception {
        Parameter parameter = new Parameter<>(NAME, INT_VALUE);
        assertEquals(INT_VALUE, parameter.value());
    }


    @Test
    public void value_String() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertEquals(STRING_VALUE, parameter.value());
    }


    @Test
    public void value_null() throws Exception {
        Parameter parameter = new Parameter<>(NAME, null);
        assertNull(parameter.value());
    }


    @Test
    public void toString_output() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        String toString = parameter.toString();
        assertTrue("doesn't contain a name", toString.contains(NAME));
        assertTrue("doesn't contain a value", toString.contains(STRING_VALUE));
        assertTrue("doesn't contain a type", toString.contains(STRING_VALUE.getClass().getSimpleName()));
    }


    @Test
    public void toString_null() throws Exception {
        Parameter parameter = new Parameter<>(NAME, null);
        String toString = parameter.toString();
        assertTrue("doesn't contain a name", toString.contains(NAME));
        assertTrue("doesn't contain a value", toString.contains("null"));
    }


    @Test
    public void equals_same() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertEquals(parameter, parameter);
    }


    @Test
    public void equals_identical() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME, STRING_VALUE);

        assertEquals(parameter1, parameter2);
    }


    @Test
    public void notEquals_differentValues() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME, STRING_VALUE + STRING_VALUE);

        assertNotEquals(parameter1, parameter2);
    }


    @Test
    public void notEquals_differentNames() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME + NAME, STRING_VALUE);

        assertNotEquals(parameter1, parameter2);
    }


    @Test
    public void notEquals_differentTypes() throws Exception {
        byte byteValue = 7;
        int intValue = 7;
        Parameter parameter1 = new Parameter<>(NAME, byteValue);
        Parameter parameter2 = new Parameter<>(NAME, intValue);

        assertNotEquals(parameter1, parameter2);
    }


    @Test
    public void notEquals_null() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);

        assertNotEquals(parameter, null);
    }


    @Test
    public void equals_nullValue() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, null);
        Parameter parameter2 = new Parameter<>(NAME, null);

        assertEquals(parameter1, parameter2);
    }


    @Test
    public void hashCode_same() throws Exception {
        Parameter parameter = new Parameter<>(NAME, STRING_VALUE);
        assertEquals(parameter.hashCode(), parameter.hashCode());
    }


    @Test
    public void hashCode_identical() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME, STRING_VALUE);

        assertEquals(parameter1.hashCode(), parameter2.hashCode());
    }


    @Test
    public void hashCode_differentValues() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME, STRING_VALUE + STRING_VALUE);

        assertNotEquals(parameter1.hashCode(), parameter2.hashCode());
    }


    @Test
    public void hashCode_differentNames() throws Exception {
        Parameter parameter1 = new Parameter<>(NAME, STRING_VALUE);
        Parameter parameter2 = new Parameter<>(NAME + NAME, STRING_VALUE);

        assertNotEquals(parameter1.hashCode(), parameter2.hashCode());
    }


    @Test
    public void hashCode_differentTypes() throws Exception {
        byte byteValue = 7;
        int intValue = 7;
        Parameter parameter1 = new Parameter<>(NAME, byteValue);
        Parameter parameter2 = new Parameter<>(NAME, intValue);

        assertNotEquals(parameter1.hashCode(), parameter2.hashCode());
    }


    @Ignore
    @Test
    public void immutable() throws Exception {
        List<String> original = new ArrayList<>();
        original.add(NAME);

        Parameter<List<String>> parameter = new Parameter<>("list", original);
        original.add(STRING_VALUE);

        List<String> actual = parameter.value();

        assertNotEquals(original, actual);
    }
}