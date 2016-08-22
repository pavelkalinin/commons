package xyz.enhorse.commons.parameters;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;
import static xyz.enhorse.commons.parameters.ParametersURLLoader.PARAMETERS_SEPARATOR;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURLLoaderTest {

    @Test
    public void load() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key2", "value2");
        parameters.put("key3", "value3");

        ParametersURLLoader url = new ParametersURLLoader(generateURL(parameters));

        for (Map.Entry<String, String> entry : url.load(UTF_8).entrySet()) {
            assertEquals(parameters.get(entry.getKey()), entry.getValue());
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_illegal_nullURL() throws Exception {
        assertNull(new ParametersFileLoader(null));
    }


    @Test
    public void load_incorrectKey() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key1", "value1");
        parameters.put("key 2", "value2");
        parameters.put("key3", "value3");

        ParametersURLLoader url = new ParametersURLLoader(generateURL(parameters));

        assertEquals(parameters.size() - 1, url.load(UTF_8).size());
    }


    @Test
    public void load_keyBetweenSpaces() throws Exception {
        String key = "key";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("  " + key + "  ", "value");

        ParametersURLLoader url = new ParametersURLLoader(generateURL(parameters));

        assertTrue(url.load(UTF_8).containsKey(key));
    }


    @Test
    public void load_duplicateKeys() throws Exception {
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";
        String query = key + Parameters.PARAMETER_VALUE_SEPARATOR + value1 + PARAMETERS_SEPARATOR
                + key + Parameters.PARAMETER_VALUE_SEPARATOR + value2;

        URL url = new URL("http://internet.org/?" + query);

        Map<String, String> parameters = new ParametersURLLoader(url).load(UTF_8);

        assertEquals(value2, parameters.get(key));
    }


    @Test
    public void load_empty() throws Exception {
        ParametersURLLoader url = new ParametersURLLoader(generateURL(new HashMap<>()));

        assertEquals(0, url.load(UTF_8).size());
    }


    //TODO check encoding-decoding manipulations


    @Test
    public void toString_output() throws Exception {
        String query = "key" + Parameters.PARAMETER_VALUE_SEPARATOR + "value";

        String toString = new ParametersURLLoader(new URL("http://internet.org/?" + query)).toString();

        assertTrue("doesn't contains query", toString.contains(query));
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