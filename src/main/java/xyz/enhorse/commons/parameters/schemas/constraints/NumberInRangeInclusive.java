package xyz.enhorse.commons.parameters.schemas.constraints;

import static xyz.enhorse.commons.parameters.schemas.constraints.NumberExaminers.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06/09/16
 */
public class NumberInRangeInclusive<T extends Number> implements Constraint<T> {

    private final NumberRange range;


    public NumberInRangeInclusive(final Number a, final Number b) {
        range = new NumberRange(a, b);
    }


    @Override
    public boolean isApplicable(final Number value) {
        return (value != null)
                && (GREATER_OR_EQUAL.evaluate(value, range.min()))
                && (LESS_OR_EQUAL.evaluate(value, range.max()));
    }


    @Override
    public int hashCode() {
        return range.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final NumberInRangeInclusive that = (NumberInRangeInclusive) o;

        return range.equals(that.range);
    }


    @Override
    public String toString() {
        return "NUMBER_IN[" + range.min() + ',' + range.max() + ']';
    }
}
