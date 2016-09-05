package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;
import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.parameters.schemas.PureType;
import xyz.enhorse.commons.parameters.schemas.PureTypes;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         30.08.2016
 */
public abstract class AbstractParameter<T extends PureType<T> & Comparable<T>> implements Parameter<T> {

    private final String name;
    private final String value;


    public AbstractParameter(final String parameter) {
        StringPair pair = StringPair.create(parameter, SEPARATOR);
        name = pair.leading();
        value = pair.trailing();
    }


    public AbstractParameter(final String name, final String value) {
        this.name = Validate.notNull("parameter name", name);
        this.value = value;
    }


    @Override
    public String name() {
        return name;
    }


    @SuppressWarnings("unchecked")
    @Override
    public T value() {
        return (T) PureTypes.convert(value);
    }


    @Override
    public PureType type() {
        return PureTypes.identify(value);
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

        AbstractParameter parameter = (AbstractParameter) o;

        return name.equals(parameter.name)
                && (value != null ? value.equals(parameter.value) : parameter.value == null);
    }


    @Override
    public String toString() {
        return name + SEPARATOR + value;
    }
}
