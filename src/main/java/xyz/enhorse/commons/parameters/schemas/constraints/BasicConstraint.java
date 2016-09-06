package xyz.enhorse.commons.parameters.schemas.constraints;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class BasicConstraint<T> implements Constraint<T> {

    private final T constraint;
    private final ConstraintChecker<T> checker;


    public BasicConstraint(final ConstraintChecker<T> checker, final T constraint) {
        this.checker = Validate.notNull("constraint checker", checker);
        this.constraint = constraint;
    }


    @Override
    public ConstraintChecker type() {
        return checker;
    }


    @Override
    public T constraint() {
        return constraint;
    }


    @Override
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

        BasicConstraint<?> that = (BasicConstraint<?>) o;


        return ((constraint != null) ? (constraint.equals(that.constraint)) : (that.constraint == null))
                && (checker == that.checker);

    }


    @Override
    public String toString() {
        return checker.name() + '(' + constraint + ')';
    }
}
