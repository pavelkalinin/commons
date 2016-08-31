package xyz.enhorse.commons.parameters;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         31.08.2016
 */
public interface Parameter {

    char SEPARATOR = '=';

    String name();

    <T> T value();

    Class<?> type();
}