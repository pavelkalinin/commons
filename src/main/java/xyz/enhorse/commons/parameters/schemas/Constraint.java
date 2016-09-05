package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class Constraint<T extends Comparable<T>> {
    private final T constraint;
    private final ConstraintChecker checker;


    public Constraint(final ConstraintChecker checker, final T constraint) {
        this.checker = Validate.notNull("constraint checker", checker);
        this.constraint = constraint;
    }


    public ConstraintChecker type() {
        return checker;
    }


    public T constraint() {
        return constraint;
    }


    public boolean check(final T value) {
        return checker.check(value, constraint);
    }


    @Override
    public int hashCode() {
        int result = (constraint != null) ? constraint.hashCode() : 0;
        return 31 * result + checker.hashCode();
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
                && (checker == that.checker);

    }


    @Override
    public String toString() {
        return checker.name() + '(' + constraint + ')';
    }
}
