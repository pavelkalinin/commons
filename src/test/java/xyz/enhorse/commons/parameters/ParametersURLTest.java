package xyz.enhorse.commons.parameters;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.ParametersURL.PARAMETERS_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURLTest {

    @Test
    public void load() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        parameters.put("key3", "value3");

        ParametersURL url = new ParametersURL(generateURL(parameters), UTF_8);

        for (Map.Entry<String, String> entry : url.load().entrySet()) {
            assertEquals(parameters.get(entry.getKey()), entry.getValue());
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nullURL() throws Exception {
        assertNull(new ParametersFile(null, UTF_8));
    }


    @Test
    public void create_nullCharset() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        parameters.put("key3", "value3");

        assertNotNull(new ParametersURL(generateURL(parameters), null));
    }


    @Test
    public void load_incorrectKey() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key 2", "value2");
        parameters.put("key3", "value3");

        ParametersURL url = new ParametersURL(generateURL(parameters), UTF_8);

        assertEquals(parameters.size() - 1, url.load().size());
    }


    @Test
    public void load_keyBetweenSpaces() throws Exception {
        String key = "key";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("  " + key + "  ", "value");

        ParametersURL url = new ParametersURL(generateURL(parameters), UTF_8);

        assertTrue(url.load().containsKey(key));
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";
        String query = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + PARAMETERS_SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;

        URL url = new URL("http://internet.org/?" + query);

        Map<String, String> parameters = new ParametersURL(url, UTF_8).load();

        assertEquals(value2, parameters.get(key));
    }


    @Test
    public void load_empty() throws Exception {
        ParametersURL url = new ParametersURL(generateURL(new HashMap<>()), UTF_8);

        assertEquals(0, url.load().size());
    }


    //TODO check encoding-decoding manipulations


    @Test
    public void toString_output() throws Exception {
        String query = "key" + Parameters.PARAMETER_VALUE_SEPARATOR + "value";

        String toString = new ParametersURL(new URL("http://internet.org/?" + query), UTF_8).toString();

        assertTrue("doesn't contains query", toString.contains(query));
        assertTrue("doesn't contains charset", toString.contains(UTF_8.name()));
    }


    private static URL generateURL(Map<String, Object> map) {
        final String query = generateQuery(map);
        try {
            return new URL("http://internet.org/?" + query);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Illegal query + \'" + query + '\'');
        }
    }


    private static String generateQuery(Map<String, Object> map) {
        StringJoiner joiner = new StringJoiner(String.valueOf(PARAMETERS_SEPARATOR));

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            joiner.add(entry.getKey() + Parameters.PARAMETER_VALUE_SEPARATOR + entry.getValue());
        }

        return joiner.toString();
    }
}