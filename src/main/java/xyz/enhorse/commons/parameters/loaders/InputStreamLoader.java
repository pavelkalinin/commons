package xyz.enhorse.commons.parameters.loaders;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.Validate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         22.08.2016
 */
public class InputStreamLoader implements Loader {

    private static final int BUFFER_SIZE = 1024;

    private final InputStream input;
    private final Charset charset;
    private final String splitter;


    public InputStreamLoader(final InputStream in, final Charset encoding, final String delimiter) {
        input = Validate.notNull("input stream", in);
        charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());
        splitter = Validate.notNullOrEmpty("delimiter", delimiter);
    }


    @Override
    public Map<String, String> load(final Companion companion) {
        String string = inputStreamToString(charset);

        if (!Check.isNullOrEmpty(string)) {
            StringLoader loader = new StringLoader(string, splitter);
            return loader.load(companion);
        }

        return new HashMap<>();
    }


    private String inputStreamToString(final Charset charset) {
        final int endOfStream = -1;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        try {
            while ((length = input.read(buffer)) != endOfStream) {
                out.write(buffer, 0, length);
            }

            return out.toString(charset.name());
        } catch (UnsupportedEncodingException ex) {
            String message = "Error encoding " + input + " with the charset " + charset.name();
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        } catch (IOException ex) {
            String message = "Error reading from " + input;
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        }
    }
}
