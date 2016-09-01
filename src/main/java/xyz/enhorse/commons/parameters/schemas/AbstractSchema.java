package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.parameters.Parameter;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class AbstractSchema<T extends Map<String, Description>> implements Schema {

    private final T content;


    @SuppressWarnings("unchecked")
    AbstractSchema(T map) {
        content = Validate.notNull("map", map);
    }


    public AbstractSchema put(final String name, final Description description) {
        content.put(Validate.notNullOrEmpty("name", name), Validate.notNull("description", description));
        return this;
    }


    public AbstractSchema remove(final String name) {
        content.remove(name);
        return this;
    }


    @Override
    public Schema clear() {
        content.clear();
        return this;
    }


    public boolean validate(final Parameter parameter) {
        if (parameter == null) {
            return false;
        }

        String name = parameter.name();
        return (content.containsKey(name)) && (content.get(name).isApplicable(parameter.value()));
    }
}
