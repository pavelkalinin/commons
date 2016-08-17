package xyz.enhorse.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class StringPairTest {

    @Test
    public void create() throws Exception {
        assertNotNull(new StringPair("key", "value"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_keyIs_null() throws Exception {
        assertNotNull(new StringPair(null, "value"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_keyIs_empty() throws Exception {
        assertNotNull(new StringPair("", "value"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_keyIs_onlySpaces() throws Exception {
        assertNotNull(new StringPair(" ", "value"));
    }


    @Test
    public void create_valueIs_null() throws Exception {
        assertNotNull(new StringPair("key", null));
    }


    @Test
    public void create_defaultValueIf_valueIs_null() throws Exception {
        assertNotNull(new StringPair("key", null).value());
    }


    @Test
    public void key() throws Exception {
        String key = "key";
        assertEquals(key, new StringPair(key, "value").key());
    }


    @Test
    public void value() throws Exception {
        String value = "value";
        assertEquals(value, new StringPair("key", value).value());
    }


    @Test
    public void parse_delimiterString() throws Exception {
        String key = "key";
        String value = "value";
        String delimiter = "==";
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key, pair.key());
        assertEquals("value is wrong", value, pair.value());
    }


    @Test
    public void parse_delimiterChar() throws Exception {
        String key = "key";
        String value = "value";
        char delimiter = '=';
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key, pair.key());
        assertEquals("value is wrong", value, pair.value());
    }


    @Test
    public void parse_emptyValue() throws Exception {
        String key = "key";
        String value = "";
        String delimiter = "==";
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key, pair.key());
        assertEquals("value is wrong", value, pair.value());
    }


    @Test
    public void parse_emptyKey() throws Exception {
        String key = "";
        String value = "value";
        String delimiter = "==";
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key + delimiter + value, pair.key());
        assertEquals("value is wrong", "", pair.value());
    }


    @Test
    public void parse_withoutDelimiter() throws Exception {
        String key = "key";
        String value = "value";
        String delimiter = "==";
        StringPair pair = StringPair.parse(key + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key + value, pair.key());
        assertEquals("value is wrong", "", pair.value());
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_stringIs_null() throws Exception {
        assertNull(StringPair.parse(null, "=="));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_stringIs_empty() throws Exception {
        assertNull(StringPair.parse("", "=="));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_stringIs_onlySpaces() throws Exception {
        assertNull(StringPair.parse("  ", "=="));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_delimiterIs_null() throws Exception {
        assertNull(StringPair.parse("key==value", null));
    }


    @Test
    public void parse_delimiterIs_empty() throws Exception {
        String key = "key";
        String value = "value";
        String delimiter = "";
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key + delimiter + value, pair.key());
        assertEquals("value is wrong", "", pair.value());
    }


    @Test
    public void parse_valueHas_delimiter() throws Exception {
        String key = "key";
        String value = "value with a delimiter";
        String delimiter = " ";
        StringPair pair = StringPair.parse(key + delimiter + value, delimiter);
        assertNotNull(pair);
        assertEquals("key is wrong", key, pair.key());
        assertEquals("value is wrong", value, pair.value());
    }
}