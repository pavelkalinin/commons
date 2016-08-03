package xyz.enhorse.commons;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.08.2016
 */
public class PrettyTest {

    @Test
    public void constructor_DefaultIsExist() throws Exception {
        Constructor<Pretty> constructor = Pretty.class.getDeclaredConstructor();
        assertNotNull(constructor);
    }


    @Test(expected = IllegalAccessException.class)
    public void constructor_DefaultIsPrivate() throws Exception {
        Constructor<Validate> constructor = Validate.class.getDeclaredConstructor();
        assertNull(constructor.newInstance());
    }


    @Test
    public void format_emptyString() throws Exception {
        String string = "";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_string_onlySpaces() throws Exception {
        String string = "   ";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_string() throws Exception {
        String string = "test";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_string_withSpaces() throws Exception {
        String string = "test with spaces";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_string_withQuotes() throws Exception {
        String string = "\"test with spaces\"";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_null() throws Exception {
        assertEquals("[]", Pretty.format(null));
    }


    @Test
    public void format_map_empty() throws Exception {
        Map<Object, Object> map = new HashMap<>();
        assertEquals("[]", Pretty.format(map));
    }


    @Test
    public void format_map() throws Exception {
        Map<Object, Object> map = new LinkedHashMap<>();
        Date date = new Date();
        map.put("integer", 1);
        map.put("string", "test");
        map.put("boolean", true);
        map.put("date", date);
        assertEquals("[integer=1; string=\"test\"; boolean=true; date=" + date.toString() + "]", Pretty.format(map));
    }
}