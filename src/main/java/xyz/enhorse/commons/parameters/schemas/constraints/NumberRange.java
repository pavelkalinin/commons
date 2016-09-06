package xyz.enhorse.commons.parameters.schemas.constraints;

import xyz.enhorse.commons.Validate;

import static xyz.enhorse.commons.parameters.schemas.constraints.NumberExaminers.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06/09/16
 */
class NumberRange {

    private final Number min;
    private final Number max;


    NumberRange(final Number a, final Number b) {
        Validate.notNull("number", a);
        Validate.notNull("number", b);

        if (LESS.evaluate(a, b)) {
            min = a;
            max = b;
        } else {
            min = b;
            max = a;
        }
    }


    Number min() {
        return min;
    }


    Number max() {
        return max;
    }


    @Override
    public int hashCode() {
        int result = min.hashCode();
        result = 31 * result + max.hashCode();
        return result;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final NumberRange that = (NumberRange) o;

        return min.equals(that.min)
                && max.equals(that.max);

    }


    @Override
    public String toString() {
        return "{" + min() + ',' + max() + '}';
    }

}
