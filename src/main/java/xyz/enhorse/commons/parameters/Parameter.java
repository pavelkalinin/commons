package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.parameters.schemas.PureType;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         31.08.2016
 */
public interface Parameter<T extends PureType<T> & Comparable<T>> {

    char SEPARATOR = '=';

    String name();

    T value();

    PureType type();
}