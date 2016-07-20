package xyz.enhorse.commons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.03.2016
 */
public class ValidateTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_DefaultIsExist() throws Exception {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());

        constructor.setAccessible(false);
    }


    @Test
    public void constructor_DefaultIsPrivate() throws Exception {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        exception.expect(IllegalAccessException.class);
        exception.expectMessage("private");

        assertNull(constructor.newInstance());
    }


    @Test
    public void notNull_NotNullValue() throws Exception {
        String string = "value";
        assertEquals(string, Validate.notNull("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNull_NullValue() throws Exception {
        assertNull(Validate.notNull("value", null));
    }


    @Test
    public void notNullOrEmpty_NotNullValue() throws Exception {
        String string = "value";
        assertEquals(string, Validate.notNullOrEmpty("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_NullValue() throws Exception {
        Validate.notNullOrEmpty("value", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_EmptyValue() throws Exception {
        String string = "";
        Validate.notNullOrEmpty("value", string);
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_OnlySpacesValue() throws Exception {
        String string = "  ";
        Validate.notNullOrEmpty("value", string);
    }


    @Test
    public void defaultIfNull_NotNullValue() throws Exception {
        String string = "value";
        assertEquals("value", Validate.defaultIfNull(string, ""));
    }


    @Test
    public void defaultIfNull_NullValue() throws Exception {
        assertEquals("value", Validate.defaultIfNull(null, "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_NotNullValue() throws Exception {
        String string = "default";
        assertEquals("default", Validate.defaultIfNullOrEmpty(string, ""));
    }


    @Test
    public void defaultIfNullOrEmpty_NullValue() throws Exception {
        assertEquals("value", Validate.defaultIfNullOrEmpty(null, "value"));
    }


    @Test
    public void required_NotNullValue() throws Exception {
        String string = "value";
        Validate.required("string", string);
    }


    @Test(expected = IllegalStateException.class)
    public void required_NullValue() throws Exception {
        Validate.required("string", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void onlyLettersAndDigits_NullValue() throws Exception {
        Validate.isIdentifier("test parameter", null);
    }


    @Test
    public void onlyLettersAndDigits_ValidValue() throws Exception {
        Validate.isIdentifier("test parameter", "string");
    }


    @Test(expected = IllegalArgumentException.class)
    public void onlyLettersAndDigits_ValueWithSpace() throws Exception {
        Validate.isIdentifier("test parameter", "string value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void onlyLettersAndDigits_ValueWithIllegalCharacter() throws Exception {
        Validate.isIdentifier("test parameter", "string%value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void onlyLettersAndDigits_EmptyValue() throws Exception {
        Validate.isIdentifier("test parameter", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void onlyLettersAndDigits_ValueWithOnlyIllegalCharacters() throws Exception {
        Validate.isIdentifier("test parameter", " ^ ");
    }
}