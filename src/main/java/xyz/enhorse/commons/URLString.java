package xyz.enhorse.commons;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         17.08.2016
 */
public class URLString {

    private static final URLString EMPTY = new URLString("", "", Charset.defaultCharset());

    private final String plain;
    private final String encoded;
    private final Charset charset;


    private URLString(final String plain, final String encoded, Charset charset) {
        this.encoded = encoded;
        this.plain = plain;
        this.charset = charset;
    }


    public String plain() {
        return plain;
    }


    public String encoded() {
        return encoded;
    }


    public Charset charset() {
        return charset;
    }


    @Override
    public int hashCode() {
        int result = plain.hashCode();
        result = 31 * result + encoded.hashCode();
        result = 31 * result + charset.hashCode();
        return result;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        URLString urlString = (URLString) o;

        return plain.equals(urlString.plain)
                && encoded.equals(urlString.encoded)
                && charset.equals(urlString.charset);

    }


    @Override
    public String toString() {
        return plain() + "->[" + charset().name() + "]->" + encoded();
    }


    public static URLString encodeUTF(final String string) {
        return encode(string, StandardCharsets.UTF_8);
    }


    public static URLString decodeUTF(final String string) {
        return decode(string, StandardCharsets.UTF_8);
    }


    public static URLString encode(final String string, final Charset charset) {
        if (string == null) {
            return EMPTY;
        }

        Charset encoding = Validate.defaultIfNull(charset, Charset.defaultCharset());

        try {
            return new URLString(string, URLEncoder.encode(string, encoding.name()), encoding);
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("Can't encode the string \'%s\' with the charset %s: %s",
                    string, encoding.name(), ex.getMessage()));
        }
    }


    public static URLString decode(final String string, final Charset charset) {
        if (string == null) {
            return EMPTY;
        }

        Charset encoding = Validate.defaultIfNull(charset, Charset.defaultCharset());

        try {
            return new URLString(URLDecoder.decode(string, encoding.name()), string, encoding);
        } catch (Exception ex) {
            throw new IllegalArgumentException(String.format("Can't decode the string \'%s\' with the charset %s: %s",
                    string, encoding.name(), ex.getMessage()));
        }
    }
}
