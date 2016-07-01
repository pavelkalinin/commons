package xyz.enhorse.commons.parameters;

import java.util.HashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         27.04.2016
 */
public class BasicParameters extends AbstractParameters {
    public BasicParameters() {
        super(new HashMap<>());
    }


    public BasicParameters(final String parameters) {
        super(new HashMap<>(), parameters);
    }


    public BasicParameters(final Parameters parameters) {
        super(new HashMap<>(), parameters);
    }
}
