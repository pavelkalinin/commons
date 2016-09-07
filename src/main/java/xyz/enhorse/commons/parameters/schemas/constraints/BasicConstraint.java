package xyz.enhorse.commons.parameters.schemas.constraints;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public class BasicConstraint<T> implements Constraint<T> {

    private final T constraint;
    private final Examiner<T> examiner;


    public BasicConstraint(final T constraint, final Examiner<T> examiner) {
        this.examiner = Validate.notNull("examiner", examiner);
        this.constraint = constraint;
    }


    @Override
    public boolean isApplicable(final T value) {
        return examiner.evaluate(value, constraint);
    }


    @Override
    public int hashCode() {
        int result = (constraint != null) ? constraint.hashCode() : 0;
        return 31 * result + examiner.hashCode();
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
                && (examiner == that.examiner);

    }


    @Override
    public String toString() {
        return examiner.toString() + '(' + constraint + ')';
    }
}
