package xyz.enhorse.commons;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         16.08.2016
 */
public class UriTest {

    @Test
    public void create_notNull() throws Exception {
        assertNotNull(new Uri(new URI("http://test.uri")));
    }


    @Test(expected = IllegalArgumentException.class)
    public void create_null() throws Exception {
        assertNull(new Uri(null));
    }


    @Test
    public void toURI_equals() throws Exception {
        String address = "http://test.uri";
        URI expected = new URI(address);
        assertEquals(expected, new Uri(expected).toURI());
    }


    @Test
    public void parse_valid_notNull() throws Exception {
        assertNotNull(Uri.parse("http://test.uri"));
    }


    @Test
    public void parse_valid_equals() throws Exception {
        String address = "http://test.uri";
        assertEquals(new URI(address), Uri.parse(address).toURI());
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_null() throws Exception {
        assertNotNull(Uri.parse(null));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_empty() throws Exception {
        assertNotNull(Uri.parse(""));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_onlySpaces() throws Exception {
        assertNotNull(Uri.parse("    "));
    }


    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_incorrect() throws Exception {
        assertNotNull(Uri.parse("incorrect url"));
    }


    @Test
    public void isValid_valid() throws Exception {
        assertTrue(Uri.isValid("http://test.uri"));
    }


    @Test
    public void isValid_invalid_null() throws Exception {
        assertFalse(Uri.isValid(null));
    }


    @Test
    public void isValid_invalid_empty() throws Exception {
        assertFalse(Uri.isValid(""));
    }


    @Test
    public void isValid_invalid_onlySpaces() throws Exception {
        assertFalse(Uri.isValid(""));
    }


    @Test
    public void isValid_invalid_incorrect() throws Exception {
        assertFalse(Uri.isValid("incorrect uri"));
    }


    @Test
    public void isValid_valid_localhost() throws Exception {
        assertTrue(Uri.isValid("localhost"));
    }


    @Test
    public void toString_outputEquals_URI_toString() throws Exception {
        String address = "http://test.uri";
        URI expected = new URI(address);
        assertEquals(expected.toString(), new Uri(expected).toString());
    }


    @Test
    public void equals_same() throws Exception {
        Uri uri = new Uri(new URI("http://test.uri"));
        assertEquals(uri, uri);
    }


    @Test
    public void equals_identical() throws Exception {
        String address = "http://test.uri";
        Uri uri1 = new Uri(new URI(address));
        Uri uri2 = new Uri(new URI(address));
        assertEquals(uri1, uri2);
    }


    @Test
    public void equals_different() throws Exception {
        Uri uri1 = new Uri(new URI("http://test1.uri"));
        Uri uri2 = new Uri(new URI("http://test2.uri"));
        assertNotEquals(uri1, uri2);
    }


    @Test
    public void equals_withNull() throws Exception {
        Uri nullUri = null;
        assertFalse(new Uri(new URI("http://test1.uri")).equals(nullUri));
    }


    @Test
    public void hashCode_same() throws Exception {
        Uri uri = new Uri(new URI("http://test.uri"));
        assertEquals(uri.hashCode(), uri.hashCode());
    }


    @Test
    public void hashCode_identical() throws Exception {
        String address = "http://test.uri";
        Uri uri1 = new Uri(new URI(address));
        Uri uri2 = new Uri(new URI(address));
        assertEquals(uri1.hashCode(), uri2.hashCode());
    }


    @Test
    public void hashCode_different() throws Exception {
        Uri uri1 = new Uri(new URI("http://test1.uri"));
        Uri uri2 = new Uri(new URI("http://test2.uri"));
        assertNotEquals(uri1.hashCode(), uri2.hashCode());
    }
}