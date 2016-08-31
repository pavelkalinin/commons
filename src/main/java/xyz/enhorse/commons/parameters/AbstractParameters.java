package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Pretty;
import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.errors.AbsentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         23.06.2016.
 */
public abstract class AbstractParameters<T extends Map> implements Parameters {

    private final Map<String, Parameter> content;


    public AbstractParameters(Class<T> map) {
        content = createInstance(map);
    }


    @Override
    public Parameters put(final Parameter parameter) {
        Validate.notNull("parameter", parameter);
        content.put(parameter.name(), parameter);
        return this;
    }


    @Override
    public Parameters remove(final String parameter) {
        content.remove(parameter);
        return this;
    }


    @Override
    public Parameters clear() {
        content.clear();
        return this;
    }


    @Override
    public Parameter get(final String name) {
        if (!isExists(name)) {
            throw new AbsentException("Parameter", name);
        }

        return content.get(name);
    }


    @Override
    public boolean isExists(final String name) {
        return content.containsKey(name);
    }


    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }


    @Override
    public Parameters merge(final Collection<Parameter> parameters) {
        parameters.forEach(this::put);
        return this;
    }


    @Override
    public int size() {
        return content.size();
    }


    @Override
    public List<Parameter> list() {
        return new ArrayList<>(content.values());
    }


    @Override
    public int hashCode() {
        return content.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractParameters other = (AbstractParameters) o;

        return content.equals(other.content);
    }


    @Override
    public String toString() {
        return Pretty.format(content);
    }


    @SuppressWarnings("unchecked")
    private static <T extends Map> Map<String, Parameter> createInstance(Class<T> map) {
        Validate.notNull("map class", map);

        try {
            return (Map<String, Parameter>) map.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Couldn't create an instance of " + map.toString(), ex);
        }
    }
}
