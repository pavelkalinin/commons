package xyz.enhorse.commons.parameters.schemas;

import xyz.enhorse.commons.parameters.Parameter;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         01.09.2016
 */
public interface Schema {

    Schema put(final String name, final Description description);

    Schema remove(final String name);

    Schema clear();

    boolean validate(final Parameter parameter);
}
