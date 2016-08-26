package xyz.enhorse.commons.parameters;

import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.enhorse.commons.URLString;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.URLLoader.PARAMETERS_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class URLLoaderTest {

    private static Level LEVEL;


    @Test(expected = IllegalArgumentException.class)
    public void create_nullURL() throws Exception {
        new URLLoader(null, UTF_8);
    }


    @Test
    public void create() throws Exception {
        new URLLoader(generateURL(new HashMap<>()), UTF_8);
    }


    @Test
    public void create_nullCharset() throws Exception {
        assertNotNull(new URLLoader(generateURL(new HashMap<>()), UTF_8));
    }


    @Test
    public void load() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        URLLoader url = new URLLoader(generateURL(map), UTF_8);
        Map<String, String> actual = url.load();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            assertTrue("doesn't contain the key \'" + key + "\'", actual.containsKey(key));
            assertEquals("incorrect value of the key \'" + key + "\'", value, actual.get(key));
        }
    }


    @Test
    public void load_incorrectKey() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key 2", "value2");
        map.put("key3", "value3");

        URLLoader url = new URLLoader(generateURL(map), UTF_8);

        assertEquals(map.size() - 1, url.load().size());
    }


    @Test
    public void load_keyTrimmed() throws Exception {
        String key = "key";

        Map<String, String> map = new HashMap<>();
        map.put("  " + key + "  ", "value");

        URLLoader url = new URLLoader(generateURL(map), UTF_8);

        assertFalse(url.load().containsKey(key));
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";
        String query = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + PARAMETERS_SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;

        URL url = new URL("http://internet.org/?" + query);

        Map<String, String> actual = new URLLoader(url, UTF_8).load();

        assertEquals(value2, actual.get(key));
    }


    @Test
    public void load_emptyValue() throws Exception {
        String key = "key";
        String value = "";
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        map.put(key + key, "value");

        URLLoader url = new URLLoader(generateURL(map), UTF_8);

        assertTrue(url.load().get(key).isEmpty());
    }


    @Test
    public void load_decodeEncodedValue() throws Exception {
        String key = "key";
        String value = "значение";

        Map<String, String> map = new HashMap<>();
        map.put(key, URLString.encode(value, UTF_8).encoded());

        Map<String, String> actual = new URLLoader(generateURL(map), UTF_8).load();

        assertNotEquals("value doesn't encoded", value, map.get(key));
        assertEquals("parameter doesn't decoded", value, actual.get(key));
    }


    @Test
    public void load_empty() throws Exception {
        URLLoader url = new URLLoader(generateURL(new HashMap<>()), UTF_8);

        assertTrue(url.load().isEmpty());
    }


    @Test
    public void toString_output() throws Exception {
        String query = "key" + Parameters.PARAMETER_VALUE_SEPARATOR + "value";

        String toString = new URLLoader(new URL("http://internet.org/?" + query), UTF_8).toString();

        assertTrue("doesn't contain query", toString.contains(query));
        assertTrue("doesn't contain charset", toString.contains(UTF_8.name()));
        assertTrue("doesn't contain separator", toString.contains(URLLoader.PARAMETERS_SEPARATOR));
    }


    private static URL generateURL(Map<String, String> map) {
        final String query = generateQuery(map);
        try {
            return new URL("http://internet.org/?" + query);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Illegal query + \'" + query + '\'');
        }
    }


    private static String generateQuery(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner(String.valueOf(PARAMETERS_SEPARATOR));

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