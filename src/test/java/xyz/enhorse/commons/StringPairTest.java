package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class StringPairTest {

    private static final String LEADING = "leading";
    private static final String TRAILING = "trailing";
    private static final String DELIMITER = "=";


    @Test
    public void constructor_validArguments() throws Exception {
        StringPair pair = new StringPair(LEADING, TRAILING);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void constructor_leading_null() throws Exception {
        StringPair pair = new StringPair(null, TRAILING);

        assertNotEquals("Leading is null", pair.leading());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void constructor_trailing_null() throws Exception {
        StringPair pair = new StringPair(LEADING, null);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertNotNull("Trailing is null", pair.trailing());
    }


    @Test
    public void constructor_leading_empty() throws Exception {
        StringPair pair = new StringPair("", TRAILING);

        assertTrue("Leading is not empty", pair.leading().isEmpty());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void constructor_trailing_empty() throws Exception {
        StringPair pair = new StringPair(LEADING, "");

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void leading() throws Exception {
        assertEquals(LEADING, new StringPair(LEADING, TRAILING).leading());
    }


    @Test
    public void trailing() throws Exception {
        assertEquals(TRAILING, new StringPair(LEADING, TRAILING).trailing());
    }


    @Test
    public void create_delimiter_string() throws Exception {
        StringPair pair = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void create_delimiter_char() throws Exception {
        char delimiter = '=';

        StringPair pair = StringPair.create(LEADING + delimiter + TRAILING, delimiter);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void create_leading_empty() throws Exception {
        StringPair pair = StringPair.create("" + DELIMITER + TRAILING, DELIMITER);

        assertTrue("Leading is not empty", pair.leading().isEmpty());
        assertEquals("Trailing is wrong", TRAILING, pair.trailing());
    }


    @Test
    public void create_trailing_empty() throws Exception {
        StringPair pair = StringPair.create(LEADING + DELIMITER + "", DELIMITER);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_withoutDelimiter() throws Exception {
        StringPair pair = StringPair.create(LEADING + TRAILING, DELIMITER);

        assertEquals("Leading is wrong", LEADING + TRAILING, pair.leading());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_string_null() throws Exception {
        StringPair pair = StringPair.create(null, DELIMITER);

        assertTrue("Leading is not empty", pair.leading().isEmpty());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_string_empty() throws Exception {
        StringPair pair = StringPair.create("", DELIMITER);

        assertTrue("Leading is not empty", pair.leading().isEmpty());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_string_onlySpaces_delimiter_notSpace() throws Exception {
        String leading = "   ";
        StringPair pair = StringPair.create(leading, "==");

        assertEquals("Leading is wrong", leading, pair.leading());
        assertTrue("Trailing is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_string_onlySpaces_delimiter_space() throws Exception {
        String trailing = "   ";
        StringPair pair = StringPair.create(" " + trailing, " ");

        assertTrue("Leading is not empty", pair.leading().isEmpty());
        assertEquals("Trailing is wrong", trailing, pair.trailing());
    }


    @Test
    public void create_delimiter_null() throws Exception {
        StringPair pair = StringPair.create(LEADING, null);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertTrue("Leading is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_delimiter_empty() throws Exception {
        StringPair pair = StringPair.create(LEADING, null);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertTrue("Leading is not empty", pair.trailing().isEmpty());
    }


    @Test
    public void create_multiple_delimiters() throws Exception {
        String trailing = TRAILING + DELIMITER + TRAILING;

        StringPair pair = StringPair.create(LEADING + DELIMITER + trailing, DELIMITER);

        assertEquals("Leading is wrong", LEADING, pair.leading());
        assertEquals("Trailing is wrong", trailing, pair.trailing());
    }


    @Test
    public void toString_output() throws Exception {
        String toString = new StringPair(LEADING, TRAILING).toString();

        assertTrue("toString() doesn't contain a leading", toString.contains(LEADING));
        assertTrue("toString() doesn't contain a trailing", toString.contains(TRAILING));
    }


    @Test
    public void equals_same() throws Exception {
        StringPair pair = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertEquals(pair, pair);
    }


    @Test
    public void equals_identical() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertEquals(pair1, pair2);
    }


    @Test
    public void notEquals_differentLeading() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(" " + LEADING + DELIMITER + TRAILING, DELIMITER);

        assertNotEquals(pair1, pair2);
    }


    @Test
    public void notEquals_differentTrailing() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(LEADING + DELIMITER + TRAILING + " ", DELIMITER);

        assertNotEquals(pair1, pair2);
    }


    @Test
    public void notEquals_null() throws Exception {
        StringPair pair = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertNotEquals(pair, null);
    }


    @Test
    public void hashCode_same() throws Exception {
        StringPair pair = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertEquals(pair.hashCode(), pair.hashCode());
    }


    @Test
    public void hashCode_identical() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);

        assertEquals(pair1.hashCode(), pair2.hashCode());
    }


    @Test
    public void hashCode_differentLeading() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(" " + LEADING + DELIMITER + TRAILING, DELIMITER);

        assertNotEquals(pair1.hashCode(), pair2.hashCode());
    }


    @Test
    public void hashCode_differentTrailing() throws Exception {
        StringPair pair1 = StringPair.create(LEADING + DELIMITER + TRAILING, DELIMITER);
        StringPair pair2 = StringPair.create(LEADING + DELIMITER + TRAILING + " ", DELIMITER);

        assertNotEquals(pair1.hashCode(), pair2.hashCode());
    }
}