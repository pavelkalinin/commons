package xyz.enhorse.commons.parameters;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         27.04.2016
 */
public class BasicParameters<T extends Map> extends AbstractParameters<T> {

    public BasicParameters(Class<T> map) {
        super(map);
    }
}
