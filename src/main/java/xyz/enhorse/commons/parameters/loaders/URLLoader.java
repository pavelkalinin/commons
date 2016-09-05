package xyz.enhorse.commons.parameters.loaders;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.URLString;
import xyz.enhorse.commons.Validate;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class URLLoader extends StringLoader {

    public static final String PARAMETERS_SEPARATOR = "&";

    private final Charset charset;


    public URLLoader(final URL url, final Charset encoding) {
        super(Validate.notNull("URL", url).getQuery(), PARAMETERS_SEPARATOR);
        charset = Validate.defaultIfNull(encoding, Charset.defaultCharset());
    }


    @Override
    public Map<String, String> load() {
        return load(new QueryLoader());
    }


    @Override
    public Map<String, String> load(final LoaderCompanion companion) {
        return super.load(companion);
    }


    @Override
    public String toString() {
        return super.toString() + ":[" + charset + ']';
    }

    private class QueryLoader implements LoaderCompanion {

        @Override
        public String preProcessKey(final String key) {
            if (!Check.isNullOrEmpty(key)) {
                if (Check.isUrlSafe(key)) {
                    return key;
                } else {
                    LOGGER.warn("\'" + key + "\' has an illegal name and will be ignored");
                }
            }
            return "";
        }


        @Override
        public String postProcessValue(final String value) {
            if (!Check.isNullOrEmpty(value)) {
                return URLString.decode(value, charset).plain();
            }
            return value;
        }
    }
}
