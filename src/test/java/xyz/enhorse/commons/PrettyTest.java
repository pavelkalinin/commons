package xyz.enhorse.commons;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static xyz.enhorse.commons.Pretty.GROUP_END;
import static xyz.enhorse.commons.Pretty.GROUP_START;

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
    public void format_nullObject() throws Exception {
        Object object = null;
        assertEquals("null", Pretty.format(object));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void format_nullMap() throws Exception {
        Map map = null;
        assertEquals(GROUP_START + GROUP_END, Pretty.format(map));
    }


    @Test
    public void format_map_empty() throws Exception {
        Map<Object, Object> map = new HashMap<>();
        assertEquals(GROUP_START + GROUP_END, Pretty.format(map));
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


    @Test
    public void format_thrownThrowable() throws Exception {
        try {
            throw new IllegalArgumentException("test exception");
        } catch (Throwable ex) {
            String actual = Pretty.format(ex);
            String expected = "java.lang.IllegalArgumentException: test exception";
            assertTrue("starts with thrown exception", actual.startsWith(expected));
            assertTrue("has more than one element at the stacktrace", actual.length() > expected.length());
        }
    }


    @Test
    public void format_nullThrowable() throws Exception {
        Throwable throwable = null;
        assertEquals(GROUP_START + GROUP_END, Pretty.format(throwable));
    }
}