package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         22.08.2016
 */
public class ParametersInputStreamLoader implements ParametersLoader {

    private static final int BUFFER_SIZE = 1024;

    protected final Map<String, String> map = new HashMap<>();

    private final InputStream input;
    private final String splitter;


    ParametersInputStreamLoader(final InputStream in, final String delimiter) {
        input = Validate.notNull("input stream", in);
        splitter = Validate.defaultIfNullOrEmpty("delimiter", delimiter);
    }


    @Override
    public Map<String, String> load(final Charset encoding) {
        Charset charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());

        try {
            String string = inputStreamToString(charset);

            StringTokenizer tokenizer = new StringTokenizer(string, splitter, false);

            while (tokenizer.hasMoreElements()) {
                processString(tokenizer.nextToken());
            }
        } catch (UnsupportedEncodingException ex) {
            String message = "Error encoding " + input + " with the charset " + charset.name();
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        } catch (IOException ex) {
            String message = "Error reading from " + input;
            LOGGER.error(message, ex);
            throw new IllegalStateException(message, ex);
        }

        return map;
    }


    private void processString(final String string) {
        StringPair pair = StringPair.create(string, Parameters.PARAMETER_VALUE_SEPARATOR);

        String key = trimQuotes(pair.leading().trim());

        if (isValidKey(key)) {
            if (map.containsKey(key)) {
                LOGGER.warn("Parameter \'" + key + "\' already defined. Its value will be replaced.");
            }
            map.put(key, pair.trailing().trim());
        }
    }


    private String trimQuotes(final String string) {
        String result = string;
        if (string.charAt(0) == '\"') {
            result = string.substring(1);
            int tail = string.length() - 1;
            if (string.charAt(tail) == '\"') {
                result = result.substring(0, tail);
            }
        }

        return result;
    }


    private boolean isValidKey(final String key) {
        if (!Check.isUrlSafe(key)) {
            LOGGER.warn("Parameter \'" + key + "\' has an illegal name and will be ignored");
            return false;
        }

        return true;
    }


    private String inputStreamToString(final Charset charset) throws IOException {
        final int endOfStream = -1;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        while ((length = input.read(buffer)) != endOfStream) {
            out.write(buffer, 0, length);
        }

        return out.toString(charset.name());
    }
}
