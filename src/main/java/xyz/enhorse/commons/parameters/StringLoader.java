package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Check;
import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public class StringLoader implements ParametersLoader {

    private final Map<String, Object> map = new HashMap<>();
    private final String content;
    private final String splitter;


    public StringLoader(final String string, final String delimiter) {
        content = Validate.defaultIfNull(string, "");
        splitter = Validate.notNullOrEmpty("delimiter", delimiter);
    }


    @Override
    public Map<String, Object> load(final LoaderCompanion companion) {
        map.clear();

        LoaderCompanion processor = Validate.defaultIfNull(companion, new LoaderCompanion() {
        });

        StringTokenizer tokenizer = new StringTokenizer(content, splitter, false);
        while (tokenizer.hasMoreElements()) {
            processEntry(tokenizer.nextToken(), processor);
        }

        return new HashMap<>(map);
    }


    private void processEntry(final String string, final LoaderCompanion companion) {
        StringPair pair = StringPair.create(string, Parameters.PARAMETER_VALUE_SEPARATOR);

        String key = companion.preProcessKey(pair.leading());
        String value = companion.preProcessValue(pair.trailing());

        if (!Check.isNullOrEmpty(key)) {
            if (map.containsKey(key)) {
                LOGGER.warn("\'" + key + "\' already defined. The previous value will be replaced with a new one.");
            }

            map.put(companion.postProcessKey(key), companion.postProcessValue(value));
        }
    }


    @Override
    public String toString() {
        return "[" + content + "]:[" + splitter + ']';
    }
}
