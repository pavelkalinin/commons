package xyz.enhorse.commons;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class URLStringTest {

    @Test
    public void encode() throws Exception {
        Charset charset = Charset.forName("windows-1251");
        String plain = "строка for encoding";
        String encoded = "%F1%F2%F0%EE%EA%E0+for+encoding";

        URLString urlString = URLString.encode(plain, charset);

        assertEquals("Encoded string is wrong", encoded, urlString.encoded());
        assertEquals("Charset is wrong", charset, urlString.charset());
    }


    @Test
    public void decode() throws Exception {
        Charset charset = Charset.forName("windows-1251");
        String plain = "строка for encoding";
        String encoded = "%F1%F2%F0%EE%EA%E0+for+encoding";

        URLString urlString = URLString.decode(encoded, charset);

        assertEquals("Encoded string is wrong", plain, urlString.plain());
        assertEquals("Charset is wrong", charset, urlString.charset());
    }


    @Test
    public void encodeUTF() throws Exception {
        String plain = "строка for encoding";
        String encoded = "%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%B0+for+encoding";

        assertEquals(encoded, URLString.encodeUTF(plain).encoded());
    }


    @Test
    public void decodeUTF() throws Exception {
        String plain = "строка for encoding";
        String encoded = "%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%B0+for+encoding";

        assertEquals(plain, URLString.decodeUTF(encoded).plain());
    }


    @Test
    public void encode_plainIs_null() throws Exception {
        assertNotNull(URLString.encode(null, Charset.defaultCharset()));
    }


    @Test
    public void encode_plainIs_empty() throws Exception {
        assertEquals("", URLString.encode("", Charset.defaultCharset()).plain());
    }


    @Test
    public void encode_charsetIs_null() throws Exception {
        String plain = "string";
        assertEquals(plain, URLString.encode(plain, null).plain());
    }


    @Test
    public void decode_plainIs_null() throws Exception {
        assertNotNull(URLString.decode(null, Charset.defaultCharset()));
    }


    @Test
    public void decode_plainIs_empty() throws Exception {
        assertNotNull(URLString.decode("", Charset.defaultCharset()));
    }


    @Test
    public void decode_charsetIs_null() throws Exception {
        String string = "string";
        assertEquals(string, URLString.decode(string, null).plain());
    }


    @Test
    public void toString_output() throws Exception {
        Charset charset = Charset.forName("windows-1251");
        String string = "строка for encoding";
        String encoded = "%F1%F2%F0%EE%EA%E0+for+encoding";

        String toString = URLString.decode(encoded, charset).toString();
        assertTrue("Doesn't contains charset", toString.contains(charset.name()));
        assertTrue("Doesn't contains plain string", toString.contains(string));
        assertTrue("Doesn't contains encoded string", toString.contains(encoded));
    }


    @Test
    public void hashCode_same() throws Exception {
        URLString urlString = URLString.encode("string", StandardCharsets.US_ASCII);

        assertEquals(urlString.hashCode(), urlString.hashCode());
    }


    @Test
    public void hashCode_identical() throws Exception {
        URLString urlString1 = URLString.encode("string", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string", StandardCharsets.US_ASCII);

        assertEquals(urlString1.hashCode(), urlString2.hashCode());
    }


    @Test
    public void hashCode_differentPlain() throws Exception {
        URLString urlString1 = URLString.encode("string1", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string2", StandardCharsets.US_ASCII);

        assertNotEquals(urlString1.hashCode(), urlString2.hashCode());
    }


    @Test
    public void hashCode_differentCharsets() throws Exception {
        URLString urlString1 = URLString.encode("string", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string", StandardCharsets.ISO_8859_1);

        assertNotEquals(urlString1.hashCode(), urlString2.hashCode());
    }


    @Test
    public void equals_same() throws Exception {
        URLString urlString = URLString.encode("string", StandardCharsets.US_ASCII);

        assertEquals(urlString, urlString);
    }


    @Test
    public void equals_identical() throws Exception {
        URLString urlString1 = URLString.encode("string", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string", StandardCharsets.US_ASCII);

        assertEquals(urlString1, urlString2);
    }


    @Test
    public void equals_differentPlain() throws Exception {
        URLString urlString1 = URLString.encode("string1", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string2", StandardCharsets.US_ASCII);

        assertNotEquals(urlString1, urlString2);
    }


    @Test
    public void equals_differentCharsets() throws Exception {
        URLString urlString1 = URLString.encode("string", StandardCharsets.US_ASCII);
        URLString urlString2 = URLString.encode("string", StandardCharsets.ISO_8859_1);

        assertNotEquals(urlString1, urlString2);
    }


    @Test
    public void equals_null() throws Exception {
        URLString urlString1 = URLString.encode("string", StandardCharsets.US_ASCII);

        assertNotEquals(urlString1, null);
    }


    @Test
    public void encode_specialSymbols_unchanged() throws Exception {
        String plain = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._";

        URLString urlString = URLString.encode(plain, StandardCharsets.UTF_8);

        assertEquals(plain, urlString.encoded());
    }


    @Test
    public void encode_specialSymbols_changed() throws Exception {
        String plain = "~:/?#[]@!$&'()*+,;= ";
        String encoded = "%7E%3A%2F%3F%23%5B%5D%40%21%24%26%27%28%29*%2B%2C%3B%3D+";

        URLString urlString = URLString.encode(plain, StandardCharsets.UTF_8);

        assertEquals(encoded, urlString.encoded());
    }
}