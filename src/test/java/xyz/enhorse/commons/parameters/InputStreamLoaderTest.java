package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         24.08.2016
 */
public class InputStreamLoaderTest {

    private static final String SEPARATOR = "-";

    private static Level LEVEL;


    @Test(expected = IllegalArgumentException.class)
    public void create_nullInputStream() throws Exception {
        new InputStreamLoader(null, UTF_8, SEPARATOR);
    }


    @Test
    public void create_nullCharset() throws Exception {
        assertNotNull(new InputStreamLoader(
                new ByteArrayInputStream(mapToString(new HashMap<>()).getBytes()), null, SEPARATOR));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_nullSeparator() throws Exception {
        new InputStreamLoader(new ByteArrayInputStream(mapToString(new HashMap<>()).getBytes()), UTF_8, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_emptySeparator() throws Exception {
        new InputStreamLoader(new ByteArrayInputStream(mapToString(new HashMap<>()).getBytes()), UTF_8, "");
    }


    @Test
    public void create() throws Exception {
        InputStream stream = new ByteArrayInputStream(mapToString(new HashMap<>()).getBytes(UTF_8));
        assertNotNull(new InputStreamLoader(stream, UTF_8, SEPARATOR));
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

        String string = mapToString(expected);
        InputStream stream = new ByteArrayInputStream(string.getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);
        Map<String, String> actual = loader.load();

        for (Map.Entry<String, String> entry : expected.entrySet()) {
            String key = entry.getKey();
            assertTrue("doesn't contain the key \'" + key + "\'", actual.containsKey(key));
            assertEquals("incorrect value of the key \'" + key + "\'", entry.getValue(), actual.get(key));
        }
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";

        String string = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;
        InputStream stream = new ByteArrayInputStream(string.getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        Map<String, String> actual = loader.load();

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

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);
        Map<String, String> actual = loader.load();

        assertTrue("incorrect size", actual.size() == 2);
        assertEquals("incorrect value", actual.get(key1), actual.get(key2));
    }


    @Test
    public void load_unicodeKey() throws Exception {
        String key = "ключ";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        assertTrue(loader.load().containsKey(key));
    }


    @Test
    public void load_keyWithSpaces() throws Exception {
        String key = " k e y ";
        String value = "value";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        assertTrue(loader.load().containsKey(key));
    }


    @Test
    public void load_unicodeValue() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        assertEquals(map.get(key), loader.load().get(key));
    }


    @Test
    public void load_valueWithSpace() throws Exception {
        String key = "key";
        String value = " v a l u e ";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        assertEquals(map.get(key), loader.load().get(key));
    }


    @Test
    public void load_empty() throws Exception {
        InputStream stream = new ByteArrayInputStream("".getBytes(UTF_8));
        ParametersLoader loader = new InputStreamLoader(stream, UTF_8, SEPARATOR);

        assertTrue(loader.load().isEmpty());
    }


    @Test
    public void load_differentCharsets() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));

        Map<String, String> utf = new InputStreamLoader(stream, UTF_8, SEPARATOR).load();
        Map<String, String> ascii = new InputStreamLoader(stream, US_ASCII, SEPARATOR).load();

        assertNotEquals("charset wasn't applied", utf.get(key), ascii.get(key));
    }


    @Test
    public void load_identicalCharsets() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<String, String>() {{
            put(key, value);
        }};

        InputStream stream = new ByteArrayInputStream(mapToString(map).getBytes(UTF_8));

        Map<String, String> utf1 = new InputStreamLoader(stream, UTF_8, SEPARATOR).load();
        Map<String, String> utf2 = new InputStreamLoader(stream, UTF_8, SEPARATOR).load();

        assertNotEquals("charset wasn't applied", utf1.get(key), utf2.get(key));
    }


    private static String mapToString(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner(String.valueOf(SEPARATOR));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            joiner.add(entry.getKey() + Parameters.PARAMETER_VALUE_SEPARATOR + entry.getValue());
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