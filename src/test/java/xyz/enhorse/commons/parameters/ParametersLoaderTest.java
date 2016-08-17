package xyz.enhorse.commons.parameters;

import org.junit.Test;
import xyz.enhorse.commons.StringPair;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        InputStream stream
                = new ByteArrayInputStream(generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR)
                .getBytes(StandardCharsets.UTF_8));

        ParametersLoader loader = new ParametersLoader(stream);
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
        InputStream stream
                = new ByteArrayInputStream(generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR)
                .getBytes(StandardCharsets.UTF_8));

        assertEquals(map.size() - 1, new ParametersLoader(stream).load().size());
    }


    @Test
    public void load_empty() throws Exception {
        Map<String, String> map = new HashMap<>();
        InputStream stream
                = new ByteArrayInputStream(generateParametersString(map, Parameters.PARAMETER_VALUE_SEPARATOR)
                .getBytes(StandardCharsets.UTF_8));
        assertEquals(0, new ParametersLoader(stream).load().size());
    }


    private static String generateParametersString(final Map<String, String> map, final char delimiter) {
        StringJoiner result = new StringJoiner(System.lineSeparator());

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.add(entry.getKey() + delimiter + entry.getValue());
        }

        return result.toString();
    }

}