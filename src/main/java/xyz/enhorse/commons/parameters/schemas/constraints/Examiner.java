package xyz.enhorse.commons.parameters.schemas.constraints;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         02.09.2016
 */
public interface Examiner<T> {

    boolean evaluate(final T value, final T constraint);

}
