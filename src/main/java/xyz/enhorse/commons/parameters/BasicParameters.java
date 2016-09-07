package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.collections.BasicFlexibleBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         27.04.2016
 */
public class BasicParameters<T extends Map> extends BasicFlexibleBox<ParameterValue<?>> implements Parameters {

    public BasicParameters(final Class<T> map) {
        super(createInstance(map));
    }


    @Override
    public List<Parameter<?>> list() {
        List<Parameter<?>> list = new ArrayList<>();

        while (iterator().hasNext()) {
            String name = iterator().next();
            list.add(new Parameter<>(name, get(name)));
        }

        return list;
    }


    @SuppressWarnings("unchecked")
    private static <T extends Map> Map<String, ParameterValue<?>> createInstance(Class<T> map) {
        Validate.notNull("map class", map);

        try {
            return (Map<String, ParameterValue<?>>) map.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Couldn't create an instance of " + map.toString(), ex);
        }
    }
}
