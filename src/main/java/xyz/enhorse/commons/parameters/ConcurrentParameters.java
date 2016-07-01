package xyz.enhorse.commons.parameters;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         27.04.2016
 */
public class ConcurrentParameters extends AbstractParameters {

    public ConcurrentParameters() {
        super(new ConcurrentHashMap<>());
    }


    public ConcurrentParameters(final String parameters) {
        super(new ConcurrentHashMap<>(), parameters);
    }


    public ConcurrentParameters(final Parameters parameters) {
        super(new ConcurrentHashMap<>(), parameters);
    }
}
