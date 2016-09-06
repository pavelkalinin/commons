package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.collections.BasicFlexibleBox;
import xyz.enhorse.commons.parameters.BasicParameter;
import xyz.enhorse.commons.parameters.BasicParameters;
import xyz.enhorse.commons.parameters.Parameter;
import xyz.enhorse.commons.parameters.Parameters;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05.09.2016
 */
public class BasicSchema extends BasicFlexibleBox<Description<?>> implements Schema {

    public BasicSchema(final Map<String, Description<?>> content) {
        super(content);
    }


    public Parameters process(final Map<String, String> map) {
        Validate.notNull("map", map);
        Map<String, Description<?>> schema = toMap();
        Parameters parameters = new BasicParameters();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (schema.containsKey(key)) {
                Description<?> description = get(key);
                if (!description.isApplicable(value)) {
                    throw new IllegalArgumentException(String.format(
                            "The value \'%s\' of the parameter \"%s\" is not applicable to %s",
                            value, key, description));
                }

                Parameter<?> parameter = new BasicParameter<>(key, description.cast(value));
                parameters.put(parameter);
                schema.remove(key);
            }
        }

        for (Map.Entry<String, Description<?>> entry : schema.entrySet()) {
            String key = entry.getKey();
            Description description = entry.getValue();
            Parameter<?> parameter;

            if (description.defaultValue() == null) {
                if (description.canBeNull()) {
                    parameter = new BasicParameter<>(key, null);
                } else {
                    throw new IllegalArgumentException(String.format(
                            "The parameter \"%s\" with the description %s is required, but not defined",
                            key, description));
                }
            } else {
                parameter = new BasicParameter<>(entry.getKey(), description.defaultValue());
            }
            parameters.put(parameter);
        }

        return parameters;
    }
}
