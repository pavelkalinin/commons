package xyz.enhorse.commons;

import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class URLStringTest {

    @Test
    public void encode() throws Exception {
        Charset charset = Charset.forName("windows-1251");
        String string = "строка for encoding";
        String encoded = "%F1%F2%F0%EE%EA%E0+for+encoding";

        URLString urlString = URLString.encode(string, charset);
        assertEquals("Encoded string is wrong", encoded, urlString.encoded());
        assertEquals("Charset is wrong", charset, urlString.charset());
    }


    @Test
    public void decode() throws Exception {
        Charset charset = Charset.forName("windows-1251");
        String string = "строка for encoding";
        String encoded = "%F1%F2%F0%EE%EA%E0+for+encoding";

        URLString urlString = URLString.decode(encoded, charset);
        assertEquals("Encoded string is wrong", string, urlString.plain());
        assertEquals("Charset is wrong", charset, urlString.charset());
    }


    @Test
    public void encodeUTF() throws Exception {
        String string = "строка for encoding";
        String encoded = "%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%B0+for+encoding";

        assertEquals(encoded, URLString.encodeUTF(string).encoded());
    }


    @Test
    public void decodeUTF() throws Exception {
        String string = "строка for encoding";
        String encoded = "%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%B0+for+encoding";

        assertEquals(string, URLString.decodeUTF(encoded).plain());
    }


    @Test
    public void encode_stringIs_null() throws Exception {
        assertNotNull(URLString.encode(null, Charset.defaultCharset()));
    }


    @Test
    public void encode_stringIs_empty() throws Exception {
        assertEquals("", URLString.encode("", Charset.defaultCharset()).plain());
    }


    @Test
    public void encode_charsetIs_null() throws Exception {
        String string = "string";
        assertEquals(string, URLString.encode(string, null).plain());
    }


    @Test
    public void decode_stringIs_null() throws Exception {
        assertNotNull(URLString.decode(null, Charset.defaultCharset()));
    }


    @Test
    public void decode_stringIs_empty() throws Exception {
        assertNotNull(URLString.decode("", Charset.defaultCharset()));
    }


    @Test
    public void decode_charsetIs_null() throws Exception {
        String string = "string";
        assertEquals(string, URLString.decode(string, null).plain());
    }
}