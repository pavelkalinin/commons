package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.collections.BasicFlexibleBox;
import xyz.enhorse.commons.parameters.BasicParameters;
import xyz.enhorse.commons.parameters.Parameter;
import xyz.enhorse.commons.parameters.Parameters;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05.09.2016
 */
public class BasicSchema<T extends Map> extends BasicFlexibleBox<Description<?>> implements Schema {

    public BasicSchema(final Class<T> map) {
        super(createInstance(map));
    }


    @Override
    public Parameters process(final Map<String, String> map) {
        Validate.notNull("map", map);

        Map<String, Description<?>> schema = toMap();
        Parameters parameters = new BasicParameters<>(schema.getClass());

        for (Map.Entry<String, Description<?>> element : schema.entrySet()) {
            String name = element.getKey();
            Description<?> description = element.getValue();

            Parameter<?> parameter = (map.containsKey(name))
                    ? checkParameter(name, map.get(name), description)
                    : computeParameter(name, description);

            parameters.put(parameter);
        }

        return parameters;
    }


    private Parameter<?> checkParameter(final String name, final String value, final Description<?> description) {
        if (description.isApplicable(value)) {
            return new Parameter<>(name, description.cast(value));
        }

        throw new IllegalArgumentException(String.format(
                "The value \'%s\' of the parameter \"%s\" is not applicable to %s",
                value, name, description));
    }


    private Parameter<?> computeParameter(final String name, final Description<?> description) {
        return (description.defaultValue() != null)
                ? new Parameter<>(name, description.defaultValue())
                : processNull(name, description);
    }


    private Parameter<?> processNull(final String name, final Description<?> description) {
        if (description.canBeNull()) {
            return new Parameter<>(name, null);
        }

        throw new IllegalArgumentException(String.format(
                "The parameter \"%s\" with the description %s is required, but not defined",
                name, description));
    }


    @SuppressWarnings("unchecked")
    private static <T extends Map> Map<String, Description<?>> createInstance(Class<T> map) {
        Validate.notNull("map class", map);

        try {
            return (Map<String, Description<?>>) map.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Couldn't create an instance of " + map.toString(), ex);
        }
    }
}
