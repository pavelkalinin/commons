package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURLLoader implements ParametersLoader {

    public static final String PARAMETERS_SEPARATOR = "&";

    private final String query;


    public ParametersURLLoader(final URL url) {
        query = Validate.notNull("URL", url).getQuery();
    }


    @Override
    public Map<String, String> load(final Charset encoding) {
        Charset charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());

        ByteArrayInputStream stream = new ByteArrayInputStream(query.getBytes(encoding));
        ParametersInputStreamLoader loader = new ParametersInputStreamLoader(stream, PARAMETERS_SEPARATOR);
        return loader.load(charset);
    }


    @Override
    public String toString() {
        return query;
    }
}
