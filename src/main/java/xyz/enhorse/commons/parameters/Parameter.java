package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.collections.NamedValue;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         30.08.2016
 */
public class Parameter<T> implements NamedValue<ParameterValue<?>> {

    public static final char SEPARATOR = '=';

    private final String name;
    private final ParameterValue<T> value;


    public Parameter(final String name, T value) {
        this.name = name;
        this.value = new ParameterValue<>(value, null);
    }


    public String name() {
        return name;
    }


    public ParameterValue<T> value() {
        return value;
    }


    public T deepValue() {
        return value.value();
    }


    @Override
    public int hashCode() {
        return 31 * name.hashCode()
                + value.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Parameter parameter = (Parameter) o;

        return (name.equals(parameter.name))
                && (value.equals(parameter.value));
    }


    @Override
    public String toString() {
        return name() + SEPARATOR + deepValue();
    }
}
