package xyz.enhorse.commons.parameters.schemas.constraints;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         06.09.2016
 */
public interface Constraint<T> {

    ConstraintChecker type();

    T constraint();

    boolean check(T value);
}
