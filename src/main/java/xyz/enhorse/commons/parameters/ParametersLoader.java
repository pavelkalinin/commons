package xyz.enhorse.commons.parameters;

import xyz.enhorse.commons.StringPair;

import java.util.List;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         18.08.2016
 */
public interface ParametersLoader {

    List<StringPair> load();
}
