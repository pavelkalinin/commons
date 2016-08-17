package xyz.enhorse.commons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17/08/16
 */
public class CheckTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_DefaultIsExist() throws Exception {
        Constructor<Check> constructor = Check.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());

        constructor.setAccessible(false);
    }


    @Test
    public void constructor_DefaultIsPrivate() throws Exception {
        Constructor<Check> constructor = Check.class.getDeclaredConstructor();
        exception.expect(IllegalAccessException.class);
        exception.expectMessage("private");

        assertNull(constructor.newInstance());
    }


    @Test
    public void isNullOrEmpty_notNullValue() throws Exception {
        String string = "value";
        assertFalse(Check.isNullOrEmpty(string));
    }


    @Test
    public void isNullOrEmpty_nullValue() throws Exception {
        assertTrue(Check.isNullOrEmpty(null));
    }


    @Test
    public void isNullOrEmpty_emptyValue() throws Exception {
        assertTrue(Check.isNullOrEmpty(""));
    }


    @Test
    public void isNullOrEmpty_onlySpacesValue() throws Exception {
        assertTrue(Check.isNullOrEmpty("    "));
    }


    @Test
    public void isIdentifier() throws Exception {
        assertTrue(Check.isIdentifier("string"));
    }


    @Test
    public void isIdentifier_nullValue() throws Exception {
        assertFalse(Check.isIdentifier(null));
    }


    @Test
    public void isIdentifier_valueWithSpace() throws Exception {
        assertFalse(Check.isIdentifier("str ing"));
    }


    @Test
    public void isIdentifier_valueWithIllegalCharacter() throws Exception {
        assertFalse(Check.isIdentifier("str%ing"));
    }


    @Test
    public void isIdentifier_emptyValue() throws Exception {
        assertFalse(Check.isIdentifier(""));
    }


    @Test
    public void isIdentifier_ValueWithOnlyIllegalCharacters() throws Exception {
        assertFalse(Check.isIdentifier(" >$ "));
    }


    @Test
    public void isUrlSafe_allValid() throws Exception {
        assertTrue(Check.isUrlSafe("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._"));
    }


    @Test
    public void isUrlSafe_onlyLetters() throws Exception {
        assertTrue(Check.isUrlSafe("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
    }


    @Test
    public void isUrlSafe_onlyDigits() throws Exception {
        assertTrue(Check.isUrlSafe("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
    }


    @Test
    public void isUrlSafe_onlyValidSymbols() throws Exception {
        assertTrue(Check.isUrlSafe("-._"));
    }


    @Test
    public void isUrlSafe_nullValue() throws Exception {
        assertFalse(Check.isUrlSafe(null));
    }


    @Test
    public void isUrlSafe_valueWithIllegalSymbol() throws Exception {
        assertFalse(Check.isUrlSafe("Ab_%."));
    }


    @Test
    public void isUrlSafe_valueWithOnlyIllegalSymbol() throws Exception {
        assertFalse(Check.isUrlSafe("&"));
    }


    @Test
    public void isUrlSafe_valueWithOnlyIllegalSymbols() throws Exception {
        assertFalse(Check.isUrlSafe("%^ >&"));
    }


    @Test
    public void isUrlSafe_emptyValue() throws Exception {
        assertFalse(Check.isUrlSafe(""));
    }
}