package xyz.enhorse.commons.parameters;

import java.util.HashMap;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         27.04.2016
 */
public class BasicParameters extends AbstractParameters<HashMap> {

    public BasicParameters() {
        super(HashMap.class);
    }


    public BasicParameters(final String parameters) {
        super(HashMap.class, parameters);
    }


    public BasicParameters(final Parameters parameters) {
        super(HashMap.class, parameters);
    }
}
