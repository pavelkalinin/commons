package xyz.enhorse.commons.parameters.loaders;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public interface Loader {

    Logger LOGGER = Logger.getLogger(Loader.class);

    default Map<String, String> load() {
        return load(new Companion() {
        });
    }


    Map<String, String> load(final Companion companion);
}
