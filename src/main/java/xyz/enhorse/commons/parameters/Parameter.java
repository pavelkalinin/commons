package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.ClassConversion;
import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         30.08.2016
 */
public class Parameter<T> {

    private final String name;
    private final T value;
    private final Class<T> type;


    public Parameter(final String name, final T value) {
        this.name = Validate.urlSafe("parameter name", name);
        this.value = value;
        type = (value != null)
                ? new ClassConversion(value.getClass()).asPrimitiveIfPossible()
                : null;
    }


    public String name() {
        return name;
    }


    public T value() {
        return value;
    }


    public Class<T> type() {
        return type;
    }


    @Override
    public int hashCode() {
        return 31 * name.hashCode()
                + (value != null ? value.hashCode() : 0)
                + (type != null ? type.hashCode() : 0);
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Parameter<?> parameter = (Parameter<?>) o;

        return name.equals(parameter.name)
                && (value != null ? value.equals(parameter.value) : parameter.value == null);

    }


    @Override
    public String toString() {
        return String.format("\"%s\":{%s}:%s", name, value, type != null ? type.getName() : null);
    }
}
