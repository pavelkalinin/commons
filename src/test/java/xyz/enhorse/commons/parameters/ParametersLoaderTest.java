package xyz.enhorse.commons.parameters;

import org.junit.Test;
import xyz.enhorse.commons.StringPair;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class ParametersLoaderTest {

    @Test
    public void load() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String parameters = generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR);
        ParametersLoader loader = new ParametersLoader(new StringReader(parameters));
        for (StringPair pair : loader.load()) {
            assertEquals(map.get(pair.key()), pair.value());
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void load_illegal_null() throws Exception {
        assertNull(new ParametersLoader(null));
    }


    @Test
    public void load_withComment() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("//key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        String parameters = generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR);
        ParametersLoader loader = new ParametersLoader(new StringReader(parameters));

        assertEquals(map.size() - 1, loader.load().size());
    }


    @Test
    public void load_empty() throws Exception {
        Map<String, String> map = new HashMap<>();
        String parameters = generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR);
        ParametersLoader loader = new ParametersLoader(new StringReader(parameters));

        assertEquals(0, loader.load().size());
    }


    private static String generateParametersString(final Map<String, String> map, final char delimiter) {
        StringJoiner result = new StringJoiner(System.lineSeparator());

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.add(entry.getKey() + delimiter + entry.getValue());
        }

        return result.toString();
    }

}