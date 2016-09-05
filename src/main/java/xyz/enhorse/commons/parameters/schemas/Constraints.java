package xyz.enhorse.commons.parameters.schemas;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public enum Constraints {
    EQUAL {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return ((constraint == null) && (value == null))
                    || ((constraint != null) && (value != null) && (constraint.compareTo(value) == 0));
        }
    },
    NOT_EQUAL {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return ((constraint == null) && (value != null))
                    || ((constraint != null) && (value != null) && (constraint.compareTo(value) != 0));
        }
    },
    GREATER {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return (constraint != null) && (value != null)
                    && (constraint.compareTo(value) < 0);
        }
    },
    LESS {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return (constraint != null) && (value != null)
                    && (constraint.compareTo(value) > 0);
        }
    },
    GREATER_OR_EQUAL {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return (constraint != null) && (value != null)
                    && (constraint.compareTo(value) <= 0);
        }
    },
    LESS_OR_EQUAL {
        @Override
        public <T extends Comparable<T>> boolean check(final T value, final T constraint) {
            return (constraint != null) && (value != null)
                    && (constraint.compareTo(value) >= 0);
        }
    };

    abstract <T extends Comparable<T>> boolean check(final T value, final T constraint);
}
