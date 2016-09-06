package xyz.enhorse.commons.parameters.schemas.constraints;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06/09/16
 */
public class NotNullConstraint<T> implements Constraint<T> {

    @Override
    public boolean isApplicable(final T value) {
        return value != null;
    }


    @Override
    public int hashCode() {
        return 0;
    }


    @Override
    public boolean equals(final Object obj) {
        return (obj != null) && (getClass() == obj.getClass());
    }


    @Override
    public String toString() {
        return "NOT_NULL";
    }
}
