package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.Validate;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         05.09.2016
 */
public class BasicParameter<T> extends AbstractParameter<T> {

    public BasicParameter(final String name, final T value) {
        super(validate(name), value);
    }


    private static String validate(final String string) {
        Validate.notNull("parameter name", string);
        return Validate.notNullOrEmpty("parameter name", string.trim());
    }
}
