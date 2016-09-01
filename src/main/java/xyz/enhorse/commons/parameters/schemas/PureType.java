package xyz.enhorse.commons.parameters.schemas;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         31.08.2016
 */
public interface PureType<T extends Comparable<T>> {

    T cast(final String string);

    Class<T> type();
}
