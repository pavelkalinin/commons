package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.URLString;
import xyz.enhorse.commons.Validate;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class ParametersURL implements ParametersLoader {

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
    public List<StringPair> load() {
        List<StringPair> list = new ArrayList<>();

        for (String parameter : query.split(String.valueOf(PARAMETERS_SEPARATOR))) {
            StringPair pair = StringPair.parse(parameter, Parameters.PARAMETER_VALUE_SEPARATOR);
            list.add(new StringPair(pair.key(), URLString.decode(pair.value(), encoding).plain()));
        }

        return list;
    }


    @Override
    public String toString() {
        return query
                + " ["
                + encoding.name()
                + ']';
    }
}
