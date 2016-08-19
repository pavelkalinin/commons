package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.StringPair;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         19.08.2016
 */
public abstract class AbstractParametersLoader implements ParametersLoader {

    protected StringPair processString(final String string, final Map<String, String> map) {
        StringPair pair = StringPair.create(string, Parameters.PARAMETER_VALUE_SEPARATOR);

        String key = pair.leading().trim();

        if (isValidKey(key)) {
            if (map.containsKey(key)) {
                LOGGER.warn("Parameter \'" + key + "\' already defined. Its value will be replaced.");
            }
            return new StringPair(key, pair.trailing());
        }

        return StringPair.EMPTY;
    }


    private boolean isValidKey(final String key) {
        if (!Check.isUrlSafe(key)) {
            LOGGER.warn("Parameter \'" + key + "\' has an illegal name and will be ignored");
            return false;
        }

        return true;
    }
}
