package xyz.enhorse.commons.parameters.schemas;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public interface ConstraintChecker<T> {

    boolean check(final T value, final T constraint);
}
