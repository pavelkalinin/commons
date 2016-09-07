package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         30.08.2016
 */
public class Parameter<T> {

    public static final char SEPARATOR = '=';

    private final String name;
    private final T value;


    public Parameter(final String name, final T value) {
        this.name = Validate.notNull("parameter name", name);
        this.value = value;
    }


    public String name() {
        return name;
    }


    public T value() {
        return value;
    }


    @Override
    public int hashCode() {
        return 31 * name.hashCode()
                + (value != null ? value.hashCode() : 0);
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

        return name.equals(parameter.name)
                && (value != null ? value.equals(parameter.value) : parameter.value == null);
    }


    @Override
    public String toString() {
        return name + SEPARATOR + value;
    }
}
