package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;
import xyz.enhorse.commons.parameters.schemas.PureType;
import xyz.enhorse.commons.parameters.schemas.PureTypes;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         07/09/16
 */
public class ParameterValue<T> {

    private final T value;
    private final PureType<?> type;


    public ParameterValue(final T value, final PureType<T> type) {
        this.value = value;
        this.type = Validate.defaultIfNull(type, PureTypes.identify(String.valueOf(value)));
    }


    public T value() {
        return value;
    }


    public PureType<?> type() {
        return type;
    }


    @Override
    public int hashCode() {
        return value != null
                ? value.hashCode()
                : 0;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ParameterValue<?> that = (ParameterValue<?>) o;

        return value != null
                ? value.equals(that.value)
                : that.value == null;

    }


    @Override
    public String toString() {
        return String.valueOf(value) + '[' + type + ']';
    }
}
