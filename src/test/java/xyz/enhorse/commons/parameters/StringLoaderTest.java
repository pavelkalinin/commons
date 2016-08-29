package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.Parameters.PARAMETER_VALUE_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.08.2016
 */
public class StringLoaderTest {

    private static final String SEPARATOR = "-";

    private static Level LEVEL;


    @Test
    public void create() throws Exception {
        String string = "key" + PARAMETER_VALUE_SEPARATOR + "value";
        assertNotNull(new StringLoader(string, SEPARATOR));
    }


    @Test
    public void create_nullString() throws Exception {
        assertNotNull(new StringLoader(null, SEPARATOR));
    }


    @Test
    public void create_emptyString() throws Exception {
        assertNotNull(new StringLoader("", SEPARATOR));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_nullDelimiter() throws Exception {
        String string = "key" + PARAMETER_VALUE_SEPARATOR + "value";
        assertNotNull(new StringLoader(string, null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_emptyDelimiter() throws Exception {
        String string = "key" + PARAMETER_VALUE_SEPARATOR + "value";
        assertNotNull(new StringLoader(string, ""));
    }


    @Test
    public void toString_output() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
        }};

        String string = mapToString(map);
        String toString = new StringLoader(string, SEPARATOR).toString();

        assertTrue("doesn't contain input string", toString.contains(string));
        assertTrue("doesn't contain separator", toString.contains(SEPARATOR));
    }


    @Test
    public void load() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";

        Map<String, String> expected = new HashMap<String, String>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(expected), SEPARATOR).load();

        for (Map.Entry<String, String> entry : expected.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertTrue("doesn't contain the key \'" + key + "\'", actual.containsKey(key));
            assertEquals("incorrect value of the key \'" + key + "\'", value, actual.get(key));
        }
    }


    @Test
    public void load_usingSameLoaderTwice() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";

        Map<String, String> expected = new HashMap<String, String>() {{
            put(key1, value1);
            put(key2, value2);
            put(key3, value3);
        }};

        ParametersLoader loader = new StringLoader(mapToString(expected), SEPARATOR);
        Map<String, Object> actual = loader.load();

        for (Map.Entry<String, String> entry : expected.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertTrue("doesn't contain the key \'" + key + "\'", actual.containsKey(key));
            assertEquals("incorrect value of the key \'" + key + "\'", value, actual.get(key));
        }

        actual = loader.load();
        assertEquals("size changed", expected.size(), actual.size());
    }


    @Test
    public void load_resultIsImmutable() throws Exception {
        String key1 = "key1";
        String value1 = "value1";

        Map<String, String> expected = new HashMap<String, String>() {{
            put(key1, value1);
        }};

        ParametersLoader loader = new StringLoader(mapToString(expected), SEPARATOR);
        Map<String, Object> actual = loader.load();

        String key = "key";
        String value = "value";
        actual.put(key, value);

        actual = loader.load();
        assertFalse(actual.containsKey(key));
    }



    @Test
    public void load_empty() throws Exception {
        Map<String, Object> actual = new StringLoader("", SEPARATOR).load();

        assertTrue(actual.size() == 0);
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key1";
        String value1 = "value1";
        String value2 = "value2";

        String string = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;

        Map<String, Object> actual = new StringLoader(string, SEPARATOR).load();

        assertTrue("incorrect size", actual.size() == 1);
        assertEquals("incorrect value", value2, actual.get(key));
    }


    @Test
    public void load_duplicateValues() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key1, value);
            put(key2, value);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(map), SEPARATOR).load();

        assertTrue("incorrect size", actual.size() == map.size());
        assertEquals("incorrect value", actual.get(key1), actual.get(key2));
    }


    @Test
    public void load_unicodeKey() throws Exception {
        String key = "ключ";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(map), SEPARATOR).load();

        assertTrue(actual.containsKey(key));
    }


    @Test
    public void load_keyWithSpaces() throws Exception {
        String key = " k e y ";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(map), SEPARATOR).load();

        assertTrue(actual.containsKey(key));
    }


    @Test
    public void load_unicodeValue() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(map), SEPARATOR).load();

        assertEquals(map.get(key), actual.get(key));
    }


    @Test
    public void load_valueWithSpace() throws Exception {
        String key = "key";
        String value = " v a l u e ";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        Map<String, Object> actual = new StringLoader(mapToString(map), SEPARATOR).load();

        assertEquals(map.get(key), actual.get(key));
    }


    @Test
    public void load_value_integer() throws Exception {
        String key = "key";
        int value = 40;

        Map<String, Object> actual = new StringLoader(key + PARAMETER_VALUE_SEPARATOR + value, SEPARATOR).load();

        assertEquals(value, actual.get(key));
    }


    @Test
    public void load_value_boolean() throws Exception {
        String key = "key";

        Map<String, Object> actual = new StringLoader(key + PARAMETER_VALUE_SEPARATOR + Boolean.TRUE, SEPARATOR).load();

        assertEquals(Boolean.TRUE, actual.get(key));
    }


    private static String mapToString(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner(String.valueOf(SEPARATOR));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            joiner.add(entry.getKey() + PARAMETER_VALUE_SEPARATOR + entry.getValue());
        }

        return joiner.toString();
    }


    @BeforeClass
    public static void setUp() throws Exception {
        LEVEL = ParametersLoader.LOGGER.getLevel();
        ParametersLoader.LOGGER.setLevel(Level.FATAL);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        ParametersLoader.LOGGER.setLevel(LEVEL);
    }

}