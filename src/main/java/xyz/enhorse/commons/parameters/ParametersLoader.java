package xyz.enhorse.commons.parameters;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public interface ParametersLoader {

    Logger LOGGER = Logger.getLogger(ParametersLoader.class);

    default Map<String, Object> load() {
        return load(new LoaderCompanion() {
        });
    }


    Map<String, Object> load(final LoaderCompanion companion);
}
