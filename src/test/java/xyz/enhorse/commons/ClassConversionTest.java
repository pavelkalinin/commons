package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24/04/16
 */
public class ClassConversionTest {

    @Test
    public void asPrimitive_null() throws Exception {
        assertNull(new ClassConversion(null).asPrimitive());
    }


    @Test
    public void asPrimitive_byte() throws Exception {
        assertEquals(byte.class, new ClassConversion(byte.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Byte() throws Exception {
        assertEquals(byte.class, new ClassConversion(Byte.class).asPrimitive());
    }


    @Test
    public void asPrimitive_short() throws Exception {
        assertEquals(short.class, new ClassConversion(short.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Short() throws Exception {
        assertEquals(short.class, new ClassConversion(Short.class).asPrimitive());
    }


    @Test
    public void asPrimitive_int() throws Exception {
        assertEquals(int.class, new ClassConversion(int.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Integer() throws Exception {
        assertEquals(int.class, new ClassConversion(Integer.class).asPrimitive());
    }


    @Test
    public void asPrimitive_long() throws Exception {
        assertEquals(long.class, new ClassConversion(long.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Long() throws Exception {
        assertEquals(long.class, new ClassConversion(Long.class).asPrimitive());
    }


    @Test
    public void asPrimitive_float() throws Exception {
        assertEquals(float.class, new ClassConversion(float.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Float() throws Exception {
        assertEquals(float.class, new ClassConversion(Float.class).asPrimitive());
    }


    @Test
    public void asPrimitive_double() throws Exception {
        assertEquals(double.class, new ClassConversion(double.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Double() throws Exception {
        assertEquals(double.class, new ClassConversion(Double.class).asPrimitive());
    }


    @Test
    public void asPrimitive_char() throws Exception {
        assertEquals(char.class, new ClassConversion(char.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Character() throws Exception {
        assertEquals(char.class, new ClassConversion(Character.class).asPrimitive());
    }


    @Test
    public void asPrimitive_boolean() throws Exception {
        assertEquals(boolean.class, new ClassConversion(boolean.class).asPrimitive());
    }


    @Test
    public void asPrimitive_Boolean() throws Exception {
        assertEquals(boolean.class, new ClassConversion(Boolean.class).asPrimitive());
    }


    @Test(expected = IllegalStateException.class)
    public void asPrimitive_Reference() throws Exception {
        new ClassConversion(String.class).asPrimitive();
    }


    @Test
    public void asPrimitiveIfPossible_Primitive() throws Exception {
        assertEquals(boolean.class, new ClassConversion(Boolean.class).asPrimitiveIfPossible());
    }


    @Test
    public void asPrimitiveIfPossible_Reference() throws Exception {
        assertEquals(String.class, new ClassConversion(String.class).asPrimitiveIfPossible());
    }


    @Test
    public void asReference_null() throws Exception {
        assertNull(new ClassConversion(null).asReference());
    }


    @Test
    public void asReference_byte() throws Exception {
        assertEquals(Byte.class, new ClassConversion(byte.class).asReference());
    }


    @Test
    public void asReference_short() throws Exception {
        assertEquals(Short.class, new ClassConversion(short.class).asReference());
    }


    @Test
    public void asReference_int() throws Exception {
        assertEquals(Integer.class, new ClassConversion(int.class).asReference());
    }


    @Test
    public void asReference_long() throws Exception {
        assertEquals(Long.class, new ClassConversion(long.class).asReference());
    }


    @Test
    public void asReference_float() throws Exception {
        assertEquals(Float.class, new ClassConversion(float.class).asReference());
    }


    @Test
    public void asReference_double() throws Exception {
        assertEquals(Double.class, new ClassConversion(double.class).asReference());
    }


    @Test
    public void asReference_char() throws Exception {
        assertEquals(Character.class, new ClassConversion(char.class).asReference());
    }


    @Test
    public void asReference_boolean() throws Exception {
        assertEquals(Boolean.class, new ClassConversion(boolean.class).asReference());
    }


    @Test
    public void asReference_Reference() throws Exception {
        assertEquals(String.class, new ClassConversion(String.class).asReference());
    }
}