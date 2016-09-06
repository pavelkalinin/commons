package xyz.enhorse.commons.parameters.schemas.constraints;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public enum NumberConstraintCheckers implements ConstraintChecker<Number> {
    EQUAL {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return ((constraint == null) && (value == null))
                    || ((constraint != null) && (value != null) && (compare(value, constraint) == 0));
        }
    },


    NOT_EQUAL {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return ((constraint == null) && (value != null))
                    || ((constraint != null) && (value != null) && (compare(value, constraint) != 0));
        }
    },


    GREATER {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return (constraint != null) && (value != null)
                    && (compare(value, constraint) < 0);
        }
    },


    LESS {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return (constraint != null) && (value != null)
                    && (compare(value, constraint) > 0);
        }
    },


    GREATER_OR_EQUAL {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return (constraint != null) && (value != null)
                    && (compare(value, constraint) <= 0);
        }
    },


    LESS_OR_EQUAL {
        @Override
        public boolean check(final Number value, final Number constraint) {
            return (constraint != null) && (value != null)
                    && (compare(value, constraint) >= 0);
        }
    };


    private static int compare(Number a, Number b) {
        if (isSpecial(a) || isSpecial(b)) {
            return Double.compare(a.doubleValue(), b.doubleValue());
        } else {
            return toBigDecimal(a).compareTo(toBigDecimal(b));
        }
    }


    private static boolean isSpecial(Number number) {
        boolean specialDouble = (number instanceof Double)
                && (Double.isNaN((Double) number)
                || Double.isInfinite((Double) number));

        boolean specialFloat = (number instanceof Float)
                && (Float.isNaN((Float) number)
                || Float.isInfinite((Float) number));

        return specialDouble || specialFloat;
    }


    private static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }

        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }

        if (number instanceof Byte || number instanceof Short
                || number instanceof Integer || number instanceof Long) {
            return new BigDecimal(number.longValue());
        }

        if (number instanceof Float || number instanceof Double) {
            return new BigDecimal(number.doubleValue());
        }

        try {
            return new BigDecimal(number.toString());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("The given number (\"" + number + "\" of class "
                    + number.getClass().getName() + ") does not have a parsable string representation", e);
        }
    }
}
