package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class Constraint<T extends Comparable<T>> {
    private final T constraint;
    private final Constraints type;


    public Constraint(final Constraints type, final T constraint) {
        this.type = Validate.notNull("constraint's type", type);
        this.constraint = constraint;
    }


    public Constraints type() {
        return type;
    }


    public T constraint() {
        return constraint;
    }


    public boolean check(final T value) {
        return type.check(value, constraint);
    }


    @Override
    public int hashCode() {
        int result = (constraint != null) ? constraint.hashCode() : 0;
        return 31 * result + type.hashCode();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Constraint<?> that = (Constraint<?>) o;


        return ((constraint != null) ? (constraint.equals(that.constraint)) : (that.constraint == null))
                && (type == that.type);

    }


    @Override
    public String toString() {
        return type.name() + '(' + constraint + ')';
    }
}
