package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.URLString;
import xyz.enhorse.commons.Validate;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURL extends AbstractParametersLoader {

    public static final char PARAMETERS_SEPARATOR = '&';

    private final String query;
    private final Charset encoding;


    public ParametersURL(final URL url, final Charset charset) {
        query = Validate.notNull("URL", url).getQuery();
        encoding = Validate.defaultIfNull(charset, Charset.defaultCharset());
    }


    public ParametersURL(final URL url) {
        this(url, null);
    }


    @Override
    public Map<String, String> load() {
        Map<String, String> map = new HashMap<>();

        for (String parameter : query.split(String.valueOf(PARAMETERS_SEPARATOR))) {
            StringPair pair = super.processString(parameter, map);
            if (!StringPair.EMPTY.equals(pair)) {
                map.put(pair.leading(), URLString.decode(pair.trailing(), encoding).plain());
            }
        }

        return map;
    }


    @Override
    public String toString() {
        return query
                + " ["
                + encoding.name()
                + ']';
    }
}
