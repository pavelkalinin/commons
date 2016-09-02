package xyz.enhorse.commons;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static xyz.enhorse.commons.Pretty.GROUP_END;
import static xyz.enhorse.commons.Pretty.GROUP_START;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.08.2016
 */
public class PrettyTest {

    @Test
    public void format_String() throws Exception {
        String string = "test";

        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_String_null() throws Exception {
        String string = null;

        assertEquals("null", Pretty.format(string));
    }


    @Test
    public void format_String_empty() throws Exception {
        String string = "";

        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_String_onlySpaces() throws Exception {
        String string = "   ";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_String_withSpaces() throws Exception {
        String string = "test with spaces";
        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_String_withQuotes() throws Exception {
        String string = "\"test with spaces\"";

        assertEquals('\"' + string + '\"', Pretty.format(string));
    }


    @Test
    public void format_Object_null() throws Exception {
        Object object = null;

        assertEquals("null", Pretty.format(object));
    }


    @Test
    public void format_Map() throws Exception {
        Map<Object, Object> map = new LinkedHashMap<>();
        Date date = new Date();
        map.put("integer", 1);
        map.put("string", "test");
        map.put("boolean", true);
        map.put("date", date);

        assertEquals(GROUP_START + "integer=1; string=\"test\"; boolean=true; date=" + date.toString() + GROUP_END,
                Pretty.format(map));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void format_Map_null() throws Exception {
        Map map = null;

        assertEquals(GROUP_START + GROUP_END, Pretty.format(map));
    }


    @Test
    public void format_Map_empty() throws Exception {
        Map<Object, Object> map = new HashMap<>();

        assertEquals(GROUP_START + GROUP_END, Pretty.format(map));
    }


    @Test
    public void format_Throwable() throws Exception {
        try {
            throw new IllegalArgumentException("test exception");
        } catch (Throwable ex) {
            String actual = Pretty.format(ex);
            String expected = "java.lang.IllegalArgumentException: test exception";

            assertTrue("starts with thrown exception", actual.startsWith(expected));
            assertTrue("has more than one element in the stacktrace", actual.length() > expected.length());
        }
    }


    @Test
    public void format_Throwable_null() throws Exception {
        Throwable throwable = null;

        assertEquals(GROUP_START + GROUP_END, Pretty.format(throwable));
    }


    @Test
    public void format_Iterable() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("string1");
        strings.add("string2");

        assertEquals("[\"string1\"; \"string2\"]", Pretty.format(strings));
    }


    @Test
    public void format_Iterable_null() throws Exception {
        List<String> strings = null;

        assertEquals("", Pretty.format(strings));
    }


    @Test
    public void format_Iterable_empty() throws Exception {
        List<String> strings = new ArrayList<>();

        assertEquals(GROUP_START + GROUP_END, Pretty.format(strings));
    }
}