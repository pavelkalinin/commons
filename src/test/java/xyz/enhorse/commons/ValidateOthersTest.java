package xyz.enhorse.commons;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static xyz.enhorse.commons.Validate.maximumIfGreater;
import static xyz.enhorse.commons.Validate.minimumIfLess;

/**
 * Tests for {@link Validate}
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 * 24.03.2016
 */
public class ValidateOthersTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_defaultIsExist()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());

        constructor.setAccessible(false);
    }


    @Test
    public void constructor_defaultIsPrivate()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        exception.expect(IllegalAccessException.class);
        exception.expectMessage("private");

        assertNull(constructor.newInstance());
    }


    @Test
    public void notNull_notNullValue() {
        String string = "value";
        assertEquals(string, Validate.notNull("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNull_nullValue() {
        assertNull(Validate.notNull("value", null));
    }


    @Test
    public void notNullOrEmpty_notNullValue() {
        String string = "value";
        assertEquals(string, Validate.notNullOrEmpty("value", string));
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_nullValue() {
        Validate.notNullOrEmpty("value", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_emptyValue() {
        String string = "";
        Validate.notNullOrEmpty("value", string);
    }


    @Test
    public void notNullOrEmpty_onlySpacesValue() {
        String string = "  ";
        assertEquals(string, Validate.notNullOrEmpty("value", string));
    }


    @Test
    public void defaultIfNull_notNullValue() {
        String string = "value";
        assertEquals("value", Validate.defaultIfNull(string, ""));
    }


    @Test
    public void defaultIfNull_nullValue() {
        assertEquals("value", Validate.defaultIfNull(null, "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_notNullValue() {
        String string = "default";
        assertEquals("default", Validate.defaultIfNullOrEmpty(string, ""));
    }


    @Test
    public void defaultIfNullOrEmpty_nullValue() {
        assertEquals("value", Validate.defaultIfNullOrEmpty(null, "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_empty() {
        assertEquals("value", Validate.defaultIfNullOrEmpty("", "value"));
    }


    @Test
    public void defaultIfNullOrEmpty_onlySpaces() {
        assertEquals("   ", Validate.defaultIfNullOrEmpty("   ", "value"));
    }


    @Test
    public void required_NotNullValue() {
        String string = "value";
        Validate.required("string", string);
    }


    @Test(expected = IllegalStateException.class)
    public void required_NullValue() {
        Validate.required("string", null);
    }


    @Test
    public void isIdentifier() {
        Validate.identifier("test parameter", "string");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_NullValue() {
        Validate.identifier("test parameter", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithSpace() {
        Validate.identifier("test parameter", "string value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithIllegalCharacter() {
        Validate.identifier("test parameter", "string%value");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_EmptyValue() {
        Validate.identifier("test parameter", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isIdentifier_ValueWithOnlyIllegalCharacters() {
        Validate.identifier("test parameter", " ^ ");
    }


    @Test
    public void isUrlSafe_allValid() {
        Validate.urlSafe("test parameter", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._");
    }


    @Test
    public void isUrlSafe_onlyLetter() {
        Validate.urlSafe("test parameter", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }


    @Test
    public void isUrlSafe_onlyDigits() {
        Validate.urlSafe("test parameter", "0123456789");
    }


    @Test
    public void isUrlSafe_onlyValidSymbols() {
        Validate.urlSafe("test parameter", "-._");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_nullValue() {
        Validate.urlSafe("test parameter", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithIllegalSymbol() {
        Validate.urlSafe("test parameter", "Ab.&-");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithOnlyIllegalSymbol() {
        Validate.urlSafe("test parameter", "&");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithOnlyIllegalSymbols() {
        Validate.urlSafe("test parameter", " &%");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_valueWithUnicodeLetters() {
        Validate.urlSafe("test parameter", "тест");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlSafe_emptyValue() {
        Validate.urlSafe("test parameter", "");
    }


    //minimumIfLess


    @Test
    public void minimumIfLess_greaterThanBoundary() {
        LocalDate value = LocalDate.now();
        LocalDate boundary = value.minusYears(1);
        assertEquals(value, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_lessThanBoundary() {
        LocalDate value = LocalDate.now();
        LocalDate boundary = value.plusYears(1);
        assertEquals(boundary, minimumIfLess(value, boundary));
    }


    @Test
    public void minimumIfLess_equalsToBoundary() {
        LocalDate value = LocalDate.now();
        assertEquals(value, minimumIfLess(value, value));
    }


    @Test
    public void minimumIfLess_boundaryIsNull() {
        LocalDate value = LocalDate.now();
        assertEquals(value, minimumIfLess(value, null));
    }


    @Test
    public void minimumIfLess_valueIsNull() {
        LocalDate boundary = LocalDate.now();
        assertEquals(boundary, minimumIfLess(null, boundary));
    }


    @Test
    public void minimumIfLess_boundaryAndValueAreNull() {
        assertNull(minimumIfLess(null, null));
    }

    //maximumIfGreater


    @Test
    public void maximumIfGreater_lessThanBoundary() {
        LocalDate value = LocalDate.now();
        LocalDate boundary = value.plusYears(1);
        assertEquals(value, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_greaterThanBoundary() {
        LocalDate value = LocalDate.now();
        LocalDate boundary = value.minusYears(1);
        assertEquals(boundary, maximumIfGreater(value, boundary));
    }


    @Test
    public void maximumIfGreater_equalsToBoundary() {
        LocalDate value = LocalDate.now();
        assertEquals(value, maximumIfGreater(value, value));
    }


    @Test
    public void maximumIfGreater_boundaryIsNull() {
        LocalDate value = LocalDate.now();
        assertEquals(value, maximumIfGreater(value, null));
    }


    @Test
    public void maximumIfGreater_valueIsNull() {
        LocalDate boundary = LocalDate.now();
        assertEquals(boundary, maximumIfGreater(null, boundary));
    }


    @Test
    public void maximumIfGreater_boundaryAndValueAreNull() {
        assertNull(maximumIfGreater(null, null));
    }
}