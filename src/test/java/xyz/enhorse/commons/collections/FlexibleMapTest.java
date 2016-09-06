package xyz.enhorse.commons.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public class FlexibleMapTest {

    private final static String KEY = "key";
    private final static String VALUE = "value";


    @Test
    public void put() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertEquals(VALUE, map.get(KEY));
    }


    @Test
    public void remove() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .remove(KEY);

        assertEquals(null, map.get(KEY));
    }


    @Test
    public void replace() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .replace(KEY, VALUE + VALUE);

        assertEquals(VALUE + VALUE, map.get(KEY));
    }


    @Test
    public void merge() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY + KEY, VALUE)
                .put(KEY + KEY + KEY, VALUE);
        FlexibleMap<String, String> merge = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        map.merge(merge);

        assertTrue(map.containsKey(KEY));
        assertEquals(3, map.size());
    }


    @Test
    public void intersect() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .put(KEY + KEY, VALUE)
                .put(KEY + KEY + KEY, VALUE);
        FlexibleMap<String, String> intersection = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .put(KEY + KEY + KEY + KEY, VALUE);

        map.intersect(intersection);

        assertTrue(map.containsKey(KEY));
        assertEquals(1, map.size());
    }


    @Test
    public void subtract() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .put(KEY + KEY, VALUE)
                .put(KEY + KEY + KEY, VALUE);
        FlexibleMap<String, String> subtract = new FlexibleLinkedHashMap<String, String>()
                .put(KEY + KEY, VALUE)
                .put(KEY + KEY + KEY, VALUE);

        map.subtract(subtract);

        assertTrue(map.containsKey(KEY));
        assertEquals(1, map.size());
    }


    @Test
    public void clear() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE)
                .clear();

        assertEquals(0, map.size());
    }


    @Test
    public void get() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertEquals(VALUE, map.get(KEY));
    }


    @Test
    public void isEmpty() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertFalse(map.isEmpty());
        assertTrue(map.clear().isEmpty());
    }


    @Test
    public void containsKey() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertTrue(map.containsKey(KEY));
        assertFalse(map.remove(KEY).containsKey(KEY));
    }


    @Test
    public void containsValue() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertTrue(map.containsValue(VALUE));
        assertFalse(map.remove(KEY).containsKey(VALUE));
    }


    @Test
    public void size() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);

        assertEquals(1, map.size());
    }


    @Test
    public void toMap() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);
        Map<String, String> actual = map.toMap();
        Map<String, String> expected = new HashMap<>();
        expected.put(KEY, VALUE);

        assertEquals(expected, actual);
    }


    @Test
    public void toString_output() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);
        String toString = map.toString();

        assertTrue("doesn't contain key", toString.contains(KEY));
        assertTrue("doesn't contain value", toString.contains(VALUE));
    }


    @Test
    public void equals_same() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);
        assertEquals(map, map);
    }


    @Test
    public void hashCode_same() throws Exception {
        FlexibleMap<String, String> map = new FlexibleLinkedHashMap<String, String>()
                .put(KEY, VALUE);
        assertEquals(map.hashCode(), map.hashCode());
    }
}