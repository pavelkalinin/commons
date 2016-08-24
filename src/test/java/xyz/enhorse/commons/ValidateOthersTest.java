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
public class ValidateOthersTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_defaultIsExist() throws Exception {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());

        constructor.setAccessible(false);
    }


    @Test
    public void constructor_defaultIsPrivate() throws Exception {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        exception.expect(IllegalAccessException.class);
        exception.expectMessage("private");

        assertNull(constructor.newInstance());
    }


    @Test
    public void notNull_notNullValue() throws Exception {
        String string = "value";
        assertEquals(string, Validate.notNull("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNull_nullValue() throws Exception {
        assertNull(Validate.notNull("value", null));
    }


    @Test
    public void notNullOrEmpty_notNullValue() throws Exception {
        String string = "value";
        assertEquals(string, Validate.notNullOrEmpty("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_nullValue() throws Exception {
        Validate.notNullOrEmpty("value", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_emptyValue() throws Exception {
        String string = "";
        Validate.notNullOrEmpty("value", string);
    }


    @Test
    public void notNullOrEmpty_onlySpacesValue() throws Exception {
        String string = "  ";
        assertEquals(string, Validate.notNullOrEmpty("value", string));
    }


    @Test
    public void defaultIfNull_notNullValue() throws Exception {
        String string = "value";
        assertEquals("value", Validate.defaultIfNull(string, ""));
    }


    @Test
    public void defaultIfNull_nullValue() throws Exception {
        assertEquals("value", Validate.defaultIfNull(null, "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_notNullValue() throws Exception {
        String string = "default";
        assertEquals("default", Validate.defaultIfNullOrEmpty(string, ""));
    }


    @Test
    public void defaultIfNullOrEmpty_nullValue() throws Exception {
        assertEquals("value", Validate.defaultIfNullOrEmpty(null, "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_empty() throws Exception {
        assertEquals("value", Validate.defaultIfNullOrEmpty("", "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_onlySpaces() throws Exception {
        assertEquals("   ", Validate.defaultIfNullOrEmpty("   ", "value"));
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


    @Test
    public void isIdentifier() throws Exception {
        Validate.identifier("test parameter", "string");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_NullValue() throws Exception {
        Validate.identifier("test parameter", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithSpace() throws Exception {
        Validate.identifier("test parameter", "string value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithIllegalCharacter() throws Exception {
        Validate.identifier("test parameter", "string%value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_EmptyValue() throws Exception {
        Validate.identifier("test parameter", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithOnlyIllegalCharacters() throws Exception {
        Validate.identifier("test parameter", " ^ ");
    }


    @Test
    public void isUrlSafe_allValid() throws Exception {
        Validate.urlSafe("test parameter", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._");
    }


    @Test
    public void isUrlSafe_onlyLetter() throws Exception {
        Validate.urlSafe("test parameter", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }


    @Test
    public void isUrlSafe_onlyDigits() throws Exception {
        Validate.urlSafe("test parameter", "0123456789");
    }


    @Test
    public void isUrlSafe_onlyValidSymbols() throws Exception {
        Validate.urlSafe("test parameter", "-._");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_nullValue() throws Exception {
        Validate.urlSafe("test parameter", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithIllegalSymbol() throws Exception {
        Validate.urlSafe("test parameter", "Ab.&-");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithOnlyIllegalSymbol() throws Exception {
        Validate.urlSafe("test parameter", "&");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithOnlyIllegalSymbols() throws Exception {
        Validate.urlSafe("test parameter", " &%");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithUnicodeLetters() throws Exception {
        Validate.urlSafe("test parameter", "тест");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_emptyValue() throws Exception {
        Validate.urlSafe("test parameter", "");
    }
}