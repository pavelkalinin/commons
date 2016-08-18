package xyz.enhorse.commons.parameters;

import org.junit.Test;
import xyz.enhorse.commons.StringPair;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURLTest {

    @Test
    public void load() throws Exception {
        String query = "key1=value1&key2=value2";
        URL url = new URL("http://internet.org/?" + query);
        List<StringPair> parameters = new ParametersURL(url).load();

        assertEquals("value1", parameters.get(0).trailing());
        assertEquals("value2", parameters.get(1).trailing());
    }
}